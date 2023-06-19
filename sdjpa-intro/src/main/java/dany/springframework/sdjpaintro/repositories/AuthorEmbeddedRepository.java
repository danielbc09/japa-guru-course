package dany.springframework.sdjpaintro.repositories;

import dany.springframework.sdjpaintro.domain.composite.AuthorEmbedded;
import dany.springframework.sdjpaintro.domain.composite.NameId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorEmbeddedRepository extends JpaRepository<AuthorEmbedded, NameId> {}
