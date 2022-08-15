package com.jaymansmann.books.db.repository;

import com.jaymansmann.books.db.entity.ReadingList;
import com.jaymansmann.books.dto.ReadingListResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReadingListRepository extends JpaRepository<ReadingList, Long> {
    List<ReadingList> findAllByUserId(Long userId);

    Optional<ReadingList> findByIdAndUserId(Long readingListId, Long userId);
}
