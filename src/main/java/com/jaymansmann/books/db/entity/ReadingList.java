package com.jaymansmann.books.db.entity;

import com.jaymansmann.books.db.entity.auth.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Table
@Entity
public class ReadingList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @ManyToMany
    private List<Book> books;

    @ManyToOne
    @NotNull
    private User user;
}
