package demo;

import java.util.Arrays;
import javax.annotation.PostConstruct;

import demo.domain.Book;
import demo.domain.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class AppInitializer {

	private final BookRepository bookRepository;

	@Autowired
	public AppInitializer(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@PostConstruct
	public void initData() {
		this.bookRepository.save(Arrays.asList(
						new Book("978-1491924228", "Matt Stine",
								"Migrating to Cloud-Native Application Architectures", 0D),
						new Book("978-144937464", "Josh Long & Kenny Bastani",
								"Cloud Native Java", 33.99),
						new Book("978-0764543852", "Rod Johnson",
								"Expert One-on-One J2EE Design and Development", 30.63),
						new Book("978-0764558313", "Rod Johnson",
								"Expert One-on-One J2EE Development without EJB", 23.57),
						new Book("978-1617291203", "Craig Walls",
								"Spring in Action", 45.97),
						new Book("078-5342349603", "Brian Goetz",
								"Java Concurrency in Practice", 34.45))
		);
	}

}
