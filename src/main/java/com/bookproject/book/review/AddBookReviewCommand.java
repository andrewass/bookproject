package com.bookproject.book.review;

import com.bookproject.book.BookRepository;
import com.bookproject.user.User;
import com.bookproject.user.UserRepository;

public class AddBookReviewCommand {

    public static BookReview execute(AddReviewRequest request, UserRepository userRepository,
                                     BookRepository bookRepository) {
        validate(request);
        BookReview bookReview = new BookReview(request.getReview(), request.getTitle());
        if (request.getUserId() != null) {
            User user = userRepository.findUserById(request.getUserId());
            if (user != null) {
                bookReview.setUser(user);
                user.addReview(bookReview);
            }
        }
        return bookReview;
    }

    private static void validate(AddReviewRequest request) {
    }
}
