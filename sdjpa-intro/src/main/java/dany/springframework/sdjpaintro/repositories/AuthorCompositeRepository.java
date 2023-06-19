package dany.springframework.sdjpaintro.repositories;

import dany.springframework.sdjpaintro.domain.composite.AuthorComposite;
import dany.springframework.sdjpaintro.domain.composite.NameId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorCompositeRepository extends JpaRepository<AuthorComposite, NameId> {}
