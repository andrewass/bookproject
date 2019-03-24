package com.bookproject.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book findBookByTitle(String title);

    @Query(value = "SELECT * FROM T_BOOK b ORDER BY b.DATE_CREATED DESC LIMIT ?1 ", nativeQuery = true)
    List<Book> findRecentlyAddedBooks(Integer count);

    Book findBookByBookId(Long bookId);
}
