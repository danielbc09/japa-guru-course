package com.spring.data.bookproject.dao.jdbc;

import com.spring.data.bookproject.domain.Author;

public interface AuthorDao {
  Author getById(Long id);
  Author getByName(String firstName, String lastName);
  Author saveNewAuthor(Author author);

  Author updateAuthor(Author saved);

  void deleteAuthorById(Long id);
}
