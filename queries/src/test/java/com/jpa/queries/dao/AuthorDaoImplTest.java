package com.jpa.queries.dao;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.jpa.queries.domain.Author;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("local")
@DataJpaTest
@Import(AuthorDaoImpl.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AuthorDaoImplTest {
  @Autowired AuthorDao authorDao;

  @Test
  void testGetAuthor() {

    Author author = authorDao.getById(1L);

    assertThat(author).isNotNull();
  }

  @Test
  void testGetAuthorByName() {
    Author author = authorDao.findAuthorByName("Craig", "Walls");

    assertThat(author).isNotNull();
  }

  @Test
  void testGetAuthorByNameNotFound() {
    assertThrows(
        EntityNotFoundException.class,
        () -> {
          Author author = authorDao.findAuthorByName("foo", "bar");
        });
  }

  @Test
  void testSaveAuthor() {
    Author author = new Author();
    author.setFirstName("Daniel");
    author.setLastName("Bautista");

    Author saved = authorDao.saveNewAuthor(author);

    assertThat(saved).isNotNull();
    assertThat(saved.getId()).isNotNull();
  }

  @Test
  void testUpdateAuthor() {
    Author author = new Author();
    author.setFirstName("Jose");
    author.setLastName("M");

    Author saved = authorDao.saveNewAuthor(author);

    saved.setLastName("Melo");
    Author updated = authorDao.updateAuthor(saved);

    assertThat(updated.getLastName()).isEqualTo("Melo");
  }

  @Test
  void testDeleteAuthor() {
    Author author = new Author();
    author.setFirstName("Jose");
    author.setLastName("Macias");

    Author saved = authorDao.saveNewAuthor(author);
    authorDao.deleteAuthorById(saved.getId());
    assertThrows(
        JpaObjectRetrievalFailureException.class,
        () -> {
          Author deleted = authorDao.getById(saved.getId());
        });
  }
}
