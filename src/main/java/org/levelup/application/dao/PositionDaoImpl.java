package org.levelup.application.dao;

import org.hibernate.SessionFactory;
import org.levelup.application.domain.PositionEntity;

public class PositionDaoImpl extends AbstractDao implements PositionDao {

    public PositionDaoImpl(SessionFactory factory) {
        super(factory);
    }

    @Override
    public PositionEntity createPosition(String name) {
        return runInTransaction(session -> {
            PositionEntity position = new PositionEntity();
            position.setName(name);

            session.persist(position);
            return position;
        });
    }
}
