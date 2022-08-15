package com.jaymansmann.books.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
    @ManyToMany
    private List<Genre> genres = new ArrayList<>();
    @NotBlank
    private String title;
    @Positive
    private int pages;
    @NotNull
    private LocalDate published;

    @ManyToMany(mappedBy = "books", cascade = CascadeType.ALL)
    private List<ReadingList> readingLists;
}
