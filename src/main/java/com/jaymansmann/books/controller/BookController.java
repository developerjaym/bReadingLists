package com.jaymansmann.books.controller;

import com.jaymansmann.books.dto.BookResponseDTO;
import com.jaymansmann.books.dto.CreateBookDTO;
import com.jaymansmann.books.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public BookResponseDTO create(@RequestBody @Valid CreateBookDTO createBookDTO) {
        return bookService.create(createBookDTO);
    }

    @GetMapping
    public List<BookResponseDTO> getAll() {
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    public BookResponseDTO getAll(@PathVariable Long id) {
        return bookService.getById(id);
    }

    @PutMapping("/{id}")
    public BookResponseDTO putById(@PathVariable Long id, @RequestBody @Valid CreateBookDTO createBookDTO) {
        return bookService.updateById(id, createBookDTO);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        bookService.deleteById(id);
    }

}
