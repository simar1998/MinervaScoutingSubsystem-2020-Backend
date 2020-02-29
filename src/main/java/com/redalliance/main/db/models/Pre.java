package com.redalliance.main.db.models;

import com.google.protobuf.Message;
import com.redalliance.main.db.models.proto.MatchWrapper;

import javax.persistence.*;

/**
 * @author Simar Pal Kalsi
 * Live Long And Prosper -----> Simt'pal
 **/
@Entity
@Table(name = "Pre")
public class Pre implements ProtoInjest{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "submittedGameID")
    int id2;

    @Column(name = "startPos")
    String startPos;

    @Column(name = "loadedBall")
    int loadedBall;


    @Override
    public void fromProtoMessage(Message message) {
        MatchWrapper.Pre pre = (MatchWrapper.Pre) message;
        this.id = pre.getId();
        this.startPos = pre.getStartPos();
        this.loadedBall = pre.getLoadedBall();
    }

    @Override
    public Message toProtoMessage() {
        return MatchWrapper.Pre.newBuilder().setId(this.id).setLoadedBall(this.loadedBall).setStartPos(this.startPos).build();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartPos() {
        return startPos;
    }

    public void setStartPos(String startPos) {
        this.startPos = startPos;
    }

    public int getLoadedBall() {
        return loadedBall;
    }

    public void setLoadedBall(int loadedBall) {
        this.loadedBall = loadedBall;
    }

    public int getId2() {
        return id2;
    }

    public void setId2(int id2) {
        this.id2 = id2;
    }
}
