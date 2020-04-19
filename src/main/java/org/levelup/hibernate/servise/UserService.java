package org.levelup.hibernate.servise;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.levelup.application.domain.UserEntity;

public class UserService {

    private final SessionFactory factory;

    public UserService(SessionFactory factory) {
        this.factory = factory;
    }

    public UserEntity createUserPersist(String name, String lastName, String passport) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        UserEntity user = new UserEntity(); // transient

        user.setName(name);
        user.setLastName(lastName);
        user.setPassport(passport);

        session.persist(user); //manager/persistent

        transaction.commit();
        session.close();

        return user;
    }

    public Integer createUserSave (String name, String lastName, String passport) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        UserEntity user = new UserEntity();
        user.setName(name);
        user.setLastName(lastName);
        user.setPassport(passport);

        Integer generateId = (Integer) session.save(user);
        //Integer generateId = user.getId();

        transaction.commit();
        session.close();

        return generateId;
    }

    public UserEntity getById(Integer id) {
        Session session = factory.openSession();
        UserEntity user = session.get(UserEntity.class, id);

        session.close(); // user - detached

        return user;
    }

    public Integer cloneUser(Integer id, String passport) {
        UserEntity user = getById(id);
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        user.setPassport(passport);
        Integer cloneId = (Integer) session.save(user);

        transaction.commit();
        session.close();

        return cloneId;

    }

    public UserEntity updateUserNameWithMerge(Integer id, String name) {
        UserEntity user = getById(id);

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        user.setName(name);
        UserEntity mergedUser = (UserEntity) session.merge(user);

        transaction.commit();
        session.close();

        System.out.println("original user: " + Integer.toHexString(user.hashCode()));

        return mergedUser;
    }

    public UserEntity mergeNewUser (String name, String lastName, String passport) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        UserEntity user = new UserEntity();
        user.setName(name);
        user.setLastName(lastName);
        user.setPassport(passport);

        UserEntity newUser = (UserEntity) session.merge(user);

        transaction.commit();
        session.close();

        return newUser;
    }

    public void updateUser (String name, String lastName, String passport) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        UserEntity user = new UserEntity();
        user.setName(name);
        user.setLastName(lastName);
        user.setPassport(passport);

        session.update(user);

        transaction.commit();
        session.close();

    }
}
