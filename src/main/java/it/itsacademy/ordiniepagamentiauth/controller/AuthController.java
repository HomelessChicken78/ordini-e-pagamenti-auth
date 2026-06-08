package it.itsacademy.ordiniepagamentiauth.controller;

import it.itsacademy.ordiniepagamentiauth.dto.JwtToken;
import it.itsacademy.ordiniepagamentiauth.dto.LoginUser;
import it.itsacademy.ordiniepagamentiauth.dto.Signup;
import it.itsacademy.ordiniepagamentiauth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

    @GetMapping(path = "health")
    public void health() {}
}
