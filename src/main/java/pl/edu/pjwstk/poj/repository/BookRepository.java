package pl.edu.pjwstk.poj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import pl.edu.pjwstk.poj.dto.BookDto;
import pl.edu.pjwstk.poj.entity.Book;

public interface BookRepository extends JpaRepository<Book,Long> {
}
