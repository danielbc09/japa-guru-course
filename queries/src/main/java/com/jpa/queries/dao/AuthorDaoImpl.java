package com.jpa.queries.dao;

import com.jpa.queries.domain.Author;
import com.jpa.queries.repositories.AuthorRepository;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;

public class AuthorDaoImpl implements AuthorDao {
  private final AuthorRepository authorRepository;

  public AuthorDaoImpl(AuthorRepository authorRepository) {
    this.authorRepository = authorRepository;
  }

  @Override
  public Author getById(Long id) {
    return authorRepository.findById(id).orElseThrow(EntityNotFoundException::new);
  }

  @Override
  public Author findAuthorByName(String firstName, String lastName) {
    return authorRepository
        .findAuthorByFirstNameAndLastName(firstName, lastName)
        .orElseThrow(EntityNotFoundException::new);
  }

  @Override
  public Author saveNewAuthor(Author author) {
    return authorRepository.save(author);
  }

  @Transactional
  @Override
  public Author updateAuthor(Author author) {
    Optional<Author> foundAuthorOpt = authorRepository.findById(author.getId());
    Author foundAuthor = foundAuthorOpt.get();
    foundAuthor.setFirstName(author.getFirstName());
    foundAuthor.setFirstName(author.getLastName());
    return authorRepository.save(foundAuthor);
  }

  @Override
  public void deleteAuthorById(Long id) {
    authorRepository.deleteById(id);
  }
}
