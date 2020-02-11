package com.redalliance.main.db;

import com.redalliance.main.ApplicationServletContextListener;
import com.redalliance.main.db.models.SubmittedGame;
import com.redalliance.main.db.subroutines.State;
import com.redalliance.main.db.subroutines.StateManager;
import com.redalliance.main.db.subroutines.TestMode;
import org.hibernate.HibernateException;
import org.hibernate.Metamodel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;

/**
 * The type Hibernate thread.
 */
public class HibernateThread implements Runnable {

    public static Configuration configuration;

    private static final String DB_STRING = "jdbc:mysql://localhost:3306/mslice?useSSL=false";
    private static final String DRIVER_STRING =  "com.mysql.cj.jdbc.Driver";
    private static final String USER_NAME = "cloud_engine";
    private static final String PASSWORD = "mechsoljavaapp123";

    public static ArrayList<Class> managedEntities = new ArrayList<>();


    static {
        managedEntities.add(SubmittedGame.class);
    }


    /**
     * Hibernate thread object
     */

    public static Thread hibernateThread;
    /**
     * The constant ourSessionFactory.
     */
    public static SessionFactory ourSessionFactory;
    /**
     * Logger object
     */
    private static Logger logger = LoggerFactory.getLogger(HibernateThread.class);

    /**
     * Gets session.
     *
     * @return the session
     * @throws HibernateException the hibernate exception
     */
    public static SessionFactory getSessionFactory() throws HibernateException {
        return ourSessionFactory;
    }


    /**
     * Build session factory session factory.
     *
     * @return the session factory
     */
    public static SessionFactory buildSessionFactory() {
        try {
            logger.info("Session factory built, hibernate thread");
            configuration = new Configuration()
                    .setProperty("hibernate.connection.url", DB_STRING)
                    .setProperty("hibernate.connection.driver_class", DRIVER_STRING)
                    .setProperty("hibernate.connection.username", USER_NAME)
                    .setProperty("hibernate.connection.password", PASSWORD)
                    .setProperty("hibernate.show_sql", "true")
                    .setProperty("hibernate.hbm2ddl.auto", "update");

            if (ApplicationServletContextListener.TEST_MODE && TestMode.ENABLE_DROP_MODE) {
                configuration.setProperty("hibernate.hbm2ddl.auto", "drop");
            }
            for (Class entityClass : managedEntities) {
                configuration.addAnnotatedClass(entityClass);
            }
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            ourSessionFactory = configuration.buildSessionFactory(serviceRegistry);
            logger.info("DB MANAGEMENT THREAD IS OPERATIONAL !!");
            return ourSessionFactory;
        }
        catch (Exception e){
            logger.error("HIBERNATE SESSION FACTORY HAS THROWN AN ERROR AND THUS IS NOT ACTIVE !!!");
            StateManager.programState = State.ERROR;
        }
        throw new RuntimeException("HIBERNATE THREAD ERROR!!!!!");
    }

    /**
     * Shut down.
     */
    public static void shutDown() {
        logger.info("Hibernate session factory closed");
        ourSessionFactory.close();
    }

    public static void initHibernateThread() {
        logger.info("Hibernate thread initialized");
        HibernateThread.buildSessionFactory();
        hibernateThread = new Thread(new HibernateThread());
        hibernateThread.start();
    }

    @Override
    public void run() {
        final Session session = getSessionFactory().openSession();
        try {
            logger.info("querying all the managed entities...");
            final Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities()) {
                final String entityName = entityType.getName();
                final Query query = session.createQuery("from " + entityName);
                logger.info("executing: " + query.getQueryString());
                for (Object o : query.list()) {
                    System.out.println("  " + o.toString());
                }
            }
        } finally {
            session.close();
        }


    }

}