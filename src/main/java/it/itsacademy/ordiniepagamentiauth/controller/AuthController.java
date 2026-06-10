package it.itsacademy.ordiniepagamentiauth.controller;

import it.itsacademy.ordiniepagamentiauth.dto.*;
import it.itsacademy.ordiniepagamentiauth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final String json = "application/json";
    private final AuthService authService;

    @PostMapping(path = "/login", produces = json, consumes = json)
    public JwtToken login(@Valid @RequestBody LoginUser dto) {
        return authService.login(dto);
    }

    @PostMapping(path = "/signup", produces = json, consumes = json)
    public JwtToken signup(@Valid @RequestBody Signup dto) {
        return authService.signUp(dto);
    }

    @GetMapping(path = "/me", produces = json)
    public UserInformationDTO whoAmI(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String bearerToken) {
        return authService.whoAmI(bearerToken);
    }

    @GetMapping(path = "health")
    public void health() {}
}
