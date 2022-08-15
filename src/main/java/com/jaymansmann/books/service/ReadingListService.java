package com.jaymansmann.books.service;

import com.jaymansmann.books.db.entity.ReadingList;
import com.jaymansmann.books.db.repository.ReadingListRepository;
import com.jaymansmann.books.dto.CreateReadingListDTO;
import com.jaymansmann.books.dto.ReadingListResponseDTO;
import com.jaymansmann.books.security.UserPrincipal;
import com.jaymansmann.books.service.auth.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ReadingListService {
    @Autowired
    private ReadingListRepository readingListRepository;
    @Autowired
    private BookService bookService;
    @Autowired
    private AuthService authService;
    @Autowired
    private ModelMapper modelMapper;


    public ReadingListResponseDTO create(CreateReadingListDTO createReadingListDTO, UserPrincipal principal) {
        ReadingList readingList = modelMapper.map(createReadingListDTO, ReadingList.class);
        readingList.setBooks(bookService.findAllByBookIds(createReadingListDTO.getBookIds()));
        readingList.setUser(authService.getUser(principal));
        return mapReadingListToReadingListResponseDTO(readingListRepository.save(readingList));
    }

    public List<ReadingListResponseDTO> getAllByUserId(Long userId, UserPrincipal principal) {
        secure(userId, principal);
        return readingListRepository.findAllByUserId(userId).stream().map(this::mapReadingListToReadingListResponseDTO).toList();
    }

    public ReadingListResponseDTO getByUserIdAndReadingListId(Long userId, Long readingListId, UserPrincipal principal) {
        secure(userId, principal);
        return readingListRepository.findByIdAndUserId(readingListId, userId).map(this::mapReadingListToReadingListResponseDTO).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    private ReadingListResponseDTO mapReadingListToReadingListResponseDTO(ReadingList readingList) {
        return modelMapper.map(readingList, ReadingListResponseDTO.class);
    }

    private void secure(Long userId, UserPrincipal principal) {
        if(!principal.getId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }
}
