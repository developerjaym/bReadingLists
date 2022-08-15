package com.jaymansmann.books.controller;

import com.jaymansmann.books.dto.CreateReadingListDTO;
import com.jaymansmann.books.dto.ReadingListResponseDTO;
import com.jaymansmann.books.security.UserPrincipal;
import com.jaymansmann.books.service.ReadingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users/{userId}/reading_lists")
public class ReadingListController {

    @Autowired
    private ReadingListService readingListService;

    @GetMapping
    public List<ReadingListResponseDTO> getAllByUserId(@PathVariable Long userId,
                                                       Authentication authentication) {
        return readingListService.getAllByUserId(userId,
                (UserPrincipal) authentication.getPrincipal());

    }

    @GetMapping("/{readingListId}")
    public ReadingListResponseDTO getByUserIdAndReadingListId(@PathVariable Long userId,
                                                             @PathVariable Long readingListId,
                                            Authentication authentication) {
        return readingListService.getByUserIdAndReadingListId(userId, readingListId,
                (UserPrincipal) authentication.getPrincipal());

    }

    @PostMapping
    public ReadingListResponseDTO create(@Valid @RequestBody CreateReadingListDTO createReadingListDTO,
                                         Authentication authentication) {
        return readingListService.create(createReadingListDTO, (UserPrincipal) authentication.getPrincipal());
    }
}
