package it.itsacademy.ordiniepagamentiauth.service;

import it.itsacademy.ordiniepagamentiauth.dto.*;

public interface AuthService {
    JwtToken signUp(Signup dto);

    JwtToken login(LoginUser dto);

    UserInformationDTO whoAmI(String bearerToken);
}
