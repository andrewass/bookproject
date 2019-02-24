package com.bookproject.book;

import com.bookproject.author.Author;
import com.bookproject.user.User;

import java.util.Date;

class BookBuilder {

    private String title;
    private User owner;
    private String isbn;
    private Author author;
    private Date datePublished;
    private BookCondition bookCondition;


    public BookBuilder withTitle(String title){
        this.title = title;
        return this;
    }

    public BookBuilder withCondition(BookCondition condition) {
        this.bookCondition = condition;
        return this;
    }

    public BookBuilder withOwner(User owner){
        this.owner = owner;
        return this;
    }

    public BookBuilder withAuthor(Author author){
        this.author = author;
        return this;
    }

    public Book build(){
        Book book = new Book();
        book.setAuthor(this.author);
        book.setCondition(this.bookCondition);
        book.setOwner(this.owner);
        return book;
    }
}