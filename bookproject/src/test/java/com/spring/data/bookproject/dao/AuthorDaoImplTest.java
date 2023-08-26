package com.spring.data.bookproject.dao;

import com.spring.data.bookproject.dao.jdbc.AuthorDao;
import com.spring.data.bookproject.domain.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"com.spring.data.bookproject.dao"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AuthorDaoImplTest {

  @Autowired
  AuthorDao authorDao;

  @Test
  void testGetAuthorById() {
    Author author = authorDao.getById(1L);

    assertThat(author).isNotNull();
  }

  @Test
  void testGetAuthorByName() {
    Author author = authorDao.getByName("Craig", "Walls");

    assertThat(author).isNotNull();
  }

  @Test
  void testSaveAuthor() {
    Author author = new Author();
    author.setFirstName("John");
    author.setLastName("Thompson");
    Author saved = authorDao.saveNewAuthor(author);

    assertThat(saved).isNotNull();
  }

  @Test
  void testUpdateAuthor() {
    Author author = new Author();
    author.setFirstName("Daniel");
    author.setLastName("B");

    Author saved = authorDao.saveNewAuthor(author);

    saved.setLastName("Bautista");
    Author updated = authorDao.updateAuthor(saved);

    assertThat(updated.getLastName()).isEqualTo("Bautista");
  }

  @Test
  void testDeleteAuthor() {
    Author author = new Author();
    author.setFirstName("john");
    author.setLastName("t");

    Author saved = authorDao.saveNewAuthor(author);

    authorDao.deleteAuthorById(saved.getId());

    Author deleted = authorDao.getById(saved.getId());

    assertThat(deleted).isNull();
  }
}
