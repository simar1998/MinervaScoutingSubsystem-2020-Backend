package com.redalliance.main.db.subroutines;

import com.redalliance.main.ApplicationServletContextListener;
import com.redalliance.main.db.HibernateThread;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author Simar Pal Kalsi
 * Live Long And Prosper -----> Simt'pal
 **/
public class TestMode {

    private static Logger logger = LoggerFactory.getLogger(TestMode.class);

    public static final boolean ENABLE_DROP_MODE = false;

    /**
     * DB Operation modes :
     *
     * 0  =  Truncated all managed entities
     * 1  =  Deletes all rows in managed entities
     *
     */
    private static final int DB_OPERATION_MODE = 0;


    public static void testSubroutine(){

        Session session = HibernateThread.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        //Truncate all managed entities
        if (DB_OPERATION_MODE == 0){
            for (Class entityClass : HibernateThread.managedEntities){
                Query query = session.createQuery("TRUNCATE TABLE "+ entityClass.getName());
                query.executeUpdate();
            }
            transaction.commit();
            session.close();
        }
        //Delete entries from managed entities
        else if (DB_OPERATION_MODE == 1){
            for (Class entityClass : HibernateThread.managedEntities){
                Query query = session.createQuery("DELETE *  FROM "+ entityClass.getName());
                query.executeUpdate();
            }
            transaction.commit();
            session.close();
        }
        //Error state
        else {
            logger.error("DB_OPERATION_MODE integer value must be 0 or 1, please check config");
            ApplicationServletContextListener.error_state = true;
        }
    }


}
