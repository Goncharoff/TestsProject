package repository.default_repos;

import java.util.Optional;

public interface CRUDRepository<T, ID> extends Repository<T, ID> {

  void delete(T entity);

  void deleteAll();

  void deleteById(ID id);

  Iterable<T> findAll();

  Iterable<T> findAllById(Iterable<ID> ids);

  Optional<T> findById(ID id);

  <S extends T> S save(S entity);

  <S extends T> S saveAll(Iterable<S> entities);

}
