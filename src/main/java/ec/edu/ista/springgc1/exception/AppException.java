package ec.edu.ista.springgc1.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class AppException extends RuntimeException{

    private HttpStatus status;
    private String message;
}
