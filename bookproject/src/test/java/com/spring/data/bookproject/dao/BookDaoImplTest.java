package com.spring.data.bookproject.dao;

import com.spring.data.bookproject.dao.jdbc.AuthorDaoImpl;
import com.spring.data.bookproject.dao.jdbc.BookDao;
import com.spring.data.bookproject.dao.jdbc.BookDaoImpl;
import com.spring.data.bookproject.domain.Author;
import com.spring.data.bookproject.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("local")
@DataJpaTest
@Import({AuthorDaoImpl.class, BookDaoImpl.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookDaoImplTest {
  @Autowired BookDao bookDao;

  @Test
  void testDeleteBook() {
    Book book = new Book();
    book.setIsbn("1234");
    book.setPublisher("Self");
    book.setTitle("my book");
    Book saved = bookDao.saveNewBook(book);

    bookDao.deleteBookById(saved.getId());

    Book deleted = bookDao.getById(saved.getId());

    assertThat(deleted).isNull();
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
