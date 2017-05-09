package be.ordina.ordineo.exception;

import lombok.NoArgsConstructor;

/**
 * Created by SaFu on 4/05/2017.
 */
@NoArgsConstructor
public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
