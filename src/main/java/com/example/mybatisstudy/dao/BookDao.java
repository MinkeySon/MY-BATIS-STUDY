package com.example.mybatisstudy.dao;

import com.example.mybatisstudy.Book;
import com.example.mybatisstudy.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookDao {
    List<Book> findMyBooks(String userName);
    void getRentalBooks();
    void rentalBooks(Long id);
}
