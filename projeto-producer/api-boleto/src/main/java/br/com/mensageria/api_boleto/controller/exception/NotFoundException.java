package br.com.mensageria.api_boleto.controller.exception;

public class NotFoundException extends RuntimeException  {

    public NotFoundException(String message) {
        super(message);
    }
}
