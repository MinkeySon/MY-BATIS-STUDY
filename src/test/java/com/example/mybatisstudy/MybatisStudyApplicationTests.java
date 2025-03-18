package com.example.mybatisstudy;

import com.example.mybatisstudy.dao.BookDao;
import com.example.mybatisstudy.dao.UserDao;
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
    private BookRepository bookRepository;
    @Autowired
    private UserDao userDao;
    @Autowired
    private BookDao bookDao;


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
        User users = userDao.findById(678L);
        System.out.println("user info : " + users.toString());
    }

    @Test
    @DisplayName("책 저장 JPA")
    void saveBook(){
        for(int i=1; i<=1000; i++){
            bookRepository.save(new Book("serial"+i, "test", "title", "publisher", "img", "대여가능", "rentalDate", "rentalDueDate"));
        }
    }

    @Test
    @DisplayName("책 대여 JPA")
    void getRentalJpaBook(){
        for(int i=1; i<=1000; i = i*2){
            Book book = bookRepository.findById((long) i).get();

            book.setRentalStatus("대여 불가능");
            bookRepository.save(book);
        }
    }

    @Test
    @DisplayName("책 대여 Mybatis")
    void getRentalMybatisBook(){
        for(int i=1; i<=1000; i = i*2){
            bookDao.rentalBooks((long) i);
        }
    }

    @Test
    @DisplayName("책 대여 상태 초기화")
    void clearBook(){
        for(int i=1; i<=1000; i++){
            Book book = bookRepository.findById((long) i).get();

            book.setRentalStatus("대여가능");
            bookRepository.save(book);
        }
    }

    @Test
    @DisplayName("책 렌탈")
    void rentalBooks(){
        User user = userRepository.findById((long)1).get();
        for(int i=1; i<=1000; i=i*2){
            Book book = bookRepository.findById((long)i).get();
            book.setUser(user);
            bookRepository.save(book);
        }
    }

    @Test
    @DisplayName("내가 렌탈한 책 조회 JPA")
    void getRentalBooksJPA(){
        User user = userRepository.findById((long)1).get();
        List<Book> bookList = bookRepository.findAllByUser(user);

        bookList.stream().forEach(book -> {
            System.out.println(book.getTitle());
            System.out.println(book.getUser().getName());
        });

    }
    @Test
    @DisplayName("내가 렌탈한 책 조회 Mybatis")
    void getRentalBooksMybatis(){
        User user = userDao.findById((long)1);
        List<Book> bookList = bookDao.findMyBooks(user.getName());

        bookList.stream().forEach(book -> {
            System.out.println(book.getTitle());
            System.out.println(user.getName());
        });
    }
}
