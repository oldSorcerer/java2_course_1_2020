package org.levelup.application.dao;

import org.hibernate.SessionFactory;
import org.levelup.application.domain.CompanyLegalDetailsEntity;

import java.util.Collection;

public class CompanyLegalDetailsDaoImpl extends AbstractDao implements CompanyLegalDetailsDao {

    public CompanyLegalDetailsDaoImpl(SessionFactory factory) {
        super(factory);
    }

    @Override
    public void updateLegalDetailsInCompany(Integer companyId, String bankName, String bic) {
        runInTransaction(session -> {
            session.persist(new CompanyLegalDetailsEntity(companyId, bankName, bic));
        });

    }

    @Override
    public Collection<CompanyLegalDetailsEntity> findAllByBankName(String bankName) {
        return runWithoutTransaction(session -> session
                .createQuery("from CompanyLegalDetailsEntity  where bankName = :bankName", CompanyLegalDetailsEntity.class)
                .setParameter("bankName", bankName)
                .getResultList()
        );
    }
}
