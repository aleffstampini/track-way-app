package br.com.trackwayapp.exception;

import br.com.trackwayapp.dto.response.PostalCodeResponseDto;
import br.com.trackwayapp.dto.response.StringDefaultPageableResponseDto;
import br.com.trackwayapp.enums.StatusEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
@Slf4j
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PostalCodeNotFoundException.class)
    public final ResponseEntity<PostalCodeResponseDto> handlePostalCodeNotFoundException(PostalCodeNotFoundException exception) {
        PostalCodeResponseDto response = new PostalCodeResponseDto();
        response.setMessage(exception.getMessage());
        response.setStatus(StatusEnum.ERROR);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public final ResponseEntity<StringDefaultPageableResponseDto> handleEntityNotFoundException(EntityNotFoundException exception) {
        StringDefaultPageableResponseDto response = new StringDefaultPageableResponseDto(exception.getMessage());
        response.setStatus(StatusEnum.ERROR);
        response.setMessage(exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(JsonProcessingException.class)
    public final ResponseEntity<StringDefaultPageableResponseDto> handleJsonProcessingException(JsonProcessingException exception) {
        StringDefaultPageableResponseDto response = new StringDefaultPageableResponseDto(exception.getMessage());
        response.setStatus(StatusEnum.ERROR);
        response.setMessage(exception.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}