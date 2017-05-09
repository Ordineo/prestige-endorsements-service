package be.ordina.ordineo.repository;

import be.ordina.ordineo.model.EndorsementLike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

/**
 * Created by SaFu on 25/04/2017.
 */
@RepositoryRestResource(exported = false)
public interface EndorsementLikeRepository extends JpaRepository<EndorsementLike, UUID> {
    Page<EndorsementLike> findAllByGranterUsername(@Param("username") String username, @Param("pagerequest") Pageable pageable);
    Page<EndorsementLike> findAllByEndorsementUuid(@Param("uuid") UUID uuid, @Param("pagerequest") Pageable pageable);
}
