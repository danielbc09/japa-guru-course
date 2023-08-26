package com.spring.data.bookproject.dao.jdbctemplate;

import com.spring.data.bookproject.domain.Book;

public interface BookDaoTmpl {
  Book getById(Long id);

  Book findBookByTitle(String title);

  Book saveNewBook(Book book);

  Book updateBook(Book book);

  void deleteBookById(Long id);
}
