package by.lukyanov.departmentservice.exception;

import lombok.Getter;

@Getter
public class ExternalApiException extends RuntimeException{
    private int statusCode;

    public ExternalApiException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
