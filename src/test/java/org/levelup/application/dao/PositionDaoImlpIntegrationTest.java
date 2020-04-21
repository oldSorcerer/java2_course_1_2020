package org.levelup.application.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.levelup.application.domain.PositionEntity;
import org.levelup.comfiguration.HibernateTestConfiguration;

import javax.persistence.PersistenceException;

public class PositionDaoImlpIntegrationTest {

    private static SessionFactory factory;
    private static PositionDao positionDao;


    @BeforeAll
    public static void setupPositionDao() {
        factory = HibernateTestConfiguration.getFactory();
        positionDao = new PositionDaoImpl(factory);
    }

    @Test
    @DisplayName("Create new position, then position with this name doesn't exist")
    public void testCreatePosition_whenPositionWithNameNotExist_thenCreateNewPosition() {
        String name = "Java Developer";

        PositionEntity result = positionDao.createPosition(name);
        Assertions.assertNotNull(result.getId());
        Assertions.assertEquals(name, result.getName());

        Session session = factory.openSession();
        PositionEntity fromDb = session.createQuery("from PositionEntity where name =:name", PositionEntity.class)
                .setParameter("name", name)
                .getSingleResult();
        Assertions.assertNotNull(fromDb);
        session.close();
    }

    @Test
    @DisplayName("Throw exception, when position wjth this name exists")
    public void testCreatePosition_whenPositionWithNameExists_thenThowExeption() {
        String name = "PositionName";
        positionDao.createPosition(name);

        Assertions.assertThrows(PersistenceException.class, () -> positionDao.createPosition(name));

    }


}
