package it.itsacademy.ordiniepagamentiauth.controller;

import it.itsacademy.ordiniepagamentiauth.dto.UserInformationDTO;
import it.itsacademy.ordiniepagamentiauth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// NOTARE: Siccome l'endpoint è /internal e il gateway non riconosce /internal, questo endpoint non è accessibile a
// partire dal gateway. Tuttavia è comunque accessibile attraverso la rete interna di docker
@RestController @RequestMapping("/internal/auth")
@RequiredArgsConstructor
public class InternalController {
    private final AuthService authService;
    private static final String json = "application/json";

    @GetMapping(path = "/{username}", produces = json)
    public UserInformationDTO searchApiUser(@PathVariable String username) {
        return authService.searchApiUser(username);
    }
}
