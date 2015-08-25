package demo.domain;

import java.util.Collection;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

@CacheConfig(cacheNames = "books")
public interface BookRepository extends CrudRepository<Book, Long> {

	@Override
	@Cacheable
	Book findOne(Long aLong);

	@CacheEvict
	@Override
	void delete(Long aLong);

	@RestResource(path = "by-isbn")
	Book findByIsbn(@Param("id") String isbn);

	@RestResource(path = "by-author")
	Collection<Book> findByAuthorContainsIgnoreCase(@Param("name") String author);

}
