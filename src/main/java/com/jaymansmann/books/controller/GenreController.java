package com.jaymansmann.books.controller;

import com.jaymansmann.books.dto.BookResponseDTO;
import com.jaymansmann.books.dto.GenreDTO;
import com.jaymansmann.books.service.BookService;
import com.jaymansmann.books.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/genre")
public class GenreController {

    @Autowired
    private BookService bookService;

    @Autowired
    private GenreService genreService;

    @GetMapping("/{id}/books")
    public List<BookResponseDTO> getBooksByGenre(@PathVariable Long id) {
        return bookService.getByGenreId(id);
    }

    @GetMapping
    public List<GenreDTO> getAll() {
        return genreService.getAll();
    }

}
