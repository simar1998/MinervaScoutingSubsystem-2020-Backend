package com.redalliance.main.db.models;

import com.google.protobuf.Message;
import com.redalliance.main.db.models.proto.MatchWrapper;

import javax.persistence.*;

/**
 * @author Simar Pal Kalsi
 * Live Long And Prosper -----> Simt'pal
 **/
@Entity
@Table(name = "SubmittedGame")
public class SubmittedGame implements ProtoInjest{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "teamNum")
    int teamNum;

    @Column(name = "matchNum")
    int matchNum;

    @Column(name = "scoutTeam")
    int scoutTeamNum;

    @Column(name = "scoutName")
    String scoutName;

    @Column(name = "scoutUUID")
    String scoutUUID;

    @Column(name = "alliance")
    String alliance;

    @Column(name = "pos")
    int pos;    

    @Column(name = "event")
    String event;

    @Column(name = "timeStamp")
    String timeStamp;

    @Column(name = "isReplayed")
    int numReplayed;

    @Column(name = "submissionUUID")
    String submissionUUID;


    @Override
    public void fromProtoMessage(Message message) {
        MatchWrapper.InitInfo submittedMatch = (MatchWrapper.InitInfo) message;
        this.id = submittedMatch.getId();
        this.teamNum = submittedMatch.getTeamNum();
        this.matchNum = submittedMatch.getMatch();
        this.alliance = submittedMatch.getAlliance();
        this.pos = submittedMatch.getPos();
        this.event = submittedMatch.getEvent();
        this.timeStamp = submittedMatch.getTimeStamp();
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
            return MatchWrapper.InitInfo.newBuilder().setId(this.id).setTeamNum(this.teamNum).setMatch(this.matchNum).setAlliance(this.alliance + "")
                    .setPos(this.pos).setEvent(this.event).setTimeStamp(this.timeStamp).setNumReplayed(this.numReplayed)
                    .setScoutName(this.scoutName).setScoutTeamNum(this.teamNum).build();
        }
        else {
            return MatchWrapper.InitInfo.newBuilder().setId(this.id).setTeamNum(this.teamNum).setMatch(this.matchNum).setAlliance(this.alliance + "")
                    .setPos(this.pos).setEvent(this.event).setTimeStamp(this.timeStamp).setNumReplayed(this.numReplayed)
                    .setScoutName(this.scoutName).setScoutTeamNum(this.teamNum).setScoutUUID(this.scoutUUID).build();
        }
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

    public int getMatchNum() {
        return matchNum;
    }

    public void setMatchNum(int matchNum) {
        this.matchNum = matchNum;
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

    public String getAlliance() {
        return alliance;
    }

    public void setAlliance(String alliance) {
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

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getNumReplayed() {
        return numReplayed;
    }

    public void setNumReplayed(int numReplayed) {
        this.numReplayed = numReplayed;
    }

    public String getSubmissionUUID() {
        return submissionUUID;
    }

    public void setSubmissionUUID(String submissionUUID) {
        this.submissionUUID = submissionUUID;
    }
}
