package com.spring.data.bookproject.dao;

import com.spring.data.bookproject.domain.Author;

public interface AuthorDao {
  Author getById(Long id);
}
