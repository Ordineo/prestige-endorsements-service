package be.ordina.ordineo.resourceassembler;

import be.ordina.ordineo.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

/**
 * Created by SaFu on 4/05/2017.
 */
@Component
public class CategoryResourceAssembler implements ResourceAssembler<Category, Resource<Category>>{

    @Autowired
    private EntityLinks entityLinks;

    public CategoryResourceAssembler() {
    }

    @Override
    public Resource<Category> toResource(Category entity) {
        Resource<Category> categoryResource = new Resource<>(entity);
        categoryResource.add(linkToSingleResource(entity));
        return categoryResource;
    }

    private Link linkToSingleResource(Category category) {
        Link link = entityLinks.linkToSingleResource(Category.class, category.getId());
        return link;
    }
}
