package com.bookproject.controller;

import com.bookproject.entity.Author;
import com.bookproject.entity.Book;
import com.bookproject.repository.AuthorRepository;
import com.bookproject.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController("/book")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;


    @GetMapping(value = "/all-books")
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }


    @GetMapping(value = "/get-book/{title}")
    public Book getBookByTitle(@PathVariable String title){
        return bookRepository.findBookByTitle(title);
    }

    @PostMapping("/add-book")
    //@CrossOrigin(origins = "http://localhost:3000")
    public void registerBook(@RequestBody Book book) {
        System.out.println("Storing book with title :" + book.getTitle());
        bookRepository.save(book);

        Book retrivedBook = bookRepository.findBookByTitle(book.getTitle());
        if(retrivedBook != null){
            System.out.println("Found the book");
            if(retrivedBook.getAuthor() != null) {
                Optional<Author> authorOptional = authorRepository.findById(retrivedBook.getAuthor().getAuthorId());
                if (authorOptional.isPresent()) {
                    Author author = authorOptional.get();
                    System.out.println("Author "+author.getFirstName()+" "+author.getLastName()+" has stored "+author.getPublishedBooks().size()+" books");
                }
            }
        }
    }

}
