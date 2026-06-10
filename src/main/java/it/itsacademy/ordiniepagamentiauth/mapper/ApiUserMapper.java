package it.itsacademy.ordiniepagamentiauth.mapper;

import it.itsacademy.ordiniepagamentiauth.dto.UserInformationDTO;
import it.itsacademy.ordiniepagamentiauth.model.ApiUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ApiUserMapper {
    UserInformationDTO toDTO(ApiUser entity);
}
