package com.jpa.hibernatedao.repositories;

import com.jpa.hibernatedao.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {}
