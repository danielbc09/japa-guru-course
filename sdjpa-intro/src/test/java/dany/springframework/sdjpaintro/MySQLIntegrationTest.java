package dany.springframework.sdjpaintro;

import dany.springframework.sdjpaintro.domain.AuthorUuid;
import dany.springframework.sdjpaintro.domain.BookNatural;
import dany.springframework.sdjpaintro.domain.BookUuid;
import dany.springframework.sdjpaintro.domain.composite.AuthorComposite;
import dany.springframework.sdjpaintro.domain.composite.AuthorEmbedded;
import dany.springframework.sdjpaintro.domain.composite.NameId;
import dany.springframework.sdjpaintro.repositories.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = "dany.springframework.sdjpaintro.bootstrap")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MySQLIntegrationTest {

  @Autowired BookRepository bookRepository;
  @Autowired AuthorUuidRepository authorUuidRepository;
  @Autowired BookUuidRepository bookUuidRepository;
  @Autowired BookNaturalRepository bookNaturalRepository;

  @Autowired AuthorCompositeRepository authorCompositeRepository;
  @Autowired AuthorEmbeddedRepository authorEmbeddedRepository;

  @Test
  void testMySql() {
    long countBefore = bookRepository.count();
    assertThat(countBefore).isEqualTo(2);
  }

  @Test
  void testAuthorUuid() {
    AuthorUuid authorUuid = authorUuidRepository.save(new AuthorUuid());
    assertThat(authorUuid).isNotNull();
    assertThat(authorUuid).isNotNull();

    AuthorUuid fetched = authorUuidRepository.getReferenceById(authorUuid.getId());
    assertThat(fetched).isNotNull();
  }

  @Test
  void testBookUuid() {
    BookUuid bookUuid = bookUuidRepository.save(new BookUuid());
    assertThat(bookUuid).isNotNull();
    assertThat(bookUuid).isNotNull();

    BookUuid fetched = bookUuidRepository.getReferenceById(bookUuid.getId());
    assertThat(fetched).isNotNull();
  }

  @Test
  void bookNaturalTest() {
    BookNatural bookNatural = new BookNatural();
    bookNatural.setTitle("My Book");
    BookNatural saved = bookNaturalRepository.save(bookNatural);

    BookNatural fetched = bookNaturalRepository.getReferenceById(saved.getTitle());
    assertThat(fetched).isNotNull();
    assertEquals("My Book", fetched.getTitle());
  }

  @Test
  void authorCompositeTest() {
    NameId nameId = new NameId("John", "T");
    AuthorComposite authorComposite = new AuthorComposite();
    authorComposite.setFirstName(nameId.getFirstName());
    authorComposite.setLastName(nameId.getLastName());

    AuthorComposite saved = authorCompositeRepository.save(authorComposite);

    AuthorComposite fetched = authorCompositeRepository.getReferenceById(nameId);
    assertThat(fetched).isNotNull();
    assertEquals(nameId.getLastName(), fetched.getLastName());
  }

  @Test
  void authorEmbeddedTest() {
    NameId nameId = new NameId("John", "T");
    AuthorEmbedded authorEmbedded = new AuthorEmbedded(nameId);

    AuthorEmbedded saved = authorEmbeddedRepository.save(authorEmbedded);
    assertThat(saved).isNotNull();

    AuthorEmbedded fetched = authorEmbeddedRepository.getReferenceById(nameId);
    assertThat(fetched).isNotNull();
    assertEquals(nameId.getFirstName(), fetched.getNameId().getFirstName());
  }
}
