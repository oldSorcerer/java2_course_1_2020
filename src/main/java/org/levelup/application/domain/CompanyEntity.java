package org.levelup.application.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "companies")
public class CompanyEntity {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String ein;
    @Column(nullable = false)
    private String address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private CompanyLegalDetailsEntity legalDetails;

    @ManyToMany
    @JoinTable(
            name = "company_positions",
            joinColumns =@JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "position_id")
    )
    private List<PositionEntity> positions;

    @Override
    public String toString() {
        return "CompanyEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ein='" + ein + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
