package be.ordina.ordineo.repository;

import be.ordina.ordineo.model.Category;
import be.ordina.ordineo.model.Endorsement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

/**
 * Created by SaFu on 26/04/2017.
 */
//@RepositoryRestResource(exported = false)
public interface EndorsementRepository extends JpaRepository<Endorsement, UUID>, JpaSpecificationExecutor<Endorsement> {
    Page<Endorsement> findAllByGranterUsername(@Param("username") String username, @Param("pagerequest") Pageable pageable);
    Page<Endorsement> findAllByReceiverUsername(@Param("username") String username, @Param("pagerequest") Pageable pageable);
}
