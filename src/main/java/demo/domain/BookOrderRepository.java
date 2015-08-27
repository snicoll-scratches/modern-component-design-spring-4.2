package demo.domain;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "book-orders")
public interface BookOrderRepository extends MyRepository<BookOrder> {
}
