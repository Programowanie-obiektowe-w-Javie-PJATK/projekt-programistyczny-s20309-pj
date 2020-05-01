package pl.edu.pjwstk.poj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pjwstk.poj.entity.Book;

public interface BookRepository extends JpaRepository<Book,Long> {
}
