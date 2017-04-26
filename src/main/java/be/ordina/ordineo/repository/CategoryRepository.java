package be.ordina.ordineo.repository;

import be.ordina.ordineo.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

/**
 * Created by SaFu on 6/03/2017.
 */
//@RepositoryRestResource(exported = false)
public interface CategoryRepository extends CrudRepository<Category, Long> {
    Category findByName(@Param("name") String name);
}