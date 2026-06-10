package it.itsacademy.ordiniepagamentiauth.repository;

import it.itsacademy.ordiniepagamentiauth.exception.NotFoundException;
import it.itsacademy.ordiniepagamentiauth.model.ApiUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ApiUserRepository extends JpaRepository<ApiUser, UUID> {
    Optional<ApiUser> findByUsernameAndIsActiveTrue(String username);

    default ApiUser findByUsernameAndIsActiveTrueOrThrow(String username) {
        return findByUsernameAndIsActiveTrue(username)
                .orElseThrow(
                        () -> new NotFoundException("Non esiste un utente con username " + username)
                );
    }

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
