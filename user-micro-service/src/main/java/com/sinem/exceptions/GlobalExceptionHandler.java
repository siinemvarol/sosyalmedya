package com.sinem.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;

import static com.sinem.exceptions.ErrorType.BAD_REQUEST_ERROR;
import static com.sinem.exceptions.ErrorType.REGISTER_KULLANICIADI_KAYITLI;


@ControllerAdvice
@Slf4j  // loglama icin
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> hatalariYakalayanBenim(Exception ex){
        return new ResponseEntity<>(createErrorMessage(ex, ErrorType.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(UserException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> Java8StartExceptionHandler(UserException ex){
        return new ResponseEntity<>(createErrorMessage(ex, ex.getErrorType()), ex.getErrorType().getHttpStatus());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public final ResponseEntity<ErrorMessage> handleMessageNotReadableException(
            HttpMessageNotReadableException exception) {
        ErrorType errorType = BAD_REQUEST_ERROR;
        return new ResponseEntity<>(createErrorMessage(exception,errorType), errorType.getHttpStatus());
    }

    @ExceptionHandler(InvalidFormatException.class)
    @ResponseBody
    public final ResponseEntity<ErrorMessage> handleInvalidFormatException(
            InvalidFormatException exception) {
        ErrorType errorType = BAD_REQUEST_ERROR;
        return new ResponseEntity<>(createErrorMessage(exception,errorType), errorType.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public final ResponseEntity<ErrorMessage> handleMethodArgumentMisMatchException(
            MethodArgumentTypeMismatchException exception) {

        ErrorType errorType = BAD_REQUEST_ERROR;
        return new ResponseEntity<>(createErrorMessage(exception,errorType), errorType.getHttpStatus());
    }

    @ExceptionHandler(MissingPathVariableException.class)
    @ResponseBody
    public final ResponseEntity<ErrorMessage> handleMethodArgumentMisMatchException(
            MissingPathVariableException exception) {

        ErrorType errorType = BAD_REQUEST_ERROR;
        return new ResponseEntity<>(createErrorMessage(exception,errorType), errorType.getHttpStatus());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public final ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {

        ErrorType errorType = BAD_REQUEST_ERROR;
        List<String> fields = new ArrayList<>();
        exception
                .getBindingResult()
                .getFieldErrors()
                .forEach(e -> fields.add(e.getField() + ": " + e.getDefaultMessage()));
        ErrorMessage errorMessage = createErrorMessage(exception,errorType);
        errorMessage.setFields(fields);
        return new ResponseEntity<>(errorMessage, errorType.getHttpStatus());
    }

    /**
     * Tum hatalar belli bir metod uzerinde gectigi icin ek kodlamalar yapmadan
     * tek bir yerden olusan hatalarin loglanmasi ve veritabanina kayit edilmesini
     * kolaylastirir.
     *
     */
    private ErrorMessage createErrorMessage(Exception ex, ErrorType errorType){
        System.out.println("Hata oluştu...." +
                System.currentTimeMillis() + " - " + ex.toString()
        );
        return ErrorMessage.builder()
                .message(errorType.getMessage())
                .code(errorType.getCode())
                .build();
    }
}
