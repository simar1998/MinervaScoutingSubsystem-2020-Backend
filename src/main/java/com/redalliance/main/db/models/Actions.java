package com.redalliance.main.db.models;

import javax.persistence.Column;

/**
 * @author Simar Pal Kalsi
 * Live Long And Prosper -----> Simt'pal
 **/
public class Actions {

    @Column(name = "id")
    int id;

    @Column(name = "time")
    int time;

    @Column(name = "action")
    String action;

    @Column(name = "location")
    String location;

    @Column(name = "isAuto")
    boolean auto;


}
