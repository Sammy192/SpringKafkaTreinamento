package br.com.rocha.apiboleto.services.exceptions;

public class ApplicationException extends RuntimeException  {

    public ApplicationException(String message) {
        super(message);
    }
}
