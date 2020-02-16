package com.redalliance.main.db.models;

import com.google.protobuf.Message;
import com.redalliance.main.db.models.proto.MatchWrapper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Simar Pal Kalsi
 * Live Long And Prosper -----> Simt'pal
 **/
@Entity
@Table(name = "Pre")
public class Pre implements ProtoInjest{

    @Id
    int id;

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
}
