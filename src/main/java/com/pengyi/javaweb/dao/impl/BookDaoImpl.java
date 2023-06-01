package com.pengyi.javaweb.dao.impl;

import com.pengyi.javaweb.dao.BookDao;
import com.pengyi.javaweb.domain.Book;
import com.pengyi.javaweb.util.JdbcUtils;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class BookDaoImpl implements BookDao {
    private JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource());

    public List<Book> findAll() {
        String sql = "SELECT * FROM book";
        List<Book> books = template.query(sql, new BeanPropertyRowMapper<>(Book.class));
        return books;
    }

    @Test
    public void test() {
        String sql = "SELECT * FROM book";
        List<Book> books = template.query(sql, new BeanPropertyRowMapper<>(Book.class));
        System.out.println(books);
    }
}
