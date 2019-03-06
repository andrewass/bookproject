package com.bookproject.book;

import com.bookproject.author.Author;
import com.bookproject.user.User;

class BookBuilder {

    private String title;
    private User owner;
    private Author author;
    private BookCondition bookCondition;
    private Integer publishedYear;


    BookBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    BookBuilder withCondition(BookCondition condition) {
        this.bookCondition = condition;
        return this;
    }

    BookBuilder withOwner(User owner) {
        this.owner = owner;
        return this;
    }

    BookBuilder withAuthor(Author author) {
        this.author = author;
        return this;
    }

    BookBuilder withPublishedYear(Integer publishedYear) {
        this.publishedYear = publishedYear;
        return this;
    }

    Book build() {
        Book book = new Book();
        book.setTitle(this.title);
        book.setAuthor(this.author);
        book.setCondition(this.bookCondition);
        book.setOwner(this.owner);
        book.setYearPublished(this.publishedYear);
        return book;
    }
}
