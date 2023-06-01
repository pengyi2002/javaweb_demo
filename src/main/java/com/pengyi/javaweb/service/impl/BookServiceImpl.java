package com.pengyi.javaweb.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pengyi.javaweb.dao.BookDao;
import com.pengyi.javaweb.dao.impl.BookDaoImpl;
import com.pengyi.javaweb.domain.Book;
import com.pengyi.javaweb.service.BookService;
import com.pengyi.javaweb.util.JedisUtils;
import redis.clients.jedis.Jedis;

import java.util.List;


public class BookServiceImpl implements BookService {

    private BookDao bookDao = new BookDaoImpl();

    public String findAll() {
        Jedis jedis = JedisUtils.getJedis();
        String book_json = jedis.get("book");

        //判断数据是否存在
        if (book_json == null) {
            //redis无数据
            System.out.println("no data in redis,finding in database");
            List<Book> books = bookDao.findAll();
            ObjectMapper mapper = new ObjectMapper();
            try {
                book_json = mapper.writeValueAsString(books);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            //将json数据存入redis
            jedis.set("book", book_json);
            jedis.close();
        } else {
            System.out.println("data in redis");
        }
        return book_json;
    }

}
