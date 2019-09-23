package com.bookproject.bookreview;

import com.bookproject.user.User;
import com.bookproject.user.UserRepository;

public class AddBookReviewCommand {

    public static BookReview execute(AddBookReviewRequest request, UserRepository userRepository) {
        BookReview bookReview = new BookReview(request.getReview(), request.getTitle(), request.getStars());
        if (request.getUserId() != null) {
            User user = userRepository.findUserById(request.getUserId());
            if (user != null) {
                bookReview.setUser(user);
            }
        }
        return bookReview;
    }
}
