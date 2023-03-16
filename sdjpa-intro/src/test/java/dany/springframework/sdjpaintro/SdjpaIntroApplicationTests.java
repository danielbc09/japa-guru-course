package dany.springframework.sdjpaintro;

import dany.springframework.sdjpaintro.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ComponentScan(basePackages = "dany.springframework.sdjpaintro.bootstrap")
class SdjpaIntroApplicationTests {

  @Autowired BookRepository bookRepository;

  @Test
  void testBookRepository() {
    long count = bookRepository.count();
    assertThat(count).isGreaterThan(0);
  }

  @Test
  void contextLoads() {}
}
