package com.jaymansmann.books.dto;

import lombok.Data;

import java.util.List;

@Data
public class ReadingListResponseDTO {
    private Long id;
    private String name;
    private List<BookResponseDTO> books;
}
