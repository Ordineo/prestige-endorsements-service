package be.ordina.ordineo.repository;

import be.ordina.ordineo.model.EndorsementLike;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * Created by SaFu on 25/04/2017.
 */
//@RepositoryRestResource(exported = false)
public interface EndorsementLikeRepository extends CrudRepository<EndorsementLike, UUID> {
}
