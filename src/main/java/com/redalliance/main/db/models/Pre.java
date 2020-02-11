package com.redalliance.main.db.models;

import javax.persistence.Column;

/**
 * @author Simar Pal Kalsi
 * Live Long And Prosper -----> Simt'pal
 **/
public class Pre {

    @Column(name = "id")
    int id;

    @Column(name = "startPos")
    String startPos;

    @Column(name = "loadedBall")
    int loadedBall;


}
