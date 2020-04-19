package org.levelup.application.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class JobListId implements Serializable {

    @Column(name = "company_id")
    private Integer companyId;
    @Column(name = "position_id")
    private Integer positionId;
    @Column(name = "user_id")
    private Integer userId;

}
