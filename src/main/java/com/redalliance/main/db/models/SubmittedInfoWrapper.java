package com.redalliance.main.db.models;

import com.google.gson.Gson;
import com.google.protobuf.InvalidProtocolBufferException;
import com.redalliance.main.db.hibernate.HibernateThreadManager;
import com.redalliance.main.db.models.proto.MatchWrapper;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.Base64;

/**
 * @author Simar Pal Kalsi
 * Live Long And Prosper -----> Simt'pal
 **/
public class SubmittedInfoWrapper {

    private static Gson gson = new Gson();

    @OneToOne()
    SubmittedGame submittedGame;

    @OneToOne()
    Pre pre;

    @OneToOne()
    PostActions postActions;

    @OneToMany()
    ArrayList<Actions> actions;

    public static Gson getGson() {
        return gson;
    }

    public static void setGson(Gson gson) {
        SubmittedInfoWrapper.gson = gson;
    }

    public SubmittedGame getSubmittedGame() {
        return submittedGame;
    }

    public void setSubmittedGame(SubmittedGame submittedGame) {
        this.submittedGame = submittedGame;
    }

    public Pre getPre() {
        return pre;
    }

    public void setPre(Pre pre) {
        this.pre = pre;
    }

    public PostActions getPostActions() {
        return postActions;
    }

    public void setPostActions(PostActions postActions) {
        this.postActions = postActions;
    }

    public ArrayList<Actions> getActions() {
        return actions;
    }

    public void setActions(ArrayList<Actions> actions) {
        this.actions = actions;
    }

    public static void processIncommingString(String str){
        processIncommingString(gson.fromJson(str,SubmittedInfoWrapper.class));
    }

    public static void processIncommingString(SubmittedInfoWrapper submittedInfoWrapper){
        Session session = HibernateThreadManager.getHibernateThread(submittedInfoWrapper.getSubmittedGame().getTeamNum()).getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(submittedInfoWrapper.getSubmittedGame());
        session.save(submittedInfoWrapper.getPre());
        for (Actions action : submittedInfoWrapper.getActions()){
            session.save(action);
        }
        session.save(submittedInfoWrapper.getPostActions());
        transaction.commit();
        session.close();
    }

    public static void processProtoString(String protoString){
        byte[] messageBytes = Base64.getDecoder().decode(protoString);
        MatchWrapper.SubmittedMatch protoMessage;
        try {
            protoMessage = MatchWrapper.SubmittedMatch.parseFrom(messageBytes);
            SubmittedInfoWrapper submittedInfoWrapper = new SubmittedInfoWrapper();

            SubmittedGame submittedGame = new SubmittedGame();
            submittedGame.fromProtoMessage(protoMessage.getInitInfo());
            submittedInfoWrapper.setSubmittedGame(submittedGame);

            Pre pre = new Pre();
            pre.fromProtoMessage(protoMessage.getPre());
            submittedInfoWrapper.setPre(pre);

            ArrayList<Actions> actions = new ArrayList<>();
            Actions tempAction;
            for (int i = 0 ; i < protoMessage.getActionsList().size(); i++){
                tempAction = new Actions();
                tempAction.fromProtoMessage(protoMessage.getActions(i));
                actions.add(tempAction);
            }
            submittedInfoWrapper.setActions(actions);

            PostActions postActions = new PostActions();
            postActions.fromProtoMessage(protoMessage.getPost());
            submittedInfoWrapper.setPostActions(postActions);

            processIncommingString(submittedInfoWrapper);

        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }
}
