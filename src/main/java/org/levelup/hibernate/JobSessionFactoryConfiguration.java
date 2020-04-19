package org.levelup.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class JobSessionFactoryConfiguration {

    public SessionFactory configure() {
        Configuration configuration = new Configuration().configure();
        SessionFactory factory = configuration.buildSessionFactory();
        return factory;
    }
}
