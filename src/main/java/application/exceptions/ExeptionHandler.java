package application.exceptions;

import application.responces.MessageResponce;
import application.utils.Messages;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExeptionHandler {
    @NotNull
    private static final Logger LOGGER = LoggerFactory.getLogger(ExeptionHandler.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadCookieException.class)
    public ResponseEntity cookie() {
        LOGGER.info("400");
        return ResponseEntity.badRequest().body(new MessageResponce(Messages.BAD_COOKIE));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WrongTokenExeption.class)
    public ResponseEntity token() {
        LOGGER.info("400");
        return ResponseEntity.badRequest().body(new MessageResponce(Messages.BAD_COOKIE));
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AuthorizedExeption.class)
    public ResponseEntity authorized() {
        LOGGER.info("403");
        return new ResponseEntity<>(new MessageResponce(Messages.AUTHORIZED), HttpStatus.FORBIDDEN);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationExeption.class)
    public ResponseEntity validation(ValidationExeption ex) {
        LOGGER.info("400");
        return ResponseEntity.badRequest().body(ex.getValidation());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(UnauthorizedExeption.class)
    public ResponseEntity unauthorized() {
        LOGGER.info("403");
        return new ResponseEntity<>(new MessageResponce(Messages.NOT_AUTHORIZED), HttpStatus.FORBIDDEN);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(WrongAuthExeption.class)
    public ResponseEntity wrongAuth() {
        LOGGER.info("401");
        return new ResponseEntity<>(new MessageResponce(Messages.AUTHORIZATION_ERROR), HttpStatus.UNAUTHORIZED);
    }

}
