package demo.domain;

import java.util.Collection;
import javax.cache.annotation.CacheDefaults;
import javax.cache.annotation.CacheRemove;
import javax.cache.annotation.CacheResult;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

@CacheDefaults(cacheName = "books")
public interface BookRepository extends CrudRepository<Book, Long> {

	@Override
	@CacheResult
	Book findOne(Long aLong);

	@CacheRemove
	@Override
	void delete(Long aLong);

	@RestResource(path = "by-isbn")
	Book findByIsbn(@Param("id") String isbn);

	@RestResource(path = "by-author")
	Collection<Book> findByAuthorContainsIgnoreCase(@Param("name") String author);

}
