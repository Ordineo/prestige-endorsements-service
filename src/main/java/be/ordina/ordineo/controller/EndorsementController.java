package be.ordina.ordineo.controller;

import be.ordina.ordineo.exception.FieldEmptyException;
import be.ordina.ordineo.model.Endorsement;
import be.ordina.ordineo.resourceassembler.EndorsementResourceAssembler;
import be.ordina.ordineo.service.EndorsementService;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

/**
 * Created by SaFu on 4/05/2017.
 */
@RestController
@ExposesResourceFor(Endorsement.class)
@RequestMapping("/endorsements")
public class EndorsementController {

    private final EndorsementService endorsementService;
    private final EndorsementResourceAssembler endorsementResourceAssembler;

    @Autowired
    public EndorsementController(EndorsementService endorsementService, EndorsementResourceAssembler endorsementResourceAssembler) {
        this.endorsementService = endorsementService;
        this.endorsementResourceAssembler = endorsementResourceAssembler;
    }

    // GET METHODS

    @RequestMapping(value = "", method = RequestMethod.GET, produces = {"application/json", "application/hal+json"})
    public HttpEntity<PagedResources<Resource<Endorsement>>> findAll(Pageable pageable, PagedResourcesAssembler<Endorsement> assembler) {
        Page<Endorsement> endorsements = endorsementService.findAll(pageable);
        if(pageable != null ){
            pageable.getPageNumber();
            pageable.getPageSize();
        }
        PagedResources<Resource<Endorsement>> pagedResources = assembler.toResource(endorsements, endorsementResourceAssembler);
        return  new ResponseEntity<>(pagedResources, HttpStatus.OK);
    }

    @RequestMapping(value = "/{uuid}", method = RequestMethod.GET, produces = {"application/json", "application/hal+json"})
    public ResponseEntity findByUuid(@PathVariable("uuid") String uuid) {
        System.out.println(UUID.fromString(uuid));
        Endorsement endorsement = endorsementService.findByUuid(UUID.fromString(uuid));
        return new ResponseEntity<>(endorsementResourceAssembler.toResource(endorsement), HttpStatus.OK);
    }

    @RequestMapping(value = "/search/findByGranterUsername", method = RequestMethod.GET, produces = {"application/json", "application/hal+json"})
    public HttpEntity<PagedResources<Resource<Endorsement>>> findByGranterUsername(@RequestParam("username") @Valid String username,
                                                                                   Pageable pageable, PagedResourcesAssembler<Endorsement> assembler) {
        Page<Endorsement> endorsements = endorsementService.findByGranterUsername(username, pageable);
        if(pageable != null ){
            pageable.getPageNumber();
            pageable.getPageSize();
        }
        PagedResources<Resource<Endorsement>> pagedResources = assembler.toResource(endorsements, endorsementResourceAssembler);
        return new ResponseEntity<>(pagedResources, HttpStatus.OK);
    }

    @RequestMapping(value = "/search/findByReceiverUsername", method = RequestMethod.GET, produces = {"application/json", "application/hal+json"})
    public HttpEntity<PagedResources<Resource<Endorsement>>> findByReceiverUsername(@RequestParam("username") @Valid String username,
                                                                                   Pageable pageable, PagedResourcesAssembler<Endorsement> assembler) {
        Page<Endorsement> endorsements = endorsementService.findByReceiverUsername(username, pageable);
        if(pageable != null ){
            pageable.getPageNumber();
            pageable.getPageSize();
        }
        PagedResources<Resource<Endorsement>> pagedResources = assembler.toResource(endorsements, endorsementResourceAssembler);
        return new ResponseEntity<>(pagedResources, HttpStatus.OK);
    }

    @RequestMapping(value = "/search/findByCategory", method = RequestMethod.GET, produces = {"application/json", "application/hal+json"})
    public HttpEntity<PagedResources<Resource<Endorsement>>> findByCategory(@RequestParam("category") @Valid String category,
                                                                                   Pageable pageable, PagedResourcesAssembler<Endorsement> assembler) {
        Page<Endorsement> endorsements = endorsementService.findByCategory(category, pageable);
        if(pageable != null ){
            pageable.getPageNumber();
            pageable.getPageSize();
        }
        PagedResources<Resource<Endorsement>> pagedResources = assembler.toResource(endorsements, endorsementResourceAssembler);
        return new ResponseEntity<>(pagedResources, HttpStatus.OK);
    }

    // POST METHODS

    @RequestMapping(value = "",method = RequestMethod.POST, consumes = {"application/json","application/hal+json"},
            produces = {"application/json","application/hal+json"})//question are we gonna use hibernate validator ---> then i can use @Valid or not??,,
    public ResponseEntity<Void> save(@RequestBody @Valid Endorsement endorsement, UriComponentsBuilder ucBuilder  ){
        if(!endorsement.getCategories().isEmpty())
            endorsement = endorsementService.saveWithCategories(endorsement);
        else
            throw new FieldEmptyException("No categories are given!");

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/endorsements/{uuid}").buildAndExpand((endorsement.getUuid())).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    // PUT METHODS

    @RequestMapping(value="/{uuid}" , method = RequestMethod.PUT ,consumes = {"application/hal+json", "application/json"}, produces = {"application/hal+json", "application/json"})
    public ResponseEntity update(@PathVariable("uuid") String uuid , @RequestBody @Valid Endorsement endorsement, HttpServletRequest request)
            throws URISyntaxException {
        Endorsement updatedEndorsement = endorsementService.update(UUID.fromString(uuid) , endorsement);
        HttpHeaders httpHeaders = new HttpHeaders();
        URI uri = new URI(request.getRequestURL().append(updatedEndorsement.getId()).toString());
        httpHeaders.setLocation(uri);
        return new ResponseEntity( httpHeaders,HttpStatus.CREATED);
    }

    // DELETE METHOD

    @RequestMapping(value = "/{uuid}",method = RequestMethod.DELETE , produces = {"application/hal+json", "application/json"})
    public ResponseEntity delete(@PathVariable("uuid") String uuid){
        endorsementService.delete(UUID.fromString(uuid));
        return ResponseEntity.noContent().build();
    }

}
