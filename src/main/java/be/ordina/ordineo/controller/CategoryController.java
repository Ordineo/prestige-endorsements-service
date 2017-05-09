package be.ordina.ordineo.controller;

import be.ordina.ordineo.exception.FieldEmptyException;
import be.ordina.ordineo.model.Category;
import be.ordina.ordineo.resourceassembler.CategoryResourceAssembler;
import be.ordina.ordineo.service.CategoryService;
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
@ExposesResourceFor(Category.class)
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryResourceAssembler categoryResourceAssembler;

    @Autowired
    public CategoryController(CategoryService categoryService, CategoryResourceAssembler categoryResourceAssembler) {
        this.categoryService = categoryService;
        this.categoryResourceAssembler = categoryResourceAssembler;
    }

    // GET METHODS

    @RequestMapping(value = "", method = RequestMethod.GET, produces = {"application/json", "application/hal+json"})
    public HttpEntity<PagedResources<Resource<Category>>> findAll(Pageable pageable, PagedResourcesAssembler<Category> assembler) {
        Page<Category> categories = categoryService.findAll(pageable);
        if(pageable != null ){
            pageable.getPageNumber();
            pageable.getPageSize();
        }
        PagedResources<Resource<Category>> pagedResources = assembler.toResource(categories, categoryResourceAssembler);
        return new ResponseEntity<>(pagedResources, HttpStatus.OK);
    }

    // POST METHODS

    @RequestMapping(value = "",method = RequestMethod.POST, consumes = {"application/json","application/hal+json"},
            produces = {"application/json","application/hal+json"})//question are we gonna use hibernate validator ---> then i can use @Valid or not??,,
    public ResponseEntity<Void> save(@RequestBody @Valid Category category, UriComponentsBuilder ucBuilder  ){

        categoryService.save(category);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/category/{uuid}").buildAndExpand((category.getId())).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    // DELETE METHOD

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE , produces = {"application/hal+json", "application/json"})
    public ResponseEntity delete(@PathVariable("id") Long id){
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
