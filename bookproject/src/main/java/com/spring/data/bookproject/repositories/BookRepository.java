package com.spring.data.bookproject.repositories;

import com.spring.data.bookproject.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {}
