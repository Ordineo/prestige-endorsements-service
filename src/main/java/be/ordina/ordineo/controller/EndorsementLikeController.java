package be.ordina.ordineo.controller;

import be.ordina.ordineo.exception.FieldEmptyException;
import be.ordina.ordineo.model.EndorsementLike;
import be.ordina.ordineo.resourceassembler.EndorsementLikeResourceAssembler;
import be.ordina.ordineo.service.EndorsementLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.UUID;

/**
 * Created by SaFu on 4/05/2017.
 */
@RestController
@ExposesResourceFor(EndorsementLike.class)
@RequestMapping("/likes")
public class EndorsementLikeController {

    private final EndorsementLikeService endorsementLikeService;
    private final EndorsementLikeResourceAssembler endorsementLikeResourceAssembler;

    @Autowired
    public EndorsementLikeController(EndorsementLikeService endorsementLikeService, EndorsementLikeResourceAssembler endorsementLikeResourceAssembler) {
        this.endorsementLikeService = endorsementLikeService;
        this.endorsementLikeResourceAssembler = endorsementLikeResourceAssembler;
    }

    // GET METHODS

    @RequestMapping(value = "", method = RequestMethod.GET, produces = {"application/json", "application/hal+json"})
    public HttpEntity<PagedResources<Resource<EndorsementLike>>> findAll(Pageable pageable, PagedResourcesAssembler<EndorsementLike> assembler) {
        Page<EndorsementLike> endorsementLikes = endorsementLikeService.findAll(pageable);
        if(pageable != null ){
            pageable.getPageNumber();
            pageable.getPageSize();
        }
        PagedResources<Resource<EndorsementLike>> pagedResources = assembler.toResource(endorsementLikes, endorsementLikeResourceAssembler);
        return  new ResponseEntity<>(pagedResources, HttpStatus.OK);
    }

    @RequestMapping(value = "/search/findByGranterUsername", method = RequestMethod.GET, produces = {"application/json", "application/hal+json"})
    public HttpEntity<PagedResources<Resource<EndorsementLike>>> findByGranterUsername(@RequestParam("username") @Valid String username,
                                                                                   Pageable pageable, PagedResourcesAssembler<EndorsementLike> assembler) {
        Page<EndorsementLike> endorsementLikes = endorsementLikeService.findByGranterUsername(username, pageable);
        if(pageable != null ){
            pageable.getPageNumber();
            pageable.getPageSize();
        }
        PagedResources<Resource<EndorsementLike>> pagedResources = assembler.toResource(endorsementLikes, endorsementLikeResourceAssembler);
        return new ResponseEntity<>(pagedResources, HttpStatus.OK);
    }

    // TODO: LOOK AT THIS
//    @RequestMapping(value = "/search/findByEndorsementUuid", method = RequestMethod.GET, produces = {"application/json", "application/hal+json"})
//    public HttpEntity<PagedResources<Resource<EndorsementLike>>> findByEndorsementUuid(@RequestParam("uuid") @Valid String uuid,
//                                                                                       Pageable pageable, PagedResourcesAssembler<EndorsementLike> assembler) {
//        Page<EndorsementLike> endorsementLikes = endorsementLikeService.findByEndorsementUuid(UUID.fromString(uuid), pageable);
//        if(pageable != null ){
//            pageable.getPageNumber();
//            pageable.getPageSize();
//        }
//        PagedResources<Resource<EndorsementLike>> pagedResources = assembler.toResource(endorsementLikes, endorsementLikeResourceAssembler);
//        return new ResponseEntity<>(pagedResources, HttpStatus.OK);
//    }

    // POST METHODS

    @RequestMapping(value = "",method = RequestMethod.POST, consumes = {"application/json","application/hal+json"},
            produces = {"application/json","application/hal+json"})//question are we gonna use hibernate validator ---> then i can use @Valid or not??,,
    public ResponseEntity<Void> save(@RequestBody @Valid EndorsementLike endorsementLike, UriComponentsBuilder ucBuilder  ){
        if(endorsementLike.getEndorsement() != null)
            endorsementLike = endorsementLikeService.saveWithEndorsementUuid(endorsementLike);
        else
            throw new FieldEmptyException("No endorsement is given!");

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/likes/{uuid}").buildAndExpand((endorsementLike.getUuid())).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    // DELETE METHOD

    @RequestMapping(value = "/{uuid}",method = RequestMethod.DELETE , produces = {"application/hal+json", "application/json"})
    public ResponseEntity delete(@PathVariable("uuid") String uuid){
        endorsementLikeService.delete(endorsementLikeService.findByUuid(UUID.fromString(uuid)));
        return ResponseEntity.noContent().build();
    }

}
