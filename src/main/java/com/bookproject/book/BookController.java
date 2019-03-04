package com.bookproject.book;

import com.bookproject.author.AuthorRepository;
import com.bookproject.misc.PropertyUtils;
import com.bookproject.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PropertyUtils propertyUtils;

    @GetMapping(value = "/all-books")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping(value = "/get-book/{title}")
    @CrossOrigin(origins = "http://localhost:3000")
    public Book getBookByTitle(@PathVariable String title) {
        return bookRepository.findBookByTitle(title);
    }

    @PostMapping("/add-book")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Book> addBook(@RequestBody AddBookRequest request) {
        try {
            Book book = AddBookCommand.execute(request, authorRepository, userRepository);
            bookRepository.save(book);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch (Exception e) {
            Logger logger = Logger.getLogger(BookController.class.getName());
            logger.log(Level.INFO, e.getMessage());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/recent-books")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<Book>> getRecentBooks(@RequestParam Integer count) {
        try {
            List<Book> recentBooks = FindRecentAddedBooksCommand.execute(count, bookRepository, propertyUtils.getApiKey());
            return new ResponseEntity<>(recentBooks, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
