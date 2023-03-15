package dany.springframework.sdjpaintro;

import dany.springframework.sdjpaintro.domain.Book;
import dany.springframework.sdjpaintro.repositories.BookRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
@ComponentScan(basePackages = "dany.springframework.sdjpaintro.bootstrap")
public class SpringBootJpaSpliceTest {

  @Autowired BookRepository bookRepository;

  @Order(1)
  @Test
  void testJpTestSplice() {
    long countBefore = bookRepository.count();

    bookRepository.save(new Book("My test book", "13213213", "Self"));

    long countAfter = bookRepository.count();

    assertThat(countBefore).isLessThan(countAfter);
  }

  @Order(2)
  @Test
  void testJpTestSpliceTransaction() {
    long countBefore = bookRepository.count();
    assertThat(countBefore).isEqualTo(2L);
  }
}
