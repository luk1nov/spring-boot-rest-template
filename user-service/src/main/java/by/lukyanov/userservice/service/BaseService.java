package by.lukyanov.userservice.service;

import java.util.List;

public interface BaseService<T> {
    T create(T t);

    T findById(Long id);

    List<T> findAll();

    T update(Long id, T t);

    void deleteById(Long id);
}
