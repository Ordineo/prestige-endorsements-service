package be.ordina.ordineo.config;

import be.ordina.ordineo.model.Category;
import be.ordina.ordineo.repository.CategoryRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

/**
 * Created by SaFu on 26/04/2017.
 */
@Configuration
public class RestRepositoryConfig extends RepositoryRestConfigurerAdapter {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.withEntityLookup().forValueRepository(CategoryRepository.class, Category::getName, CategoryRepository::findByName);
    }
}
