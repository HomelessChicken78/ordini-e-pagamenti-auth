package it.itsacademy.ordiniepagamentiauth.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class ApiUser {
    @Id @GeneratedValue(strategy = GenerationType.UUID) private UUID userId;
    @Column(nullable = false) private boolean isActive;
    @Column(nullable = false, unique = true) private String username;
    @Column(nullable = false) private String password;
}
