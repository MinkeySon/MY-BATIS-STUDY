package com.example.mybatisstudy.dao;

import com.example.mybatisstudy.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    User findById(Long id);
}
