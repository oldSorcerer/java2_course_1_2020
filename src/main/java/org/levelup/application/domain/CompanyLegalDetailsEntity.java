package org.levelup.application.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "company_legal_details")
public class CompanyLegalDetailsEntity {

    @OneToOne(mappedBy = "legalDetails")
    private CompanyEntity company;

    @Id
    @Column(name ="company_id")
    private Integer companyId;
    @Column(name = "bank_name", nullable = false)
    private String bankName;
    @Column(name = "bic", nullable = false)
    private String BIC;

    public CompanyLegalDetailsEntity(Integer companyId, String bankName, String BIC) {
        this.companyId = companyId;
        this.bankName = bankName;
        this.BIC = BIC;
    }

}
