package com.redalliance.main.db.subroutines;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Simar Pal Kalsi
 * Live Long And Prosper -----> Simt'pal
 **/
public class StateManager implements Runnable {

    public static State programState = State.NOMINAL;
    private static Logger logger = LoggerFactory.getLogger(StateManager.class);
    private static ArrayList<String> warnings = new ArrayList<>();
    private final boolean EXIT_ON_ERROR = false;
    int interval;
    int initPeriod;
    TimeUnit timeUnit;
    ScheduledExecutorService scheduledExecutorService;

    public StateManager(int interval, int initPeriod, TimeUnit timeUnit) {
        this.interval = interval;
        this.initPeriod = initPeriod;
        this.timeUnit = timeUnit;
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(this, initPeriod, interval, timeUnit);
        logger.info("STATE MANAGER IS OPERATIONAL !!");
    }

    @Override
    public void run() {
        switch (programState) {
            case NOMINAL:
                logger.info("State Manager has logged NOMINAL status on all services");
                break;
            case CAUTION:
                logger.warn("WARNING MODE IS ENABLED !!!!");
                for (String message : warnings) {
                    logger.warn("CAUTION MESSAGE : " + message);
                }
                break;
            case ERROR:
                logger.error("AN ERROR HAS BEEN STATED !!!!");
                if (EXIT_ON_ERROR) {
                    logger.error("EXIT ON ERROR IS TRUE, EXITING APPLICATION IN 10 SECONDS !!");
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.exit(0);
                }
        }
    }


    public void addToCaution(String cautionMessage) {
        if (programState == State.NOMINAL) {
            programState = State.CAUTION;
        }
        warnings.add(cautionMessage);
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public int getInitPeriod() {
        return initPeriod;
    }

    public void setInitPeriod(int initPeriod) {
        this.initPeriod = initPeriod;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    public ScheduledExecutorService getScheduledExecutorService() {
        return scheduledExecutorService;
    }

    public void setScheduledExecutorService(ScheduledExecutorService scheduledExecutorService) {
        this.scheduledExecutorService = scheduledExecutorService;
    }
}
