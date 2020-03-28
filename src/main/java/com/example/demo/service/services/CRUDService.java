package com.example.demo.service.services;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CRUDService<T> {

    List<T> listAll();
    ServiceResult delete(int id);
    T create(T t);
    T update(T t,int id);
}
