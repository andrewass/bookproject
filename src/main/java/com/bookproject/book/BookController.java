package com.bookproject.book;

import com.bookproject.author.AuthorRepository;
import com.bookproject.exception.RequestValidationException;
import com.bookproject.misc.PropertyUtils;
import com.bookproject.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bookproject.book.AddBookCommand.execute;

@Slf4j
@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PropertyUtils propertyUtils;

    @GetMapping(value = "/all-books")
    @CrossOrigin(origins = "*")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/book/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book fetchedBook = bookRepository.findBookById(id);
        return fetchedBook != null ? new ResponseEntity<>(fetchedBook, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add-book")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Book> addBook(@RequestBody AddBookRequest request) throws RequestValidationException {
        Book book = execute(request, authorRepository, userRepository);
        bookRepository.save(book);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/recent-books")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<Book>> getRecentBooks(@RequestParam Integer count) {
        List<Book> recentBooks = FindRecentAddedBooksCommand.execute(count, bookRepository, propertyUtils.getApiKey());
        return new ResponseEntity<>(recentBooks, HttpStatus.OK);
    }
}

