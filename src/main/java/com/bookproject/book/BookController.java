package com.bookproject.book;

import com.bookproject.author.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;


    @GetMapping(value = "/all-books")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }


    @GetMapping(value = "/get-book/{title}")
    @CrossOrigin(origins = "http://localhost:3000")
    public Book getBookByTitle(@PathVariable String title){
        return bookRepository.findBookByTitle(title);
    }

    @PostMapping("/add-book")
    @CrossOrigin(origins = "http://localhost:3000")
    public void registerBook(@RequestBody Book book) {
        bookRepository.save(book);
    }
}
