package com.spring.data.bookproject.dao.jdbctemplate;

import com.spring.data.bookproject.domain.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.dao.EmptyResultDataAccessException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(AuthorDaoTmplImpl.class)
public class AuthorDaoTmplImplTest {
  @Autowired AuthorDaoTmpl authorDao;

  @Test
  void testDeleteAuthor() {
    Author author = new Author();
    author.setFirstName("john");
    author.setLastName("t");

    Author saved = authorDao.saveNewAuthor(author);

    authorDao.deleteAuthorById(saved.getId());

  }

  @Test
  void testUpdateAuthor() {
    Author author = new Author();
    author.setFirstName("john");
    author.setLastName("t");

    Author saved = authorDao.saveNewAuthor(author);

    saved.setLastName("Thompson");
    Author updated = authorDao.updateAuthor(saved);

    assertThat(updated.getLastName()).isEqualTo("Thompson");
  }

  @Test
  void testInsertAuthor() {
    Author author = new Author();
    author.setFirstName("john");
    author.setLastName("t");

    Author saved = authorDao.saveNewAuthor(author);

    assertThat(saved).isNotNull();
  }

  @Test
  void testGetAuthorByName() {
    Author author = authorDao.findAuthorByName("Craig", "Walls");

    assertThat(author).isNotNull();
  }

  @Test
  void testGetAuthor() {

    Author author = authorDao.getById(1l);

    assertThat(author.getId()).isNotNull();
  }

  private class EmptyResultDataAccessException {}
}
