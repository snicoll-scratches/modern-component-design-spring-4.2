package demo.domain;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

public interface BookRepository extends CrudRepository<Book, Long> {

	@RestResource(path = "by-isbn")
	Book findByIsbn(@Param("id") String isbn);

	@RestResource(path = "by-author")
	Collection<Book> findByAuthorContainsIgnoreCase(@Param("name") String author);

}
