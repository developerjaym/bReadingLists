package com.jaymansmann.books.service;

import com.jaymansmann.books.db.entity.Author;
import com.jaymansmann.books.db.repository.AuthorRepository;
import com.jaymansmann.books.dto.AuthorDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<Author> getAuthorsById(Collection<Long> ids) {
        return authorRepository.findAllById(ids);
    }

    public Optional<Author> getAuthorById(Long author) {
        return authorRepository.findById(author);
    }

    public List<AuthorDTO> getAll() {
        return authorRepository.findAll().stream().map(author -> modelMapper.map(author, AuthorDTO.class)).toList();
    }
}
