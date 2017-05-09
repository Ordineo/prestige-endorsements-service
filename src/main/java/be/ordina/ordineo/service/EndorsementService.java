package be.ordina.ordineo.service;

import be.ordina.ordineo.exception.EntityNotFoundException;
import be.ordina.ordineo.model.Category;
import be.ordina.ordineo.model.Endorsement;
import be.ordina.ordineo.model.EndorsementLike;
import be.ordina.ordineo.repository.EndorsementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

/**
 * Created by SaFu on 4/05/2017.
 */
@Service
public class EndorsementService {

    @Autowired
    private EndorsementRepository endorsementRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private EndorsementLikeService endorsementLikeService;

    public Page<Endorsement> findAll(Pageable pageable) {
        return endorsementRepository.findAll(pageable);
    }

    public Endorsement findByUuid(UUID uuid) {
        Endorsement endorsement = endorsementRepository.findOne(uuid);
        if (endorsement == null)
            throw new EntityNotFoundException("Endorsement does not exist!");
        return endorsement;
    }

    public Page<Endorsement> findByGranterUsername(String username, Pageable pageable) {
        return endorsementRepository.findAllByGranterUsername(username, pageable);
    }

    public Page<Endorsement> findByReceiverUsername(String username, Pageable pageable) {
        return endorsementRepository.findAllByReceiverUsername(username, pageable);
    }

    public Page<Endorsement> findByCategory(String name, Pageable pageable) {
        Category category = categoryService.findByName(name);
        if (category == null)
            throw new EntityNotFoundException("Category does not exist!");
        return new PageImpl<>(category.getEndorsements());
    }

    @Transactional
    public Endorsement saveWithCategories(Endorsement endorsement) {
        // TODO: Need to check if granter and receiver are in the Employees database
        List<Category> categories = endorsement.getCategories();
        categories.forEach(category -> {
            Category existingCategory = categoryService.findByName(category.getName());
            if (existingCategory == null)
                throw new EntityNotFoundException("Category does not exist!");
             category.setId(existingCategory.getId());
        });
        endorsement.setCategories(categories);
        return endorsementRepository.save(endorsement);
    }

    @Transactional
    public Endorsement update(UUID uuid, Endorsement endorsement) {
        Endorsement endorsementBeforeUpdate = findByUuid(uuid);
        checkFieldsBeforePUT(endorsementBeforeUpdate, endorsement);
        return endorsementRepository.save(endorsement);
    }

    private Endorsement checkFieldsBeforePUT(Endorsement endorsementBeforeUpdate, Endorsement endorsement) {
        if (endorsement.getGranterUsername() != null)
            endorsementBeforeUpdate.setGranterUsername(endorsement.getGranterUsername());
        if (endorsement.getReceiverUsername() != null)
            endorsementBeforeUpdate.setReceiverUsername(endorsement.getReceiverUsername());

        if (!endorsement.getCategories().equals(endorsementBeforeUpdate.getCategories().size())) {
            endorsementBeforeUpdate.setCategories(endorsement.getCategories());
        }

        if (endorsement.getScore() != 0)
            endorsementBeforeUpdate.setScore(endorsement.getScore());
        if (endorsement.getReason() != null)
            endorsementBeforeUpdate.setReason(endorsement.getReason());
        if (endorsement.getUrl() != null)
            endorsementBeforeUpdate.setUrl(endorsement.getUrl());
        saveWithCategories(endorsementBeforeUpdate);
        return endorsementBeforeUpdate;
    }

    // TODO: PATCH CHECK
//    private Endorsement checkFieldsBeforePATCH(Endorsement endorsementBeforeUpdate, Endorsement endorsement) {
//        if (endorsement.getGranterUsername() != null && endorsement.getGranterUsername() != endorsementBeforeUpdate.getGranterUsername())
//            endorsementBeforeUpdate.setGranterUsername(endorsement.getGranterUsername());
//        if (endorsement.getReceiverUsername() != null)
//            endorsementBeforeUpdate.setReceiverUsername(endorsement.getReceiverUsername());
//        if (endorsement.getCategories().size() != endorsement.getCategories().size())
//            endorsementBeforeUpdate.setCategories(endorsement.getCategories());
//        if (endorsement.getScore() != 0)
//            endorsementBeforeUpdate.setScore(endorsement.getScore());
//        if (endorsement.getReason() != null)
//            endorsementBeforeUpdate.setReason(endorsement.getReason());
//        if (endorsement.getUrl() != null)
//            endorsementBeforeUpdate.setUrl(endorsement.getUrl());
//        saveWithCategories(endorsementBeforeUpdate);
//        return endorsementBeforeUpdate;
//    }

    @Transactional
    public void delete(UUID uuid) {
        Endorsement endorsement = findByUuid(uuid);

        if(!endorsement.getCategories().isEmpty())
            unlinkCategory(endorsement);
        if (!endorsement.getEndorsementLikes().isEmpty())
            unlinkEndorsementLike(endorsement);

        endorsementRepository.delete(endorsement.getUuid());
    }

    @Transactional
    private void unlinkCategory(Endorsement endorsement) {
        List<Category> categories = endorsement.getCategories();
        categories.forEach(category -> {
            if (category.getEndorsements().contains(endorsement))
                category.getEndorsements().remove(endorsement);
        });
        endorsement.getCategories().removeAll(categories);
        categoryService.saveAll(categories);
    }
    @Transactional
    private void unlinkEndorsementLike(Endorsement endorsement) {
        endorsement.getEndorsementLikes()
                .forEach(endorsementLike -> endorsementLikeService.delete(endorsementLike));
    }

    public List<Endorsement> saveAll(List<Endorsement> endorsements) {
        List<Endorsement> savedEndorsements = endorsementRepository.save(endorsements);
        return savedEndorsements;
    }
}
