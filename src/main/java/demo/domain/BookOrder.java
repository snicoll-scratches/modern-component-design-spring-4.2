package demo.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BookOrder implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	private LocalDateTime lastUpdated;

	private String isbn;

	private Long customerId;

	public BookOrder() {
	}

	public BookOrder(String isbn, Long customerId) {
		this.lastUpdated = LocalDateTime.now();
		this.isbn = isbn;
		this.customerId = customerId;
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(LocalDateTime lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "BookOrder{" + "id=" + id +
				", lastUpdated=" + lastUpdated +
				", isbn='" + isbn + '\'' +
				", customerId=" + customerId +
				'}';
	}

}
