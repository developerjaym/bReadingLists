package com.jaymansmann.books.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class CreateReadingListDTO {
    @NotBlank
    @NotNull
    private String name;
    @Size(min = 1, max = 1_000)
    private List<Long> bookIds;
}
