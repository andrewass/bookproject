package com.bookproject.controller;

import com.bookproject.entity.Author;
import com.bookproject.entity.Book;
import com.bookproject.repository.AuthorRepository;
import com.bookproject.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {

    @Autowired
    AuthorRepository authorRepository;

    @GetMapping(value = "/all-authors")
    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }


    @GetMapping(value = "/get-author/{name}")
    public Book getBookByTitle(@PathVariable String name){
        return authorRepository.findAuthorBylastName(name);
    }

    @PostMapping("/add-author")
    //@CrossOrigin(origins = "http://localhost:3000")
    public void registerBook(@RequestBody Author author) {
        //Book book = new Book(bookToCreate.title);
        System.out.println("Storing author  :" + author.getFirstName()+" "+author.getLastName());
        authorRepository.save(author);
    }

}
