package dany.springframework.sdjpaintro.repositories;

import dany.springframework.sdjpaintro.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {}
