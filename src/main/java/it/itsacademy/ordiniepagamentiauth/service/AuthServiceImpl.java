package it.itsacademy.ordiniepagamentiauth.service;

import it.itsacademy.ordiniepagamentiauth.dto.*;
import it.itsacademy.ordiniepagamentiauth.exception.*;
import it.itsacademy.ordiniepagamentiauth.mapper.ApiUserMapper;
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
    private final ApiUserMapper mapper;

    @Override
    public JwtToken signUp(Signup dto) {
        if (userRepository.existsByUsername(dto.getUsername()))
            throw new ConflictException("Esiste già un utente con l'username " + dto.getUsername());
        if (userRepository.existsByEmail(dto.getEmail()))
            throw new ConflictException("Email già in uso");

        ApiUser utenteDaSalvare = ApiUser.builder()
                .isActive(true)
                .username(dto.getUsername())
                .password(encoder.encode(dto.getPassword()))
                .email(dto.getEmail()).build();

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
    public UserInformationDTO whoAmI(String bearerToken) {
        if (bearerToken == null || !bearerToken.startsWith("Bearer "))
            throw new UnauthorizedException("Non è stato effettuato alcun accesso o l'accesso non è valido");

        String userFromJwt = jwtService.extractUsername(bearerToken.substring(7));
        ApiUser found = userRepository.findByUsernameAndIsActiveTrueOrThrow(userFromJwt);
        return mapper.toDTO(found);
    }
}
