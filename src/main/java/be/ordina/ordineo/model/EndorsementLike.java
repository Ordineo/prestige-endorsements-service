package be.ordina.ordineo.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * Created by SaFu on 3/03/2017.
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
@Table(name = "ENDORSEMENT_LIKES")
public class EndorsementLike {

    // Primary Key
    @Id
    @Column(name = "UUID")
    @Type(type = "uuid-char")
    private UUID uuid;

    @Column(name = "GRANTER_USERNAME")
    private String granterUsername;

    @ManyToOne
    private Endorsement endorsement;

    @Column(name = "REASON", length = 500)
    private String reason;
    @Column(name = "CREATED", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    @Temporal(TemporalType.DATE)
    private Date created;

//    public EndorsementLike(/*User user, */UUID granterUuid, String reason) {
////        this.user = user;
//        this.uuid = UUID.randomUUID();
//        this.granterUuid = granterUuid;
//        this.reason = reason;
//    }

}
