package be.ordina.ordineo.resourceassembler;

import be.ordina.ordineo.model.EndorsementLike;
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
public class EndorsementLikeResourceAssembler implements ResourceAssembler<EndorsementLike, Resource<EndorsementLike>> {

    @Autowired
    private EntityLinks entityLinks;

    public EndorsementLikeResourceAssembler() {
    }

    @Override
    public Resource<EndorsementLike> toResource(EndorsementLike entity) {
        Resource<EndorsementLike> endorsementLikeResource = new Resource<>(entity);
        endorsementLikeResource.add(linkToSingleResource(entity));
        return endorsementLikeResource;
    }

    private Link linkToSingleResource(EndorsementLike endorsement) {
        Link link = entityLinks.linkToSingleResource(EndorsementLike.class, endorsement.getUuid());
        return link;
    }
}
