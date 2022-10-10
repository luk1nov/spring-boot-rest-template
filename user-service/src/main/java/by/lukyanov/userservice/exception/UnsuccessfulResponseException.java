package by.lukyanov.userservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UnsuccessfulResponseException extends RuntimeException{
    private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    public UnsuccessfulResponseException(HttpStatus status) {
        this.status = status;
    }

    public UnsuccessfulResponseException(String message) {
        super(message);
    }

    public UnsuccessfulResponseException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
}
