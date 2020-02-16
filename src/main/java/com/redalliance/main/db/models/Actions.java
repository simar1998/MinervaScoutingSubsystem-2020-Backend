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
@Table(name = "Actions")
public class Actions implements ProtoInjest{

    @Id
    int id;

    @Column(name = "time")
    int time;

    @Column(name = "action")
    String action;

    @Column(name = "location")
    String location;

    @Column(name = "isAuto")
    boolean auto;


    @Override
    public void fromProtoMessage(Message message) {
        MatchWrapper.Action action = (MatchWrapper.Action) message;
        this.id = action.getId();
        this.action = action.getAction();
        this.auto = action.getIsAuto();
        this.location = action.getLocation();
        this.time = action.getTime();
    }

    @Override
    public Message toProtoMessage() {
        return MatchWrapper.Action.newBuilder().setId(this.id).setAction(this.action)
                .setIsAuto(this.auto).setLocation(this.location).setTime(this.time).build();
    }
}
