package com.example.brainbounce.repositories;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends Repository<T, ID> {

    <S extends T> S save(S entity);

    Optional<T> findById(ID id);

    List<T> findAll();

    void deleteById(ID id);

    void delete(T entity);

    long count();

    boolean existsById(Long id);

    // Add any other method from JPA if you want to ..
}
