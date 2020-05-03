package pl.edu.pjwstk.poj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.edu.pjwstk.poj.dto.BookDto;
import pl.edu.pjwstk.poj.exception.ErrorResponse;
import pl.edu.pjwstk.poj.service.BookService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getOneById(@PathVariable(value = "id") Long id) {
        Optional<BookDto> book = bookService.getBookById(id);
        if (book.isPresent()) {
            return new ResponseEntity<>(book.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorResponse("The book was not found", 404), HttpStatus.NOT_FOUND);
    }

    @PostMapping("/books")
    public ResponseEntity<?> save(@RequestBody BookDto book){
        BookDto savedBook = bookService.saveBook(book);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedBook.getId()).toUri();
        return ResponseEntity.created(location).build();
    }



    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody BookDto book, @PathVariable Long id){

        Optional<BookDto> bookToUpdate = Optional.ofNullable(bookService.getBookById(book.getId()).map(newBook -> {
            book.setAuthor(book.getAuthor());
            book.setTitle(book.getTitle());
            return bookService.saveBook(book);
        })
                .orElseGet(() -> {
                    book.setId(id);
                    return bookService.saveBook(book);
                }));
        return new ResponseEntity<>(bookToUpdate, HttpStatus.OK);

    }


    @GetMapping
    public ResponseEntity<List<BookDto>> getAll() {
        return new ResponseEntity<>(bookService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        Optional<BookDto> book = bookService.getBookById(id);
        if (book.isPresent()) {
            bookService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity(new ErrorResponse("The book was not found", 404), HttpStatus.NOT_FOUND);

    }

}
