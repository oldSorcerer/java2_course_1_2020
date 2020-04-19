package org.levelup.application.dao;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.function.Consumer;
import java.util.function.Function;

@RequiredArgsConstructor
public abstract class AbstractDao {

    protected final SessionFactory factory;

    protected <T> T runInTransaction (Function<Session, T> function) {
        try (Session session = factory.openSession()) {
            Transaction transaction =session.beginTransaction();

            T resuit = function.apply(session);

            transaction.commit();
            return resuit;
        }
    }

    protected void runInTransaction (Consumer<Session> consumer) {
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            consumer.accept(session);
            transaction.commit();
        }
    }

    protected <T> T runWithoutTransaction(Function<Session, T> function) {
        try (Session session = factory.openSession()) {
            return function.apply(session);
        }
    }

}
