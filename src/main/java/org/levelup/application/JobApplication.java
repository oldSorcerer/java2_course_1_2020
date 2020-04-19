package org.levelup.application;

import org.hibernate.SessionFactory;
import org.levelup.application.dao.*;
import org.levelup.application.domain.JobListEntity;
import org.levelup.hibernate.JobSessionFactoryConfiguration;
import org.levelup.application.domain.UserEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class JobApplication {
    public static void main(String[] args) {

        SessionFactory factory = new JobSessionFactoryConfiguration().configure();

        CompanyDao companyDao = new CompanyDaoImpl(factory);
        CompanyLegalDetailsDao legalDetailsDao = new CompanyLegalDetailsDaoImpl(factory);

        companyDao.create("Company JobList", "0897865895", "SBp");
        Integer companyId = companyDao.findByEin("0897865895").getId();

        UserDao userDao = new UserDaoImpl(factory);
        UserEntity user = userDao.createUser("User1", "Last2", "0897865 4895", new ArrayList<>(Arrays.asList(
                "addres 1",
                "addres 2",
                "addres 3"
        )));

        PositionDao positionDao = new PositionDaoImpl(factory);
        Integer positionId = positionDao.createPosition("PO 8").getId();

        JobListDao jobListDao = new JobListDaoImpl(factory);
        jobListDao.createJobRecord(companyId, user.getId(), positionId, LocalDate.of(2019, 12, 4), null);

        JobListEntity jobRecord = jobListDao.findJobRecord(companyId, user.getId(), positionId);

        System.out.println(jobRecord.getCompany());
        System.out.println(jobRecord.getPosition());
        System.out.println(jobRecord.getUser());
        System.out.println(jobRecord.getStartDate());
        System.out.println(jobRecord.getEndDate());



        factory.close();

    }
}
