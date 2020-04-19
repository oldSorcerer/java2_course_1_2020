package org.levelup.application.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@Table(name = "job_list")
public class JobListEntity {

    @EmbeddedId
    private JobListId id;

    @ManyToOne
    @MapsId("company_id")
    private CompanyEntity company;
    @ManyToOne
    @MapsId("position_id")
    private PositionEntity position;
    @ManyToOne
    @MapsId("user_id")
    private UserEntity user;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;

    public JobListEntity(JobListId id, LocalDate startDate) {
        this.id = id;
        this.startDate = startDate;

    }

}
