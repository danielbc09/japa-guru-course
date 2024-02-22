package com.jpa.hibernatedao.dao;

import com.jpa.hibernatedao.domain.Author;
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
    Author author = authorDao.getByName("Craig", "Walls");

    assertThat(author).isNotNull();
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
    Author deleted = authorDao.getById(saved.getId());
    assertThat(deleted).isNull();
  }

  @Test
  void testGetAuthorByNameCriteria() {
    Author author = authorDao.findAuthorByNameCriteria("Craig", "Walls");

    assertThat(author).isNotNull();
  }

  @Test
  void testGetAuthorByNameNative() {
    Author author = authorDao.findAuthorByNameNative("Craig", "Walls");

    assertThat(author).isNotNull();
  }

  @Test
  void testListAuthorByLastNameLike() {
    List<Author> authors = authorDao.listAuthorByLastNameLike("Wall");

    assertThat(authors).isNotNull();
    assertThat(authors.size()).isPositive();
  }

  @Test
  void testFindAllAuthors() {
    List<Author> authors = authorDao.findAll();

    assertThat(authors).isNotNull();
    assertThat(authors.size()).isGreaterThan(0);
  }
}
