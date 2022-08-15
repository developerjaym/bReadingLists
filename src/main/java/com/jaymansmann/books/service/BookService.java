package com.jaymansmann.books.service;

import com.jaymansmann.books.db.entity.Book;
import com.jaymansmann.books.db.repository.BookRepository;
import com.jaymansmann.books.dto.BookResponseDTO;
import com.jaymansmann.books.dto.CreateBookDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private GenreService genreService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private ModelMapper modelMapper;

    public BookResponseDTO create(CreateBookDTO createBookDTO) {
        Book book = modelMapper.map(createBookDTO, Book.class);
        book.setGenres(genreService.findAllByGenreIds(createBookDTO.getGenreIds()));
        book.setAuthor(authorService.getAuthorById(createBookDTO.getAuthorId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No author")));
        book = bookRepository.save(book);
        return mapBookToBookResponseDTO(book);
    }

    public BookResponseDTO getById(Long id) {
        return bookRepository.findById(id).map(this::mapBookToBookResponseDTO).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No book"));
    }

    public List<BookResponseDTO> getAll() {
        return bookRepository.findAll().stream().map(this::mapBookToBookResponseDTO).toList();
    }

    public BookResponseDTO updateById(Long id, CreateBookDTO createBookDTO) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        modelMapper.map(createBookDTO, book); // put createBookDTO's values into book
        book.setGenres(genreService.findAllByGenreIds(createBookDTO.getGenreIds()));
        book.setAuthor(authorService.getAuthorById(createBookDTO.getAuthorId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No author")));
        book = bookRepository.save(book);
        return mapBookToBookResponseDTO(book);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    public List<BookResponseDTO> getByGenreId(Long genreId) {
        return bookRepository.findAllByGenres_Id(genreId).stream().map(this::mapBookToBookResponseDTO).toList();
    }

    private BookResponseDTO mapBookToBookResponseDTO(Book book) {
        return modelMapper.map(book, BookResponseDTO.class);
    }

    public List<Book> findAllByBookIds(Iterable<Long> bookIds) {
        return bookRepository.findAllById(bookIds);
    }
}
