package com.bookproject.book;

import com.bookproject.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book findBookByTitle(String title);
}
