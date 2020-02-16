package com.redalliance.main;

import com.redalliance.main.db.hibernate.HibernateThread;
import com.redalliance.main.db.hibernate.HibernateThreadManager;
import com.redalliance.main.db.subroutines.StateManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author Simar Pal Kalsi
 * Live Long And Prosper -----> Simt'pal
 **/
@WebListener
public class ApplicationServletContextListener implements ServletContextListener {

    public static boolean error_state = false;

    StateManager stateManager;

    private static Logger logger = LoggerFactory.getLogger(ApplicationServletContextListener.class);

    public static final boolean TEST_MODE = false;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logger.info("Application Servlet Context Listener initialized");
        HibernateThreadManager.initDBThread();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.info("Application Servlet Context Listener destroyed!");
    }
}
