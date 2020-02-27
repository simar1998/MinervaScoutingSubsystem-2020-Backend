package com.redalliance.main.db.hibernate;

import com.redalliance.main.ApplicationServletContextListener;
import com.redalliance.main.CONST;
import com.redalliance.main.db.models.*;
import com.redalliance.main.db.subroutines.State;
import com.redalliance.main.db.subroutines.StateManager;
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

    private  String dbString = "jdbc:mysql://localhost:3306/minerva?useSSL=false";
    private  String userName = "newuser";
    private  String password = "password";
    private  String team = "1310";

    public static ArrayList<Class> managedEntities = new ArrayList<>();

    public HibernateThread(String dbString, String userName, String password, String team) {
        this.dbString = dbString;
        this.userName = userName;
        this.password = password;
        this.team = team;
    }

    public HibernateThread(String dbString, String userName, String password) {
        this.dbString = dbString;
        this.userName = userName;
        this.password = password;
    }

    static {
        managedEntities.add(SubmittedGame.class);
        managedEntities.add(Actions.class);
        managedEntities.add(PostActions.class);
        managedEntities.add(Pre.class);
        managedEntities.add(BufferTable.class);
    }

    /**
     * Hibernate thread object
     */

    public Thread hibernateThread;
    /**
     * The constant ourSessionFactory.
     */
    public SessionFactory ourSessionFactory;
    /**
     * Logger object
     */
    private Logger logger = LoggerFactory.getLogger(HibernateThread.class);

    /**
     * Gets session.
     *
     * @return the session
     * @throws HibernateException the hibernate exception
     */
    public SessionFactory getSessionFactory() throws HibernateException {
        return ourSessionFactory;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    /**
     * Build session factory session factory.
     *
     * @return the session factory
     */
    public SessionFactory buildSessionFactory() {
        try {
            logger.info("Session factory built, hibernate thread");
            configuration = new
                    Configuration()
                    .setProperty("hibernate.connection.url", dbString)
                    .setProperty("hibernate.connection.driver_class", CONST.DRIVER_STRING)
                    .setProperty("hibernate.connection.username", userName)
                    .setProperty("hibernate.connection.password", password)
                    .setProperty("hibernate.show_sql", "true")
                    .setProperty("hibernate.dialect","org.hibernate.dialect.MySQL5InnoDBDialect")
                    .setProperty("hibernate.hbm2ddl.auto", "update");

            if (ApplicationServletContextListener.TEST_MODE) {
                configuration.setProperty("hibernate.hbm2ddl.auto", "update");
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
            e.printStackTrace();
            logger.error("HIBERNATE SESSION FACTORY HAS THROWN AN ERROR AND THUS IS NOT ACTIVE !!!");
            StateManager.programState = State.ERROR;
        }
        throw new RuntimeException("HIBERNATE THREAD ERROR!!!!!");
    }

    /**
     * Shut down.
     */
    public void shutDown() {
        logger.info("Hibernate session factory closed");
        ourSessionFactory.close();
    }

    public void initHibernateThread() {
        logger.info("Hibernate thread initialized");
        hibernateThread = new Thread(this);
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