package com.redalliance.main.db.models;

import com.google.protobuf.Message;
import com.redalliance.main.db.models.proto.MatchWrapper;

import javax.persistence.*;

/**
 * @author Simar Pal Kalsi
 * Live Long And Prosper -----> Simt'pal
 **/
@Entity
@Table(name = "PostActions")
public class PostActions implements ProtoInjest{

    @Id
    int id;

    @Column(name = "hangStart")
    int hangStart;

    @Column(name = "hangEnd")
    int hangEnd;

    @Column(name = "hangLoc")
    int hangLoc;

    @Column(name = "balanceTime")
    int balanceTime;

    @Column(name  = "isBalanced")
    boolean isBalanced;

    @Column(name = "gotClimbAssistance")
    boolean gotClimbAssistance;

    @Column(name = "isBuddyBot")
    boolean buddyBot;

    @Column(name = "comment")
    String comment;

    @Column(name = "isParked")
    boolean isParked;

    @Override
    public void fromProtoMessage(Message message) {
        MatchWrapper.PostActions postActions = (MatchWrapper.PostActions) message;
        this.id = postActions.getId();
        this.hangStart = postActions.getHangStart();
        this.hangEnd = postActions.getHangLoc();
        this.balanceTime = postActions.getBalanceTime();
        this.hangLoc = postActions.getHangLoc();
        this.isBalanced = postActions.getBalanced();
        this.gotClimbAssistance = postActions.getGotHangAssist();
        this.buddyBot = postActions.getBuddyBot();
        this.comment = postActions.getComment();
        this.isParked = postActions.getIsParked();
    }

    @Override
    public Message toProtoMessage() {
        return MatchWrapper.PostActions.newBuilder()
                .setId(this.id).setHangStart(this.hangStart).setHangEnd(this.hangEnd)
                .setBalanced(this.isBalanced).setHangLoc(this.hangLoc).setBalanceTime(this.balanceTime)
                .setGotHangAssist(this.gotClimbAssistance).setBuddyBot(this.buddyBot).
                setComment(this.comment).setIsParked(this.isParked).build();
    }

}
