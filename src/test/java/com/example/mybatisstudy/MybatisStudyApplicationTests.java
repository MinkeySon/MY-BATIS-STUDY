package com.example.mybatisstudy;

import com.example.mybatisstudy.mappers.UserMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = MybatisStudyApplication.class)
class MybatisStudyApplicationTests {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @Test
    @DisplayName("User 생성")
    void createUser() {
        for(int i=1; i<=10_000; i++){
            userRepository.save(new User("test"+i, i % 20, "test@"+i+"co.kr"));
        }
    }

    @Test
    @DisplayName("User id로 조회 JPA")
    void selectJPAUser(){
        Optional<User> users = userRepository.findById(678L);
        if(users.isPresent()){
            System.out.println("user info : " + users.toString());
        }
    }

    @Test
    @DisplayName("User id로 조회 Mybatis")
    void selectMyBatisUser(){
        List<User> users = userMapper.findById(678L);
        if(!users.isEmpty()){
            System.out.println("user info : " + users.toString());
        }
    }
}
