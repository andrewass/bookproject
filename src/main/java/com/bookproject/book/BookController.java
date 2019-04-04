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

    Logger logger = Logger.getLogger(BookController.class.getName());

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PropertyUtils propertyUtils;

    @GetMapping(value = "/all-books")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/book/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book fetchedBook = bookRepository.findBookById(id);
        return fetchedBook != null ? new ResponseEntity<>(fetchedBook, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add-book")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Book> addBook(@RequestBody AddBookRequest request) {
        try {
            Book book = AddBookCommand.execute(request, authorRepository, userRepository);
            bookRepository.save(book);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch (Exception e) {
            logger.log(Level.INFO, e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/recent-books")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<Book>> getRecentBooks(@RequestParam Integer count) {
        try {
            List<Book> recentBooks = FindRecentAddedBooksCommand.execute(count, bookRepository, propertyUtils.getApiKey());
            return new ResponseEntity<>(recentBooks, HttpStatus.OK);
        } catch (Exception e) {
            logger.log(Level.INFO, e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

