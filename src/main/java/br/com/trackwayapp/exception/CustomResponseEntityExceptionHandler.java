package br.com.trackwayapp.exception;

import br.com.trackwayapp.dto.response.DefaultPageableResponseDto;
import br.com.trackwayapp.dto.response.ZipCodeResponseDto;
import br.com.trackwayapp.enums.StatusEnum;
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

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<DefaultPageableResponseDto<String>> handleAllExceptions() {
        DefaultPageableResponseDto<String> response = new DefaultPageableResponseDto<>();
        response.setStatus(StatusEnum.ERROR);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(ZipCodeNotFoundException.class)
    public final ResponseEntity<ZipCodeResponseDto> handleZipCodeNotFoundException(ZipCodeNotFoundException exception) {
        ZipCodeResponseDto response = new ZipCodeResponseDto();
        response.setMessage(exception.getMessage());
        response.setStatus(StatusEnum.ERROR);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}