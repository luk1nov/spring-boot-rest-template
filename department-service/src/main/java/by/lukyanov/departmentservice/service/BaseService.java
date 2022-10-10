package by.lukyanov.departmentservice.service;

import java.util.List;
import java.util.Optional;

public interface BaseService<T> {
    T create(T t);

    T findById(Long id);

    List<T> findAll();

    T update(Long id, T t);

    void deleteById(Long id);
}
