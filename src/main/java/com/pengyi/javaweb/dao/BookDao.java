package com.pengyi.javaweb.dao;

import com.pengyi.javaweb.domain.Book;

import java.util.List;

public interface BookDao {
    public List<Book> findAll();
}
