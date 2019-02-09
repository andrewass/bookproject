package com.bookproject.repository;

import com.bookproject.entity.Author;
import com.bookproject.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Long> {

    Book findAuthorBylastName(String name);
}
