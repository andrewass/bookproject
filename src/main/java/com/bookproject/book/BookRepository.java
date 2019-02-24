package com.bookproject.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book findBookByTitle(String title);

    @Query(value = "SELECT * From T_BOOK b LIMIT ?1" , nativeQuery = true)
    List<Book> findRecentlyAddedBooks(Integer count);
}
