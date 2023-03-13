package dany.springframework.sdjpaintro.bootstrap;

import dany.springframework.sdjpaintro.domain.Book;
import dany.springframework.sdjpaintro.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

  private final BookRepository bookRepository;

  public DataInitializer(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    Book book1 = new Book("Domain Driven Design", "123", "Manning");

    bookRepository.save(book1);
    Book book2 = new Book("Spring in action", "23232323", "Orreily");
    bookRepository.save(book2);

    bookRepository
        .findAll()
        .forEach(
            book -> {
              System.out.println("Book id: " + book.getId());
              System.out.println("Book Title: " + book.getTitle());
            });
  }
}
