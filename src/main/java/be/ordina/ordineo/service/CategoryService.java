package be.ordina.ordineo.service;

import be.ordina.ordineo.exception.EntityNotFoundException;
import be.ordina.ordineo.model.Category;
import be.ordina.ordineo.model.Endorsement;
import be.ordina.ordineo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by SaFu on 4/05/2017.
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private EndorsementService endorsementService;

    public Category findByName(String categoryName) {
        Category category = categoryRepository.findByName(categoryName);
        if (category == null)
            throw new EntityNotFoundException("Category does not exist!");
        return category;
    }

    public Category findById(Long id) {
        Category category = categoryRepository.findById(id);
        if (category == null)
            throw new EntityNotFoundException("Category does not exist!");
        return category;
    }

    public List<Category> saveAll(List<Category> categories) {
        List<Category> savedCategories = categoryRepository.save(categories);
        return savedCategories;
    }

    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Transactional
    public void delete(Long id) {
        Category category = findById(id);

        if (!category.getEndorsements().isEmpty())
            unlinkEndorsement(category);

        categoryRepository.delete(category);
    }

    @Transactional
    private void unlinkEndorsement(Category category) {
        List<Endorsement> endorsements = category.getEndorsements();
        endorsements.forEach(endorsement -> {
            if(endorsement.getCategories().contains(category))
                endorsement.getCategories().remove(category);
        });
        category.getEndorsements().removeAll(endorsements);
        endorsementService.saveAll(endorsements);
    }
}
