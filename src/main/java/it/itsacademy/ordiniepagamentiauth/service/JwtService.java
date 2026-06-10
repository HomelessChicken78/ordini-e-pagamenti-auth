package it.itsacademy.ordiniepagamentiauth.service;

import it.itsacademy.ordiniepagamentiauth.dto.JwtToken;

public interface JwtService {
    JwtToken generateToken(String user);

    String extractUsername(String jwt);
}
