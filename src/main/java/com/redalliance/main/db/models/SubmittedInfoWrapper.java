package com.redalliance.main.db.models;

import com.google.gson.Gson;
import com.google.protobuf.InvalidProtocolBufferException;
import com.redalliance.main.db.hibernate.HibernateThread;
import com.redalliance.main.db.hibernate.HibernateThreadManager;
import com.redalliance.main.db.models.proto.MatchWrapper;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.Base64;
import java.util.UUID;

/**
 * @author Simar Pal Kalsi
 * Live Long And Prosper -----> Simt'pal
 **/
public class SubmittedInfoWrapper {

    private static Gson gson = new Gson();


    SubmittedGame submittedGame;


    Pre pre;


    PostActions postActions;


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
        try {


            HibernateThread hibernateThread = HibernateThreadManager.getHibernateThread(1310);
            String uuid = UUID.randomUUID().toString();
            if (submittedInfoWrapper.getSubmittedGame().getSubmissionUUID() == null){
                submittedInfoWrapper.getSubmittedGame().setSubmissionUUID(uuid);
            }
            Session session = hibernateThread.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from SubmittedGame where teamNum = :teamNum and  matchNum = :matchNum and event = :event");
            query.setParameter("teamNum",submittedInfoWrapper.getSubmittedGame().getTeamNum());
            query.setParameter("matchNum",submittedInfoWrapper.getSubmittedGame().getMatchNum());
            query.setParameter("event",submittedInfoWrapper.getSubmittedGame().getEvent());
            try{
                if (query.list().size() != 0){
                    transaction.commit();
                    session.close();
                    throw new RuntimeException("Match " + submittedInfoWrapper.getSubmittedGame().getMatchNum() + "for team " + submittedInfoWrapper
                    .getSubmittedGame().getTeamNum() + " in even " + submittedInfoWrapper.getSubmittedGame().getEvent() + " is already submitted");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            transaction.commit();
            session.close();

            session = hibernateThread.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(submittedInfoWrapper.getSubmittedGame());
            transaction.commit();
            session.close();

            session = hibernateThread.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            query = session.createQuery("from SubmittedGame where submissionUUID = :uuid ");
            query.setParameter("uuid", uuid);
            ArrayList<SubmittedGame> submittedGames = (ArrayList<SubmittedGame>) query.list();
            submittedInfoWrapper.getSubmittedGame().setId(submittedGames.get(0).getId());
            transaction.commit();
            session.close();

            session = hibernateThread.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            submittedInfoWrapper.getPre().setId2(submittedInfoWrapper.getSubmittedGame().getId());
            session.save(submittedInfoWrapper.getPre());
            transaction.commit();
            session.close();
            for (Actions action : submittedInfoWrapper.getActions()) {
                session = hibernateThread.getSessionFactory().openSession();
                transaction = session.beginTransaction();
                action.setId2(submittedInfoWrapper.getSubmittedGame().getId());
                session.save(action);
                transaction.commit();
                session.close();
            }
            session = hibernateThread.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            submittedInfoWrapper.getPostActions().setId2(submittedInfoWrapper.getSubmittedGame().getId());
            session.save(submittedInfoWrapper.getPostActions());
            transaction.commit();
            session.close();
        }catch (Exception e){
            e.printStackTrace();
        }
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
