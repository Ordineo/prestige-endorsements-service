package be.ordina.ordineo.resourceassembler;

import be.ordina.ordineo.model.Endorsement;
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
public class EndorsementResourceAssembler implements ResourceAssembler<Endorsement, Resource<Endorsement>> {

    @Autowired
    private EntityLinks entityLinks;

    public EndorsementResourceAssembler() {
    }

    @Override
    public Resource<Endorsement> toResource(Endorsement entity) {
        Resource<Endorsement> endorsementResource = new Resource<>(entity);
        endorsementResource.add(linkToSingleResource(entity));
        return endorsementResource;
    }

    private Link linkToSingleResource(Endorsement endorsement) {
        Link link = entityLinks.linkToSingleResource(Endorsement.class, endorsement.getUuid());
        return link;
    }

}
