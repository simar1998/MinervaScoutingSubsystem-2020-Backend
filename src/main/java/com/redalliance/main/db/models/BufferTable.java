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
    int id;

    @Column(name = "incommingData" ,  columnDefinition="TEXT")
    String incommingData;

    @Column(name = "isProcessed")
    boolean isProcessed;

    @Column(name = "isByteStream")
    boolean isByteStream;
}
