package org.levelup.application.dao;

import lombok.SneakyThrows;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.levelup.application.domain.CompanyEntity;

import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Function;

public class CompanyDaoImpl extends AbstractDao implements CompanyDao {

    public CompanyDaoImpl (SessionFactory factory) {
        super(factory);
    }

    @Override
    public void create(String name, String ein, String address) {

        runInTransaction(session -> {
            CompanyEntity entity = new CompanyEntity();
            entity.setName(name);
            entity.setEin(ein);
            entity.setAddress(address);
            session.persist(entity);
        });
    }

    @Override
    public CompanyEntity findById(Integer id) {
        return runWithoutTransaction(session ->  session.get(CompanyEntity.class, id));
    }

    @Override
    public CompanyEntity findByEin(String ein) {

        return runWithoutTransaction(session -> {
                    return session
                            .createQuery("from CompanyEntity where ein = :ein", CompanyEntity.class)
                            .setParameter("ein", ein)
                            .getSingleResult();
            });
    }

    public CompanyEntity findByName(String name) {

        List<CompanyEntity> entities = runWithoutTransaction(session -> {
            return session
                    .createQuery("from CompanyEntity where name =:name", CompanyEntity.class)
                    .setParameter("name", name)
                    .getResultList();
        });
        return entities.isEmpty() ? null : entities.get(0);
    }

    @SneakyThrows
    private  void performDatabaseOperation(Method method, Object object, Object... args) {
        Session session = factory.openSession();
        method.invoke(object, args);
        session.close();

    }


    private <TYPE> TYPE perform(Function<Session, TYPE> function) {
        Session session = factory.openSession();
        TYPE result = function.apply(session);
        session.close();
        return result;
    }

    private void performWithoutTransaction(DatabaseOperation operation) {
        Session session = factory.openSession();
        // action, method

        operation.doAction();
        session.close();

    }


    interface DatabaseOperation {
        void doAction();
    }

    class FindByIdOperatio implements  DatabaseOperation {

        @Override
        public void doAction() {

        }
    }

}
