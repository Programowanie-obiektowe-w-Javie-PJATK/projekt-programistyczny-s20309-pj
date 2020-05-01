package pl.edu.pjwstk.poj.service;

import org.springframework.stereotype.Service;
import pl.edu.pjwstk.poj.converter.BookConverter;
import pl.edu.pjwstk.poj.repository.BookRepository;

@Service
public class BookService {

    private BookRepository bookRepository;
    private BookConverter converter;
}
