package com.bookproject.author;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author,Long> {

    List<Author> findAuthorBylastName(String name);
}
