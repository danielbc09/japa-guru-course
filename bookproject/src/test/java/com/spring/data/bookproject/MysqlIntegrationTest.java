package com.spring.data.bookproject;

import com.spring.data.bookproject.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MysqlIntegrationTest {
  @Autowired BookRepository bookRepository;

  @Test
  void testMySQL() {
    long countBefore = bookRepository.count();
    assertThat(countBefore).isGreaterThan(0);
  }
}
