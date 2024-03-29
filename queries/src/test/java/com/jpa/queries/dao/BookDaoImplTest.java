package com.jpa.queries.dao;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.jpa.queries.domain.Author;
import com.jpa.queries.domain.Book;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("local")
@DataJpaTest
@Import(BookDaoImpl.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookDaoImplTest {

  @Autowired BookDao bookDao;

  @Test
  void testGetBook() {
    Book book = bookDao.getById(3L);

    assertThat(book.getId()).isNotNull();
  }

  @Test
  void testGetBookByName() {
    Book book = bookDao.findBookByTitle("Clean Code");

    assertThat(book).isNotNull();
  }

  @Test
  void testSaveBook() {
    Book book = new Book();
    book.setIsbn("1234");
    book.setPublisher("Self");
    book.setTitle("my book");

    Author author = new Author();
    author.setId(3L);

    Book saved = bookDao.saveNewBook(book);

    assertThat(saved).isNotNull();
  }

  @Test
  void saveNewBook() {
    Book book = new Book();
    book.setIsbn("1234");
    book.setPublisher("Self");
    book.setTitle("My book");

    book.setAuthorId(1L);
    Book saved = bookDao.saveNewBook(book);

    assertThat(saved).isNotNull();
    assertThat(saved.getId()).isNotNull();
  }

  @Test
  void updateBookTest() {
    Book book = new Book();
    book.setIsbn("1234");
    book.setPublisher("Self");
    book.setTitle("my book");

    Author author = new Author();
    author.setId(3L);

    Book saved = bookDao.saveNewBook(book);

    saved.setTitle("New Book");
    bookDao.updateBook(saved);

    Book fetched = bookDao.getById(saved.getId());

    assertThat(fetched.getTitle()).isEqualTo("New Book");
  }

  @Test
  void testDeleteBook() {
    Book book = new Book();
    book.setIsbn("1234");
    book.setPublisher("Self");
    book.setTitle("my book");
    Book saved = bookDao.saveNewBook(book);

    bookDao.deleteBookById(saved.getId());

    assertThrows(
        EntityNotFoundException.class,
        () -> {
          bookDao.getById(saved.getId());
        });
  }
}
