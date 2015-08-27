package demo.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface MyRepository<T> extends CrudRepository<T, Long> {
}
