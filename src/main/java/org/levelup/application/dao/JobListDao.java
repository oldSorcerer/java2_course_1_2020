package org.levelup.application.dao;

import org.levelup.application.domain.JobListEntity;

import java.time.LocalDate;

public interface JobListDao {

    JobListEntity createJobRecord(Integer companyId, Integer userId, Integer positionId,
                                  LocalDate startDate, LocalDate endDate);

    JobListEntity findJobRecord(Integer companyId, Integer userId, Integer positionId);
}
