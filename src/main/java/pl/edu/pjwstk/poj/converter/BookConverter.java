package pl.edu.pjwstk.poj.converter;

import org.springframework.stereotype.Component;
import pl.edu.pjwstk.poj.dto.BookDto;
import pl.edu.pjwstk.poj.entity.Book;

@Component
public class BookConverter {

    public BookDto convert(Book book){
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setTitle(book.getTitle());
        return bookDto;
    }


}
