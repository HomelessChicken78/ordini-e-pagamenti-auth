package it.itsacademy.ordiniepagamentiauth.service;

import it.itsacademy.ordiniepagamentiauth.dto.*;
import it.itsacademy.ordiniepagamentiauth.exception.ConflictException;
import it.itsacademy.ordiniepagamentiauth.model.ApiUser;
import it.itsacademy.ordiniepagamentiauth.repository.ApiUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service @Transactional
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder encoder;
    private final ApiUserRepository userRepository;
    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    @Override
    public JwtToken signUp(Signup dto) {
        if (userRepository.existsByUsername(dto.getUsername())) throw new ConflictException("Esiste già un utente con l'username " + dto.getUsername());

        ApiUser utenteDaSalvare = ApiUser.builder().isActive(true).build();
        utenteDaSalvare.setUsername(dto.getUsername());
        utenteDaSalvare.setPassword(encoder.encode(dto.getPassword()));

        userRepository.save(utenteDaSalvare);

        return jwtService.generateToken(utenteDaSalvare.getUsername());
    }

    @Override
    public JwtToken login(LoginUser dto) {
        // NB: AuthenticationManager prende la password in chiaro poichè cripta la password in automatico.
        // In pratica pensa lui alla login
        authManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));

        userRepository.findByUsernameAndIsActiveTrueOrThrow(dto.getUsername());

        return jwtService.generateToken(dto.getUsername());
    }

    @Override
    public UserInformationDTO whoAmI() {
        return null;
    }
}
