package demo.domain;

public class BookNotFoundException extends RuntimeException {

	public BookNotFoundException(Long id) {
		super(String.format("Book with id '%s' not found", id));
	}

}