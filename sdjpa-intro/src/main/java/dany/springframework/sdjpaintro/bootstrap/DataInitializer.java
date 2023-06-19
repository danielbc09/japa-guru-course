package dany.springframework.sdjpaintro.bootstrap;

import dany.springframework.sdjpaintro.domain.AuthorUuid;
import dany.springframework.sdjpaintro.domain.Book;
import dany.springframework.sdjpaintro.domain.BookUuid;
import dany.springframework.sdjpaintro.repositories.AuthorUuidRepository;
import dany.springframework.sdjpaintro.repositories.BookRepository;
import dany.springframework.sdjpaintro.repositories.BookUuidRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

  private final BookRepository bookRepository;
  private final AuthorUuidRepository authorUuidRepository;
  private final BookUuidRepository bookUuidRepository;

  public DataInitializer(
      BookRepository bookRepository,
      AuthorUuidRepository authorUuidRepository,
      BookUuidRepository bookUuidRepository) {
    this.bookRepository = bookRepository;
    this.authorUuidRepository = authorUuidRepository;
    this.bookUuidRepository = bookUuidRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    bookRepository.deleteAll();
    Book book1 = new Book("Domain Driven Design", "123", "Manning", null);

    bookRepository.save(book1);
    Book book2 = new Book("Spring in action", "23232323", "Orreily", null);
    bookRepository.save(book2);

    bookRepository
        .findAll()
        .forEach(
            book -> {
              System.out.println("Book id: " + book.getId());
              System.out.println("Book Title: " + book.getTitle());
            });

    AuthorUuid authorUuid = new AuthorUuid();
    authorUuid.setFirstName("Joe");
    authorUuid.setLastName("Buck");
    AuthorUuid savedAuthor = authorUuidRepository.save(authorUuid);
    System.out.println("Saved Author UUID: " + savedAuthor.getId());

    BookUuid bookUuid = new BookUuid();
    bookUuid.setTitle("All About UUIDs");
    BookUuid savedBookUuid = bookUuidRepository.save(bookUuid);
    System.out.println("Saved Book UUID: " + savedBookUuid.getId());
  }
}
