package com.jpa.hibernatedao.dao;

import com.jpa.hibernatedao.domain.Author;

import java.util.List;

public interface AuthorDao {
  Author getById(Long id);

  Author getByName(String firstName, String lastName);

  Author saveNewAuthor(Author author);

  Author updateAuthor(Author saved);

  void deleteAuthorById(Long id);

  List<Author> listAuthorByLastNameLike(String lastName);

  List<Author> findAll();

  Author findAuthorByNameCriteria(String firstName, String lastName);

  Author findAuthorByNameNative(String firstName, String lastName);

}
