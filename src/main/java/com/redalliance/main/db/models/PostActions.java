package com.redalliance.main.db.models;

import javax.persistence.Column;

/**
 * @author Simar Pal Kalsi
 * Live Long And Prosper -----> Simt'pal
 **/
public class PostActions {

    @Column(name = "id")
    int id;

    @Column(name = "hangStart")
    int hangStart;

    @Column(name = "hangEnd")
    int hangEnd;

    @Column(name = "hangLoc")
    int hangLoc;

    @Column(name = "balanceTime")
    int balanceTime;
}
