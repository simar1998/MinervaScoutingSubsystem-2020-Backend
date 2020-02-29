package com.redalliance.main.db.models;

import javax.persistence.*;

/**
 * @author Simar Pal Kalsi
 * Live Long And Prosper -----> Simt'pal
 **/
@Entity
@Table(name = "BufferTable")
public class BufferTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "submittedGameID")
    int id2;

    @Column(name = "incommingData" ,  columnDefinition="TEXT")
    String incommingData;

    @Column(name = "isProcessed")
    boolean isProcessed;

    @Column(name = "isByteStream")
    boolean isByteStream;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId2() {
        return id2;
    }

    public void setId2(int id2) {
        this.id2 = id2;
    }

    public String getIncommingData() {
        return incommingData;
    }

    public void setIncommingData(String incommingData) {
        this.incommingData = incommingData;
    }

    public boolean isProcessed() {
        return isProcessed;
    }

    public void setProcessed(boolean processed) {
        isProcessed = processed;
    }

    public boolean isByteStream() {
        return isByteStream;
    }

    public void setByteStream(boolean byteStream) {
        isByteStream = byteStream;
    }
}
