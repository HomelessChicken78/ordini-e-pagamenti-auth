package it.itsacademy.ordiniepagamentiauth.exception;

import it.itsacademy.ordiniepagamentiauth.exception.dto.GeneralErrorResponseDTO;
import it.itsacademy.ordiniepagamentiauth.exception.dto.ValidationErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<GeneralErrorResponseDTO> error401(UnauthorizedException err401) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new GeneralErrorResponseDTO(err401.getMessage(), 401));
    }

    @ExceptionHandler
    public ResponseEntity<GeneralErrorResponseDTO> error404Handler(NotFoundException err404) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND) // Usando .notFound non posso mettere un body con .body perché non ritorna
                // un BodyBuilder (usato per il body) ma un HeadersBuilder (usato per gli headers)
                .body(new GeneralErrorResponseDTO(err404.getMessage(), 404));
    }

    @ExceptionHandler
    public ResponseEntity<GeneralErrorResponseDTO> error409(ConflictException err409) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new GeneralErrorResponseDTO(err409.getMessage(), 409));
    }

    @ExceptionHandler
    public ResponseEntity<ValidationErrorResponseDTO> errorValidationHandler(MethodArgumentNotValidException exceptionRaised) {
        ValidationErrorResponseDTO responseDTO = new ValidationErrorResponseDTO(
                exceptionRaised.getFieldErrors()
                        .stream()
                        .collect(Collectors.toMap(
                                FieldError::getField,
                                err -> err.getDefaultMessage() != null ? err.getDefaultMessage() : "missing error message", // Evita i null nel caso in cui non ci sia un messaggio
                                (existing, replacement) -> existing // Prendi sempre quello già esistente in caso field duplicati
                        ))
        );

        return ResponseEntity
                .badRequest()
                .body(responseDTO);
    }
}
