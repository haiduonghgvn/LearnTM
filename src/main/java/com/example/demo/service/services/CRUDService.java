package com.example.demo.service.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CRUDService<T> {
//    Page<T> fillAll(Pageable pageable);

    List<T> listAll();
    ServiceResult delete(int id);
    T create(T t);
    T update(T t,int id);
}
