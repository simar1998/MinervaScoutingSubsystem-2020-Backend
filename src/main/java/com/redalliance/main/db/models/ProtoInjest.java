package com.redalliance.main.db.models;

import com.google.protobuf.Message;

/**
 * @author Simar Pal Kalsi
 * Live Long And Prosper -----> Simt'pal
 **/
public interface ProtoInjest {

    public void fromProtoMessage(Message message);

    public Message toProtoMessage();

}
