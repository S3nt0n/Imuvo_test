package com.example.sco.imuvo.Model;

import android.util.Log;

import com.example.sco.imuvo.HelperClasses.WebServiceHelper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

/**
 * Created by sco on 05.12.2016.
 */

public class Vocab {
    private long sqlID;
    private String german;
    private String foreign;
    private byte[] speech;
    private int lection;
    private byte[] picture;


    public Vocab(long sqlID, String german, String foreign, int lection, byte[] speech, byte[] picture) {
        this.sqlID = sqlID;
        this.german = german;
        this.foreign = foreign;
        this.speech = speech;
        this.lection = lection;
        this.picture = picture;
    }

    public Vocab(String german, String foreign, int lection) {
        this.german = german;
        this.foreign = foreign;
        this.lection = lection;
    }

    public int getLection() {
        return lection;
    }

    public void setLection(int lection) {
        this.lection = lection;
    }

    public long getSqlID() {
        return sqlID;
    }

    public void setSqlID(long sqlID) {
        this.sqlID = sqlID;
    }

    public String getGerman() {
        return german;
    }

    public void setGerman(String german) {
        this.german = german;
    }

    public String getForeign() {
        return foreign;
    }

    public void setForeign(String foreign) {
        this.foreign = foreign;
    }

    public byte[] getSpeech() {
        return speech;
    }

    public void setSpeech(byte[] speech) {
        this.speech = speech;
    }

    @Override
    public String toString() {
        return getForeign();
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
}
