package com.redalliance.main.db.hibernate;

import com.redalliance.main.CONST;

import java.util.ArrayList;

/**
 * @author Simar Pal Kalsi
 * Live Long And Prosper -----> Simt'pal
 **/
public class HibernateThreadManager {

    public static ArrayList<HibernateThread> hibernateThreads = new ArrayList<>();

    public static void initDBThread(){
        HibernateThread team1 = new HibernateThread(CONST.DB_STRING,CONST.USER_NAME,CONST.PASSWORD,"1310-188");
        team1.buildSessionFactory();
        team1.initHibernateThread();
        hibernateThreads.add(team1);

//        HibernateThread team2 = new HibernateThread("jdbc:mysql://localhost:3306/team2?useSSL=false",CONST.USER_NAME,CONST.PASSWORD,"1334");
//        team2.buildSessionFactory();
//        team2.initHibernateThread();
//        hibernateThreads.add(team2);
//
//        HibernateThread other = new HibernateThread("jdbc:mysql://localhost:3306/otherTeams?useSSL=false",CONST.USER_NAME,CONST.PASSWORD,"other");
//        other.buildSessionFactory();
//        other.initHibernateThread();
//        hibernateThreads.add(other);
    }


    public static HibernateThread getHibernateThread(String teamStr){
        for (HibernateThread hibernateThread : hibernateThreads){
            if (hibernateThread.getTeam().equals(teamStr)){
                return hibernateThread;
            }
        }
        return hibernateThreads.get(2);
    }

    public static HibernateThread getHibernateThread(int teamNum){
        for (HibernateThread hibernateThread : hibernateThreads){
            if (hibernateThread.getTeam().contains(teamNum+"")){
                System.out.println("team NUM " + hibernateThread.getTeam());
                return hibernateThread;
            }
        }
        return hibernateThreads.get(2);
    }



}
