package com.jaymansmann.books.service;

import com.jaymansmann.books.db.entity.Genre;
import com.jaymansmann.books.db.repository.GenreRepository;
import com.jaymansmann.books.dto.GenreDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<Genre> findAllByGenreIds(Collection<Long> ids) {
        return genreRepository.findAllById(ids);
    }

    public List<GenreDTO> getAll() {
        return genreRepository.findAll().stream().map(genre -> modelMapper.map(genre, GenreDTO.class)).toList();
    }
}
