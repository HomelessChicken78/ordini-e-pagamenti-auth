package it.itsacademy.ordiniepagamentiauth.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor @NoArgsConstructor
public class LoginUser {
    @NotEmpty(message = "Il campo username non può essere vuoto")
    @NotNull(message = "Il campo username non può essere vuoto")
    private String username;

    @NotEmpty(message = "Il campo password non può essere vuoto")
    @NotNull(message = "Il campo password non può essere vuoto")
    private String password;
}
