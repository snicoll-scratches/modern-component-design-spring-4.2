package demo.domain;

import java.io.Serializable;
import java.time.LocalDate;

@SuppressWarnings("serial")
public class BookOrderStatus implements Serializable {

	private Long orderId;

	private Status status;

	private LocalDate lastUpdated;

	public BookOrderStatus() {
	}

	public BookOrderStatus(BookOrder order, Status status) {
		this.orderId = order.getId();
		this.status = status;
		this.lastUpdated = LocalDate.now();
	}

	public Long getOrderId() {
		return orderId;
	}

	public Status getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "OrderStatus{" + "orderId='" + orderId + '\'' +
				", status='" + status + '\'' +
				", lastUpdated=" + lastUpdated +
				'}';
	}


	public enum Status {
		PENDING,

		PROCESSING,

		COMPLETED
	}

}