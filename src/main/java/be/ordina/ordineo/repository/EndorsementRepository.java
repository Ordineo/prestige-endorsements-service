package be.ordina.ordineo.repository;

import be.ordina.ordineo.model.Endorsement;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * Created by SaFu on 26/04/2017.
 */
public interface EndorsementRepository extends CrudRepository <Endorsement, UUID> {
}
