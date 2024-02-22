package com.jpa.hibernatedao.dao;

import com.jpa.hibernatedao.domain.Author;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorDaoImpl implements AuthorDao {

  private final EntityManagerFactory emf;

  public AuthorDaoImpl(EntityManagerFactory emf) {
    this.emf = emf;
  }

  @Override
  public Author getById(Long id) {
    return getEntityManager().find(Author.class, id);
  }

  @Override
  public Author getByName(String firstName, String lastName) {
    EntityManager em = getEntityManager();

    TypedQuery<Author> query = em.createNamedQuery("find_by_name", Author.class);

    query.setParameter("first_name", firstName);
    query.setParameter("last_name", lastName);

    Author author = query.getSingleResult();
    em.close();
    return author;
  }

  @Override
  public Author saveNewAuthor(Author author) {
    EntityManager em = getEntityManager();
    em.getTransaction().begin();
    em.persist(author);
    em.flush();
    em.getTransaction().commit();
    return author;
  }

  @Override
  public Author updateAuthor(Author updated) {
    EntityManager em = getEntityManager();
    try {
      em.joinTransaction();
      em.merge(updated);
      em.flush();
      em.clear();
      return em.find(Author.class, updated.getId());
    } finally {
      em.close();
    }
  }

  @Override
  public void deleteAuthorById(Long id) {
    EntityManager em = getEntityManager();
    em.getTransaction().begin();
    Author author = em.find(Author.class, id);
    em.remove(author);
    em.flush();
    em.getTransaction().commit();
  }

  @Override
  public List<Author> listAuthorByLastNameLike(String lastName) {
    EntityManager em = getEntityManager();
    try {
      Query query = em.createQuery("SELECT a from Author a where a.lastName like :last_name");
      query.setParameter("last_name", lastName + "%");
      return query.getResultList();
    } finally {
      em.close();
    }
  }

  @Override
  public Author findAuthorByNameCriteria(String firstName, String lastName) {
    EntityManager em = getEntityManager();

    try {
      CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
      CriteriaQuery<Author> criteriaQuery = criteriaBuilder.createQuery(Author.class);

      Root<Author> root = criteriaQuery.from(Author.class);

      ParameterExpression<String> firstNameParam = criteriaBuilder.parameter(String.class);
      ParameterExpression<String> lastNameParam = criteriaBuilder.parameter(String.class);

      Predicate firstNamePred = criteriaBuilder.equal(root.get("firstName"), firstNameParam);
      Predicate lastNamePred = criteriaBuilder.equal(root.get("lastName"), lastNameParam);

      criteriaQuery.select(root).where(criteriaBuilder.and(firstNamePred, lastNamePred));

      TypedQuery<Author> typedQuery = em.createQuery(criteriaQuery);
      typedQuery.setParameter(firstNameParam, firstName);
      typedQuery.setParameter(lastNameParam, lastName);

      return typedQuery.getSingleResult();
    } finally {
      em.close();
    }
  }

  @Override
  public Author findAuthorByNameNative(String firstName, String lastName) {
    EntityManager em = getEntityManager();

    try {
      Query query =
          em.createNativeQuery(
              "SELECT * FROM author a WHERE a.first_name = ? and a.last_name = ?", Author.class);

      query.setParameter(1, firstName);
      query.setParameter(2, lastName);

      return (Author) query.getSingleResult();
    } finally {
      em.close();
    }
  }

  @Override
  public List<Author> findAll() {
    EntityManager em = getEntityManager();

    try {
      TypedQuery<Author> typedQuery = em.createNamedQuery("author_find_all", Author.class);
      return typedQuery.getResultList();
    } finally {
      em.close();
    }
  }

  private EntityManager getEntityManager() {
    return emf.createEntityManager();
  }
}