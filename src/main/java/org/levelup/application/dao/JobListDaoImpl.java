package org.levelup.application.dao;

import org.hibernate.SessionFactory;
import org.levelup.application.domain.JobListEntity;
import org.levelup.application.domain.JobListId;

import java.time.LocalDate;

public class JobListDaoImpl extends AbstractDao implements JobListDao {

    public JobListDaoImpl(SessionFactory factory) {
        super(factory);
    }

    @Override
    public JobListEntity createJobRecord(Integer companyId, Integer userId, Integer positionId,
                                         LocalDate startDate, LocalDate endDate) {
        return runInTransaction(session -> {
            JobListId id = new JobListId(companyId, positionId, userId);
            JobListEntity jobRecord = new JobListEntity(id, startDate);

            if (endDate != null)
                jobRecord.setEndDate(endDate);

            session.persist(jobRecord);
            return jobRecord;
        });
    }

    @Override
    public JobListEntity findJobRecord(Integer companyId, Integer userId, Integer positionId) {
        return runWithoutTransaction(session ->
                session.get(JobListEntity.class, new JobListId(companyId, positionId, userId)));
    }


}
