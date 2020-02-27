package com.redalliance.main.db.models;

import com.google.protobuf.Message;
import com.redalliance.main.db.models.proto.MatchWrapper;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Simar Pal Kalsi
 * Live Long And Prosper -----> Simt'pal
 **/
@Entity()
@Table(name = "submittedGame")
public class SubmittedGame implements ProtoInjest{

    public transient DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

    @Id
    int id;

    @Column(name = "teamNum")
    int teamNum;

    @Column(name = "matchNum")
    int match;

    @Column(name = "scoutTeamNum")
    int scoutTeamNum;

    @Column(name = "scoutName")
    String scoutName;

    @Column(name = "scoutUUID")
    String scoutUUID;

    @Column(name = "alliance")
    char alliance;

    @Column(name = "pos")
    int pos;    

    @Column(name = "event")
    String event;

    @Column(name = "timeStamp")
    Date timeStamp;

    @Column(name = "isReplayed" , columnDefinition = "int default 0")
    int numReplayed = 0;


    @Override
    public void fromProtoMessage(Message message) {
        MatchWrapper.InitInfo submittedMatch = (MatchWrapper.InitInfo) message;
        this.id = submittedMatch.getId();
        this.teamNum = submittedMatch.getTeamNum();
        this.match = submittedMatch.getMatch();
        this.alliance = submittedMatch.getAlliance().toCharArray()[0];
        this.pos = submittedMatch.getPos();
        this.event = submittedMatch.getEvent();
        try {
            this.timeStamp = dateFormat.parse(submittedMatch.getTimeStamp());
        } catch (ParseException e) {
            this.timeStamp = null;
            e.printStackTrace();
        }
        this.numReplayed = submittedMatch.getNumReplayed();
        this.scoutName = submittedMatch.getScoutName();
        this.scoutTeamNum = submittedMatch.getScoutTeamNum();
        try {
            if (submittedMatch.getScoutUUID() != null) {
                this.scoutUUID = submittedMatch.getScoutUUID();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Message toProtoMessage() {
        if (this.scoutUUID == null) {
            return MatchWrapper.InitInfo.newBuilder().setId(this.id).setTeamNum(this.teamNum).setMatch(this.match).setAlliance(this.alliance + "")
                    .setPos(this.pos).setEvent(this.event).setTimeStamp(dateFormat.format(this.timeStamp)).setNumReplayed(this.numReplayed)
                    .setScoutName(this.scoutName).setScoutTeamNum(this.teamNum).build();
        }
        else {
            return MatchWrapper.InitInfo.newBuilder().setId(this.id).setTeamNum(this.teamNum).setMatch(this.match).setAlliance(this.alliance + "")
                    .setPos(this.pos).setEvent(this.event).setTimeStamp(dateFormat.format(this.timeStamp)).setNumReplayed(this.numReplayed)
                    .setScoutName(this.scoutName).setScoutTeamNum(this.teamNum).setScoutUUID(this.scoutUUID).build();
        }
    }

    public DateFormat getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTeamNum() {
        return teamNum;
    }

    public void setTeamNum(int teamNum) {
        this.teamNum = teamNum;
    }

    public int getMatch() {
        return match;
    }

    public void setMatch(int match) {
        this.match = match;
    }

    public int getScoutTeamNum() {
        return scoutTeamNum;
    }

    public void setScoutTeamNum(int scoutTeamNum) {
        this.scoutTeamNum = scoutTeamNum;
    }

    public String getScoutName() {
        return scoutName;
    }

    public void setScoutName(String scoutName) {
        this.scoutName = scoutName;
    }

    public String getScoutUUID() {
        return scoutUUID;
    }

    public void setScoutUUID(String scoutUUID) {
        this.scoutUUID = scoutUUID;
    }

    public char getAlliance() {
        return alliance;
    }

    public void setAlliance(char alliance) {
        this.alliance = alliance;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getNumReplayed() {
        return numReplayed;
    }

    public void setNumReplayed(int numReplayed) {
        this.numReplayed = numReplayed;
    }
}
