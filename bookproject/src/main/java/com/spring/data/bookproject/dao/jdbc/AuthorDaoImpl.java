package com.spring.data.bookproject.dao.jdbc;

import com.spring.data.bookproject.domain.Author;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

@Component
public class AuthorDaoImpl implements AuthorDao {

  private final DataSource source;

  public AuthorDaoImpl(DataSource dataSource) {
    this.source = dataSource;
  }

  @Override
  public Author getById(Long id) {
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;

    try {
      connection = source.getConnection();
      preparedStatement = connection.prepareStatement("SELECT * from author where id = ?");
      preparedStatement.setLong(1, id);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        return getAuthorFromRS(resultSet);
      }

    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      try {
        closeAll(resultSet, preparedStatement, connection);
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }
    return null;
  }

  @Override
  public Author getByName(String firstName, String lastName) {
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;

    try {
      connection = source.getConnection();
      preparedStatement =
          connection.prepareStatement(
              "SELECT * from author where first_name = ? and last_name = ?");
      preparedStatement.setString(1, firstName);
      preparedStatement.setString(2, lastName);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        return getAuthorFromRS(resultSet);
      }

    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      try {
        closeAll(resultSet, preparedStatement, connection);
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }
    return null;
  }

  @Override
  public Author saveNewAuthor(Author author) {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet resultSet = null;

    try {
      connection = source.getConnection();
      ps = connection.prepareStatement("INSERT INTO author (first_name, last_name) values (?, ?)");
      ps.setString(1, author.getFirstName());
      ps.setString(2, author.getLastName());
      ps.execute();

      Statement statement = connection.createStatement();

      resultSet = statement.executeQuery("SELECT LAST_INSERT_ID()");

      if (resultSet.next()) {
        Long savedId = resultSet.getLong(1);
        return this.getById(savedId);
      }

      statement.close();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        closeAll(resultSet, ps, connection);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return null;
  }

  @Override
  public Author updateAuthor(Author author) {
    Connection connection = null;
    PreparedStatement ps = null;

    try {
      connection = source.getConnection();
      ps =
          connection.prepareStatement(
              "UPDATE author set first_name = ?, last_name = ? where author.id = ?");
      ps.setString(1, author.getFirstName());
      ps.setString(2, author.getLastName());
      ps.setLong(3, author.getId());
      ps.execute();

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        closeAll(null, ps, connection);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return this.getById(author.getId());
  }

  @Override
  public void deleteAuthorById(Long id) {
    Connection connection = null;
    PreparedStatement ps = null;

    try {
      connection = source.getConnection();
      ps = connection.prepareStatement("DELETE from author where id = ?");
      ps.setLong(1, id);
      ps.execute();

    } catch (SQLException throwables) {
      throwables.printStackTrace();
    } finally {
      try {
        closeAll(null, ps, connection);
      } catch (SQLException ex) {

      }
    }
  }

  private Author getAuthorFromRS(ResultSet resultSet) throws SQLException {
    Author author = new Author();
    author.setId(resultSet.getLong("id"));
    author.setFirstName(resultSet.getString("first_name"));
    author.setLastName(resultSet.getString("last_name"));
    return author;
  }

  private void closeAll(
      ResultSet resultSet, PreparedStatement preparedStatement, Connection connection)
      throws SQLException {
    if (resultSet != null) {
      resultSet.close();
    }
    if (preparedStatement != null) {
      preparedStatement.close();
    }
    if (connection != null) {
      connection.close();
    }
  }
}
