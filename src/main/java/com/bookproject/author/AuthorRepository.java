package com.bookproject.author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query(value = "SELECT * From T_AUTHOR WHERE FIRST_NAME = ?1 AND LAST_NAME = ?2", nativeQuery = true)
    Author findAuthorByName(String firstname, String lastname);
}
