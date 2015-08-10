package demo.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "book-orders")
public interface BookOrderRepository extends CrudRepository<BookOrder, Long> {
}
