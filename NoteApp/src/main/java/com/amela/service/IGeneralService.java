package com.amela.service;

import java.util.List;
import java.util.Optional;

public interface IGeneralService <T>{
    Iterable<T> findAll();

    Optional<T> findById(Integer id);

    void save(T t);


    void remove(Integer id);
}
