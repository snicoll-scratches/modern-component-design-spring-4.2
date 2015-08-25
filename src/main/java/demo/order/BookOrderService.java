package demo.order;

import demo.domain.Book;
import demo.domain.BookNotFoundException;
import demo.domain.BookOrder;
import demo.domain.BookOrderRepository;
import demo.domain.BookOrderStatus;
import demo.domain.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookOrderService {

	private static final Logger logger = LoggerFactory.getLogger(BookOrderService.class);

	private final BookRepository bookRepository;

	private final BookOrderRepository bookOrderRepository;

	private final JmsMessagingTemplate messagingTemplate;

	@Autowired
	public BookOrderService(BookRepository bookRepository, BookOrderRepository bookOrderRepository,
			JmsMessagingTemplate messagingTemplate) {
		this.bookRepository = bookRepository;
		this.bookOrderRepository = bookOrderRepository;
		this.messagingTemplate = messagingTemplate;
	}

	@Transactional
	public BookOrder order(Long bookId, Long customerId) {
		Book book = this.bookRepository.findOne(bookId);
		if (book == null) {
			throw new BookNotFoundException(bookId);
		}
		BookOrder order = new BookOrder(book.getIsbn(), customerId);
		this.bookOrderRepository.save(order);
		logger.info("Created " + order + " successfully");
		this.messagingTemplate.convertAndSend("bookOrder", order);
		return order;
	}

	@JmsListener(destination = "bookOrder")
	@SendTo("bookOrderStatus")
	public BookOrderStatus verifyBookOrder(BookOrder bookOrder) {
		try {
			// Don't do this at home
			Thread.sleep(2000);
			return new BookOrderStatus(bookOrder, BookOrderStatus.Status.PENDING);
		}
		catch (InterruptedException o_O) {
			throw new IllegalStateException("Ooops", o_O);
		}

	}

	@JmsListener(destination = "bookOrderStatus")
	public void handleBookOrderStatus(BookOrderStatus status) {
		logger.info(String.format("Book order '%s' has now status '%s'", status.getOrderId(), status.getStatus()));
	}
}