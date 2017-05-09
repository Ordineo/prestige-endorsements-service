package be.ordina.ordineo.exception;

import java.security.InvalidParameterException;

/**
 * Created by SaFu on 4/05/2017.
 */
public class FieldEmptyException extends InvalidParameterException {
    public FieldEmptyException(String msg) {
        super(msg);
    }
}
