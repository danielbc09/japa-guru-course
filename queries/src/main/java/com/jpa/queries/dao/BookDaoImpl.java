package com.jpa.queries.dao;

import com.jpa.queries.domain.Book;
import com.jpa.queries.repositories.BookRepository;
import jakarta.persistence.EntityNotFoundException;

public class BookDaoImpl implements BookDao {

  private final BookRepository bookRepository;

  public BookDaoImpl(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @Override
  public Book getById(Long id) {
    return bookRepository.findById(id).orElseThrow(EntityNotFoundException::new);
  }

  @Override
  public Book findBookByTitle(String title) {
    return bookRepository.findBookByTitle(title).orElseThrow(EntityNotFoundException::new);
  }

  @Override
  public Book saveNewBook(Book book) {
    return bookRepository.save(book);
  }

  @Override
  public Book updateBook(Book book) {
    Book foundBook =
        bookRepository.findById(book.getId()).orElseThrow(EntityNotFoundException::new);
    foundBook.setIsbn(book.getIsbn());
    foundBook.setPublisher(book.getPublisher());
    foundBook.setAuthorId(book.getAuthorId());
    foundBook.setTitle(book.getTitle());
    return bookRepository.save(foundBook);
  }

  @Override
  public void deleteBookById(Long id) {
    bookRepository.deleteById(id);
  }
}
