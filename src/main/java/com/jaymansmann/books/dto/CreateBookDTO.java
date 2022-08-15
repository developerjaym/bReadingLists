package com.jaymansmann.books.dto;

import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Set;

@Data
public class CreateBookDTO {
    @Size(min = 1, max = 24)
    private Set<Long> genreIds;
    @NotBlank
    private String title;
    @NotNull
    @Positive
    private int pages;
    @NotNull
    private LocalDate published;
    @Positive
    @NotNull
    private Long authorId;
}
