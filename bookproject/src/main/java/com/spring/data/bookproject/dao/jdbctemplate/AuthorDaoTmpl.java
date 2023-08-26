package com.spring.data.bookproject.dao.jdbctemplate;

import com.spring.data.bookproject.domain.Author;

public interface AuthorDaoTmpl {
  Author getById(Long id);

  Author findAuthorByName(String firstName, String lastName);

  Author saveNewAuthor(Author author);

  Author updateAuthor(Author author);

  void deleteAuthorById(Long id);
}
