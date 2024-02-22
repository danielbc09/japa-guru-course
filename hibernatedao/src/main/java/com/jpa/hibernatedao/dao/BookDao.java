package com.jpa.hibernatedao.dao;

import com.jpa.hibernatedao.domain.Book;

import java.util.List;

public interface BookDao {
  Book getById(Long id);

  Book findBookByTitle(String title);

  Book findBookByTitleCriteria(String title);

  Book saveNewBook(Book book);

  Book updateBook(Book book);

  void deleteBookById(Long id);

  Book findByISBN(String isbn);

  List<Book> findAll();

  Book findBookByTitleNative(String title);
}
