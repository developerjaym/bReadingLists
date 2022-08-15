package com.jaymansmann.books.db.repository;

import com.jaymansmann.books.db.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByGenres_Id(Long genreId);

    boolean existsAllByIdIn(Iterable<Long> bookIds);
}
