package com.jaymansmann.books.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class BookResponseDTO {
    private Long id;
    private String title;
    private int pages;
    private LocalDate published;
    private List<GenreDTO> genres;
    private AuthorDTO author;
}
