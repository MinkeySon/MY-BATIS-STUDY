package com.example.mybatisstudy;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@ToString
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String serialNumber;
    @Column
    private String category;
    @Column
    private String title;
    @Column
    private String publisher;
    @Column
    private String img;
    @Column
    private String rentalStatus;
    @Column
    private String rentalDate;
    @Column
    private String rentalDueDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private User user;

    public Book(String serialNumber, String category, String title, String publisher, String img, String rentalStatus, String rentalDate, String rentalDueDate){
        this.serialNumber = serialNumber;
        this.category = category;
        this.title = title;
        this.publisher = publisher;
        this.img = img;
        this.rentalStatus = rentalStatus;
        this.rentalDate = rentalDate;
        this.rentalDueDate = rentalDueDate;
    }
}
