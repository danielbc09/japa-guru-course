package com.spring.data.bookproject.dao.jdbctemplate;

import com.spring.data.bookproject.domain.Author;
import com.spring.data.bookproject.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({AuthorDaoTmplImpl.class, BookDaoTmplImpl.class})
public class BookDaoTmplImplTest {
  @Autowired BookDaoTmplImpl bookDao;

  @Test
  void testDeleteBook() {
    Book book = new Book();
    book.setIsbn("1234");
    book.setPublisher("Self");
    book.setTitle("my book");
    Author author = new Author();
    author.setId(3L);
    book.setAuthor(author);
    Book saved = bookDao.saveNewBook(book);

    bookDao.deleteBookById(saved.getId());

    assertThrows(
        EmptyResultDataAccessException.class,
        () -> {
          bookDao.getById(saved.getId());
        });
  }

  @Test
  void updateBookTest() {
    Book book = new Book();
    book.setIsbn("1234");
    book.setPublisher("Self");
    book.setTitle("my book");
    Author author = new Author();
    author.setId(3L);
    book.setAuthor(author);
    Book saved = bookDao.saveNewBook(book);

    saved.setTitle("New Book");
    bookDao.updateBook(saved);

    Book fetched = bookDao.getById(saved.getId());

    assertThat(fetched.getTitle()).isEqualTo("New Book");
  }

  @Test
  void testSaveBook() {
    Book book = new Book();
    book.setIsbn("1234");
    book.setPublisher("Self");
    book.setTitle("my book");
    Author author = new Author();
    author.setId(3L);
    book.setAuthor(author);

    Book saved = bookDao.saveNewBook(book);

    assertThat(saved).isNotNull();
  }

  @Test
  void testGetBookByName() {
    Book book = bookDao.findBookByTitle("Clean Code");

    assertThat(book).isNotNull();
  }

  @Test
  void testGetBook() {
    Book book = bookDao.getById(3L);

    assertThat(book.getId()).isNotNull();
  }
}
