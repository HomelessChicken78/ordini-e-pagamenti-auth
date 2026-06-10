package it.itsacademy.ordiniepagamentiauth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class Signup {
    @NotEmpty(message = "Il campo username non può essere vuoto")
    @NotNull(message = "Il campo username non può essere vuoto")
    private String username;

    @NotEmpty(message = "Il campo password non può essere vuoto")
    @NotNull(message = "Il campo password non può essere vuoto")
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-.]).{8,}$",
            message = "La password deve contenere almeno 8 caratteri, una lettera maiuscola, una lettera minuscola, un numero e un carattere speciale.")
    private String password;

    @NotEmpty(message = "Il campo email non può essere vuoto")
    @NotNull(message = "Il campo email non può essere vuoto")
    @Email(message = "Formato email non valido")
    private String email;
}
