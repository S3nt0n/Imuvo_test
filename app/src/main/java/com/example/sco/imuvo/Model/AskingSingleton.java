package com.example.sco.imuvo.Model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by sco on 22.12.2016.
 */
public class AskingSingleton {
    private static AskingSingleton ourInstance = new AskingSingleton();
    public static Date startingDate;
    public static Date endingDate;
    public static ArrayList<Vocab> wrongVocabs  = new ArrayList<>();
    public static ArrayList<Vocab> rightVocabs = new ArrayList<>();
    public static ArrayList<Vocab> skippedVocabs = new ArrayList<>();
    public static List<Integer> selectedLections;

    public static AskingSingleton getInstance() {
        return ourInstance;
    }

    private AskingSingleton() {
    }

    public static void resetData(){
        startingDate = (Date) Calendar.getInstance().getTime();
        endingDate = null;
        wrongVocabs = new ArrayList<>();
        rightVocabs = new ArrayList<>();
        skippedVocabs = new ArrayList<>();
    }
}
