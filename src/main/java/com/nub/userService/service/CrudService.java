package com.nub.userService.service;

public interface CrudService<E, K> {
    E createOrUpdate(E object);

    E find(K id);

    Iterable<E> findAll();

    boolean delete(K id);


}
