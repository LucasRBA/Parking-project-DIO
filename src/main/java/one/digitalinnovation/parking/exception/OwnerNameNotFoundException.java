package one.digitalinnovation.parking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class OwnerNameNotFoundException extends RuntimeException{


    public OwnerNameNotFoundException(String ownerName) {
        super("Owner Name wasn't found, please try again with correct Info...");
    }
}
