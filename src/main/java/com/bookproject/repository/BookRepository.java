package com.bookproject.repository;

import com.bookproject.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book findBookByTitle(String title);
}
