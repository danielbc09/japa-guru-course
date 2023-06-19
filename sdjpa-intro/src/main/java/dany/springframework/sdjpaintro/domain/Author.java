package dany.springframework.sdjpaintro.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Author {

  @Id @GeneratedValue private Long id;

  private String firstName;
  private String LastName;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return LastName;
  }

  public void setLastName(String lastName) {
    LastName = lastName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Author author = (Author) o;

    if (!Objects.equals(id, author.id)) return false;
    if (!Objects.equals(firstName, author.firstName)) return false;
    return Objects.equals(LastName, author.LastName);
  }

  @Override
  public int hashCode() {
    return id != null ? id.hashCode() : 0;
  }
}
