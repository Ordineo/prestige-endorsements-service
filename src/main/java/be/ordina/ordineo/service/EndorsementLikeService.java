package be.ordina.ordineo.service;

import be.ordina.ordineo.exception.EntityNotFoundException;
import be.ordina.ordineo.model.Endorsement;
import be.ordina.ordineo.model.EndorsementLike;
import be.ordina.ordineo.repository.EndorsementLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

/**
 * Created by SaFu on 4/05/2017.
 */
@Service
public class EndorsementLikeService {

    @Autowired
    EndorsementLikeRepository endorsementLikeRepository;
    @Autowired
    EndorsementService endorsementService;

    public List<EndorsementLike> saveAll(List<EndorsementLike> endorsementLikes) {
        List<EndorsementLike> savedEndorsementLikes = endorsementLikeRepository.save(endorsementLikes);
        return savedEndorsementLikes;
    }

    public void delete(EndorsementLike endorsementLike) {
        endorsementLikeRepository.delete(endorsementLike);
    }

    public Page<EndorsementLike> findAll(Pageable pageable) {
        return endorsementLikeRepository.findAll(pageable);
    }

    public Page<EndorsementLike> findByGranterUsername(String username, Pageable pageable) {
        return endorsementLikeRepository.findAllByGranterUsername(username, pageable);
    }

    public Page<EndorsementLike> findByEndorsementUuid(UUID uuid, Pageable pageable) {
        return endorsementLikeRepository.findAllByEndorsementUuid(uuid, pageable);
    }

    @Transactional
    public EndorsementLike saveWithEndorsementUuid(EndorsementLike endorsementLike) {
        // TODO: NEED TO CHECK IF GRANTER IS IN DATABASE
        UUID uuid = endorsementLike.getEndorsement().getUuid();
        Endorsement endorsement = endorsementService.findByUuid(uuid);

        endorsementLike.setEndorsement(endorsement);
        return endorsementLikeRepository.save(endorsementLike);
    }

    public EndorsementLike findByUuid(UUID uuid) {
        return endorsementLikeRepository.findOne(uuid);
    }
}
