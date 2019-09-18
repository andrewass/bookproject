package com.bookproject.book;

import com.bookproject.author.AuthorRepository;
import com.bookproject.exception.RequestValidationException;
import com.bookproject.misc.PropertyUtils;
import com.bookproject.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PropertyUtils propertyUtils;

    @Autowired
    private BookResourceAssembler bookResourceAssembler;

    @GetMapping(value = "/all-books")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Resources<BookResource>> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        List<BookResource> resourcesList = bookResourceAssembler.toResources(books);
        Resources<BookResource> resources = new Resources<>(resourcesList);
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<BookResource> getBookById(@PathVariable Long id) {
        Book fetchedBook = bookRepository.findBookById(id);
        if (fetchedBook != null) {
            BookResource bookResource = bookResourceAssembler.toResource(fetchedBook);
            return ResponseEntity.ok(bookResource);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add-book")
    @CrossOrigin(origins = "*")
    public ResponseEntity<BookResource> addBook(@RequestBody Book book) throws RequestValidationException {
        //Book book = execute(request, authorRepository, userRepository);
        bookRepository.save(book);
        BookResource resource = bookResourceAssembler.toResource(book);
        return ResponseEntity.ok(resource);
    }

    @GetMapping("/recent-books")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<Book>> getRecentBooks(@RequestParam Integer count) {
        List<Book> recentBooks = FindRecentAddedBooksCommand.execute(count, bookRepository, propertyUtils.getApiKey());
        return new ResponseEntity<>(recentBooks, HttpStatus.OK);
    }
}

