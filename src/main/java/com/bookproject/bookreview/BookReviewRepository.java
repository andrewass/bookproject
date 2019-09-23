package com.bookproject.bookreview;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookReviewRepository extends JpaRepository<BookReview, Long> {

    @Query(value = "SELECT * FROM T_BOOK_REVIEW WHERE TITLE = ?1", nativeQuery = true)
    List<BookReview> findAllBookReviewsWithTitle(String title);
}