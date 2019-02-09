package com.bookproject.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Author>> getAuthorByLastName(@PathVariable String lastName){
        List<Author> authors = authorRepository.findAuthorBylastName(lastName);
        if(authors.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @PostMapping("/add-author")
    @CrossOrigin(origins = "http://localhost:3000")
    ResponseEntity<Author> addAuthor(@RequestBody Author author) {
        authorRepository.save(author);
        return new ResponseEntity<>(author, HttpStatus.CREATED);
    }
}
