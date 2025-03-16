package com.example.mybatisstudy.mappers;

import com.example.mybatisstudy.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> findById(Long id);
}
