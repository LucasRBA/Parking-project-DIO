package one.digitalinnovation.parking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_ACCEPTABLE)
public class InfoNotInformedException extends RuntimeException {
    public InfoNotInformedException(String ownerName, String model) {
        super("Some of the required info wasn't provided correctly, please check Owner name: " + ownerName + " and model of your vehicle: " + model);
    }
}
