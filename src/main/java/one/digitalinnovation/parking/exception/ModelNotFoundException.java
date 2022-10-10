package one.digitalinnovation.parking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
public class ModelNotFoundException extends RuntimeException {
    public ModelNotFoundException(String model){
        super("Model name wasn't found, please try again with correct Info...");
    }
}
