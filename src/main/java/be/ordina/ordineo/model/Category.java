package be.ordina.ordineo.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * Created by SaFu on 28/02/2017.
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
@Table(name = "CATEGORIES" ,uniqueConstraints={
        @UniqueConstraint(columnNames = "ID"),
        @UniqueConstraint(columnNames = "NAME")})
public class Category implements Serializable, Identifiable<Long> {

    // Primary Key
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "categories")
    private List<Endorsement> endorsements;

    @Column(name = "NAME", length = 60)
    private String name;

    public Category(String name) {
        this.name = name;
    }

}
