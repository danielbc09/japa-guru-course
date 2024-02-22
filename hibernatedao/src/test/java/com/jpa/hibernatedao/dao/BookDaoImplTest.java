package com.jpa.hibernatedao.dao;

import com.jpa.hibernatedao.domain.Book;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("local")
@DataJpaTest
@Import(BookDaoImpl.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookDaoImplTest {

  @Autowired BookDao bookDao;

  @Test
  void getById() {
    Book book = bookDao.getById(1L);

    assertThat(book).isNotNull();
  }

  @Test
  void findBookByTitle() {
    Book book = bookDao.findBookByTitle("Domain-Driven Design");

    assertThat(book).isNotNull();
  }

  @Test
  void testGetBookByTitleCriteria() {
    Book book = bookDao.findBookByTitleCriteria("Clean Code");

    assertThat(book).isNotNull();
  }

  @Test
  void testGetBookByNameNative() {
    Book book = bookDao.findBookByTitleNative("Clean Code");

    assertThat(book).isNotNull();
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
  void updateBook() {
    Book book = new Book();
    book.setIsbn("1234");
    book.setPublisher("Self");
    book.setTitle("my book");

    book.setAuthorId(1L);
    Book saved = bookDao.saveNewBook(book);

    saved.setTitle("New Book");
    bookDao.updateBook(saved);

    Book fetched = bookDao.getById(saved.getId());

    assertThat(fetched.getTitle()).isEqualTo("New Book");
  }

  @Test
  void deleteBookById() {
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
  void testFindBookByISBN() {
    Book book = new Book();
    book.setIsbn("1234" + RandomString.make());
    book.setTitle("ISBN TEST");

    Book saved = bookDao.saveNewBook(book);
    Book fetched = bookDao.findByISBN(book.getIsbn());

    assertThat(fetched).isNotNull();
  }

  @Test
  void testFindAllBooks() {
    List<Book> books = bookDao.findAll();

    assertThat(books).isNotNull();
    assertThat(books.size()).isGreaterThan(0);
  }
}
