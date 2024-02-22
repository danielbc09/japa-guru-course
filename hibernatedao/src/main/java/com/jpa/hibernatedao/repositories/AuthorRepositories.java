package com.jpa.hibernatedao.repositories;

import com.jpa.hibernatedao.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepositories extends JpaRepository<Author, Long> {}
