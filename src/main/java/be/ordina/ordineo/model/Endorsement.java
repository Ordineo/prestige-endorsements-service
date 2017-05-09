package be.ordina.ordineo.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * Created by SaFu on 25/04/2017.
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
@Table(name = "ENDORSEMENTS")
public class Endorsement implements Serializable, Identifiable<UUID> {

    // Primary Key
    @Id
    @Column(name = "UUID")
    @Type(type = "uuid-char")
    private UUID uuid;

    //  Foreign Keys
//    @ManyToOne
//    private User grantor;
//
//    @ManyToOne
//    private User receiver;

    @Column(name = "GRANTER_USERNAME", length = 40)
    private String granterUsername;

    @Column(name = "RECEIVER_USERNAME", length = 40)
    private String receiverUsername;

    @ManyToMany
    @JoinTable(name = "CATEGORY_ASSIGNMENTS", joinColumns = {
            @JoinColumn(name = "ENDORSEMENT_UUID")
    }, inverseJoinColumns = {
            @JoinColumn(name = "CATEGORY_ID")
    })
    private List<Category> categories;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "endorsement")
    private List<EndorsementLike> endorsementLikes = new ArrayList<>();

    @Column(name = "SCORE")
    private int score;
    @Column(name = "REASON", length = 500)
    private String reason;
    @Column(name = "URL", length = 300)
    private String url;

    @Column(name = "CREATED", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    @Temporal(TemporalType.DATE)
    private Date created;

    @Override
    public UUID getId() {
        return uuid;
    }

    @PrePersist
    public void generateUuid() {
        if (getUuid() == null) {
            setUuid(UUID.randomUUID());
        }
    }

    //    public Endorsement(UUID granterUuid, UUID receiverUuid, int score, String url, String reason) {
//        this.uuid = UUID.randomUUID();
//        this.granterUsername = granterUuid;
//        this.receiverUuid = receiverUuid;
//        this.score = score;
//        this.url = url;
//        this.reason = reason;
//    }

}
