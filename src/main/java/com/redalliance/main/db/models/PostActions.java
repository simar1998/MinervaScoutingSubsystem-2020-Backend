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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "submittedGameID")
    int id2;

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

    @Column(name = "wasBotDefended")
    boolean wasBotDefended;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHangStart() {
        return hangStart;
    }

    public void setHangStart(int hangStart) {
        this.hangStart = hangStart;
    }

    public int getHangEnd() {
        return hangEnd;
    }

    public void setHangEnd(int hangEnd) {
        this.hangEnd = hangEnd;
    }

    public int getHangLoc() {
        return hangLoc;
    }

    public void setHangLoc(int hangLoc) {
        this.hangLoc = hangLoc;
    }

    public int getBalanceTime() {
        return balanceTime;
    }

    public void setBalanceTime(int balanceTime) {
        this.balanceTime = balanceTime;
    }

    public boolean isBalanced() {
        return isBalanced;
    }

    public void setBalanced(boolean balanced) {
        isBalanced = balanced;
    }

    public boolean isGotClimbAssistance() {
        return gotClimbAssistance;
    }

    public void setGotClimbAssistance(boolean gotClimbAssistance) {
        this.gotClimbAssistance = gotClimbAssistance;
    }

    public boolean isBuddyBot() {
        return buddyBot;
    }

    public void setBuddyBot(boolean buddyBot) {
        this.buddyBot = buddyBot;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isParked() {
        return isParked;
    }

    public void setParked(boolean parked) {
        isParked = parked;
    }

    public int getId2() {
        return id2;
    }

    public void setId2(int id2) {
        this.id2 = id2;
    }

    public boolean isWasBotDefended() {
        return wasBotDefended;
    }

    public void setWasBotDefended(boolean wasBotDefended) {
        this.wasBotDefended = wasBotDefended;
    }
}
