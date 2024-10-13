package br.com.trackwayapp.exception;

public class ZipCodeNotFoundException extends RuntimeException {
    public ZipCodeNotFoundException(String message) {
        super(message);
    }
}