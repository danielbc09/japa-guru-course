package com.spring.data.bookproject.repositories;

import com.spring.data.bookproject.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepositories extends JpaRepository<Author, Long> {}
