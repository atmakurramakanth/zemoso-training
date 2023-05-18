package com.librarymanagementsystem.service;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Services<T> {

    public List<T> findAll();
    public T findById(int id);
    public void save(T object);
    public void deleteById(int id);
}
