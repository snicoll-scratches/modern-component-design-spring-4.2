package demo.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@SuppressWarnings("serial")
public class Book implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	@Column(unique = true, nullable = false)
	private String isbn;

	private String author;

	private String title;

	private Double price;

	protected Book() {
	}

	public Book(String isbn, String author, String title, Double price) {
		this.isbn = isbn;
		this.author = author;
		this.title = title;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Book{" + "id=" + id +
				", isbn='" + isbn + '\'' +
				", author='" + author + '\'' +
				", title='" + title + '\'' +
				", price=" + price +
				'}';
	}

}
