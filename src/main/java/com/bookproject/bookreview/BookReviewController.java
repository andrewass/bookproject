package com.bookproject.bookreview;

import com.bookproject.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookReviewController {

    @Autowired
    BookReviewRepository bookReviewRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/add-book-review")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<BookReview> addBookReview(@RequestBody AddBookReviewRequest request){
        BookReview review = AddBookReviewCommand.execute(request, userRepository);
        bookReviewRepository.save(review);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @GetMapping("/book-review/{title}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<BookReview>> getBookById(@PathVariable String title){
        List<BookReview> fetchedReviews = bookReviewRepository.findAllBookReviewsWithTitle(title);
        return !fetchedReviews.isEmpty() ?
                new ResponseEntity(fetchedReviews, HttpStatus.OK) :
                new ResponseEntity(fetchedReviews, HttpStatus.NOT_FOUND);
    }
}
