package org.levelup.comfiguration;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.levelup.application.domain.*;

import java.util.Properties;

public class HibernateTestConfiguration {

    private static SessionFactory factory;

    static {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        hibernateProperties.setProperty("hibernate.connection.url", "jdbc:h2:mem:job_list_test;INIT=CREATE SCHEMA IF NOT EXISTS job_list_test");
        hibernateProperties.setProperty("hibernate.connection.username", "sa");
        hibernateProperties.setProperty("hibernate.connection.password", "");
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        hibernateProperties.setProperty("hibernate.show_sql", "true");
        hibernateProperties.setProperty("hibernate.format_sql", "true");

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(hibernateProperties)
                .build();

        factory = new Configuration()
                .addAnnotatedClass(UserEntity.class)
                .addAnnotatedClass(CompanyEntity.class)
                .addAnnotatedClass(CompanyLegalDetailsEntity.class)
                .addAnnotatedClass(UserAddressEntity.class)
                .addAnnotatedClass(PositionEntity.class)
                .addAnnotatedClass(JobListEntity.class)
                .buildSessionFactory(registry);
    }

    public  static SessionFactory getFactory() {
        return factory;
    }

    public static void close() {
        factory.close();
    }

}
