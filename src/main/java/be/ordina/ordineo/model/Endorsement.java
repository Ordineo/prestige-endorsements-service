package be.ordina.ordineo.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

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
public class Endorsement implements Serializable {

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

    @Column(name = "GRANTER_UUID")
    private UUID granterUuid;

    @Column(name = "RECEIVER_UUID")
    private UUID receiverUuid;

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

    public Endorsement(UUID granterUuid, UUID receiverUuid, int score, String url, String reason) {
        this.uuid = UUID.randomUUID();
        this.granterUuid = granterUuid;
        this.receiverUuid = receiverUuid;
        this.score = score;
        this.url = url;
        this.reason = reason;
    }

}