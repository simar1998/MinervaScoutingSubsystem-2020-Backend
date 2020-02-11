package com.redalliance.main.db.models;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Simar Pal Kalsi
 * Live Long And Prosper -----> Simt'pal
 **/
@Entity()
@Table(name = "submittedGame")
public class SubmittedGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "teamNum")
    int teamNum;

    @Column(name = "match")
    int match;

    @Column(name = "alliance")
    char alliance;

    int pos;    

    @Column(name = "event")
    String event;

    @Column(name = "timeStamp")
    Date timeStamp;

    @Column(name = "isReplayed" , columnDefinition = "int default 0")
    int numReplayed = 0;


}
