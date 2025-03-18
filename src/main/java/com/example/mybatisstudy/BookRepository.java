package com.example.mybatisstudy;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByRentalStatus(String rentalStatus);
    List<Book> findAllByUser(User user);
}
