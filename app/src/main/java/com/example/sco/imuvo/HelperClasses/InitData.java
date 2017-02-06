package com.example.sco.imuvo.HelperClasses;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;

import com.example.sco.imuvo.Activities.LogIn;
import com.example.sco.imuvo.Activities.readVocabs;
import com.example.sco.imuvo.Model.Lection;
import com.example.sco.imuvo.Model.User;
import com.example.sco.imuvo.Model.Vocab;
import com.example.sco.imuvo.R;
import com.voicerss.tts.AudioCodec;
import com.voicerss.tts.AudioFormat;
import com.voicerss.tts.Languages;
import com.voicerss.tts.SpeechDataEvent;
import com.voicerss.tts.SpeechDataEventListener;
import com.voicerss.tts.SpeechErrorEvent;
import com.voicerss.tts.SpeechErrorEventListener;
import com.voicerss.tts.VoiceParameters;
import com.voicerss.tts.VoiceProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Simon Cox on 17.12.2016.
 */

public class InitData {
    static Context glbContext;

    private static void insertLection(Context context){
        LectionDatabaseHelper db = LectionDatabaseHelper.getInstance(context);
        if(!db.getAll().moveToFirst()){
            insertLection(1,"Englisch",db);
            insertLection(2,"Englisch",db);
            insertLection(3,"Englisch",db);
            insertLection(4,"Englisch",db);
            insertLection(5,"Englisch",db);
            insertLection(6,"Englisch",db);
        }
    }

    private static void insertLection(int i, String englisch, LectionDatabaseHelper db) {
        db.insert(new Lection(i,englisch));
    }

    private static void insertUser(String nutzer, String passwort,UserDatabaseHelper db) {
        db.insert(new User(nutzer,passwort));
    }

    private static void insertUser(Context context){
        UserDatabaseHelper db = UserDatabaseHelper.getInstance(context);
        if(!db.getAll().moveToFirst()){
            insertUser("Simon","Simon",db);
            insertUser("User","Passwort",db);
        }
    }

    private static void insertVocabsFromAsset(Context context){

    }

    private static void insertVocabs(Context context) {
        final VocabDatabaseHelper db = VocabDatabaseHelper.getInstance(context);

        if(!db.getAll().moveToFirst()) {

            insertVocab("Hallo", "Hello", 1, db);
            insertVocab("guten Morgen", "good morning", 1, db);
            insertVocab("Wie heißt du", "What is your name", 1, db);
            insertVocab("Ich heiße", "My name is", 1, db);
            insertVocab("Name", "name", 1, db);
            insertVocab("wie geht es dir", "How are you", 1, db);
            insertVocab("Mir geht es gut", "I am fine", 1, db);
            insertVocab("Dankeschön", "thank you", 1, db);
            insertVocab("Bitte", "please", 1, db);
            insertVocab("Auf wiedersehen", "Good bye", 1, db);
            insertVocab("Bis bald", "See you soon", 1, db);
            insertVocab("Ich wohne in", "I live in", 1, db);
            insertVocab("schön dich kennen zu lernen", "Nice to meet you", 1, db);
            insertVocab("Ich bin", "I am", 1, db);
            insertVocab("Du bist", "you are", 1, db);
            insertVocab("Wir sind", "we are", 1, db);
            insertVocab("ihr seit", "you are", 1, db);
            insertVocab("er ist", "he is", 1, db);
            insertVocab("sie ist", "she is", 1, db);
            insertVocab("es ist", "it is", 1, db);
            insertVocab("Sie sind", "they are", 1, db);
            insertVocab("und", "and", 1, db);

            insertVocab("Mutter", "mother", 2, db);
            insertVocab("Vater", "father", 2, db);
            insertVocab("Bruder", "brother", 2, db);
            insertVocab("Schwester", "sister", 2, db);
            insertVocab("Oma", "Grandma", 2, db);
            insertVocab("Opa", "Grandpa", 2, db);
            insertVocab("Cousine /Cousin", "cousine", 2, db);
            insertVocab("Tante", "aunt", 2, db);
            insertVocab("Onkel", "uncle", 2, db);
            insertVocab("Das ist mein /meine", "This is my", 2, db);
            insertVocab("Ich habe", "I have", 2, db);
            insertVocab("ein", "a", 2, db);
            insertVocab("Baby", "baby", 2, db);
            insertVocab("Familie", "family", 2, db);
            insertVocab("Verwandte", "relatives", 2, db);

            insertVocab("1", "one", 3, db);
            insertVocab("2", "two", 3, db);
            insertVocab("3", "three", 3, db);
            insertVocab("4", "four", 3, db);
            insertVocab("5", "five", 3, db);
            insertVocab("6", "six", 3, db);
            insertVocab("7", "seven", 3, db);
            insertVocab("8", "eight", 3, db);
            insertVocab("9", "nine", 3, db);
            insertVocab("10", "ten", 3, db);
            insertVocab("rot", "red", 3, db);
            insertVocab("grün", "green", 3, db);
            insertVocab("gelb", "yellow", 3, db);
            insertVocab("blau", "blue", 3, db);
            insertVocab("schwarz", "black", 3, db);
            insertVocab("weiß", "white", 3, db);
            insertVocab("braun", "brown", 3, db);
            insertVocab("orange", "orange", 3, db);
            insertVocab("grau", "grey", 3, db);
            insertVocab("lila", "purple", 3, db);
            insertVocab("rosa", "pink", 3, db);

            insertVocab("Hund", "dog", 4, db);
            insertVocab("Katze", "cat", 4, db);
            insertVocab("Maus", "mouse", 4, db);
            insertVocab("Vogel", "bird", 4, db);
            insertVocab("Ente", "duck", 4, db);
            insertVocab("Schmetterling", "butterfly", 4, db);
            insertVocab("Schildkröte", "turtle", 4, db);
            insertVocab("Kuh", "cow", 4, db);
            insertVocab("Pferd", "horse", 4, db);
            insertVocab("Huhn", "chicken", 4, db);
            insertVocab("Schwein", "pig", 4, db);
            insertVocab("Schaf", "sheep", 4, db);
            insertVocab("Ameisenbär", "anteater", 4, db);
            insertVocab("Antilope", "antelope", 4, db);
            insertVocab("Bär", "bear", 4, db);
            insertVocab("Biber", "beaver", 4, db);
            insertVocab("Dachs", "badger", 4, db);
            insertVocab("Dackel", "dachshund", 4, db);
            insertVocab("Delfin", "dolphin", 4, db);
            insertVocab("Eichhörnchen", "squirrel", 4, db);
            insertVocab("Eisbär", "polar bear", 4, db);
            insertVocab("Elefant", "elephant", 4, db);
            insertVocab("Elch", "moose", 4, db);
            insertVocab("Faultier", "sloth", 4, db);
            insertVocab("Feldhase", "hare", 4, db);
            insertVocab("Fischotter", "otter", 4, db);
            insertVocab("Fledermaus", "bat", 4, db);
            insertVocab("Fuchs", "fox", 4, db);

            insertVocab("Lehrer", "teacher", 5, db);
            insertVocab("Schüler", "pupil", 5, db);
            insertVocab("Schreibtisch", "desk", 5, db);
            insertVocab("Stuhl", "chair", 5, db);
            insertVocab("Stift", "pencil", 5, db);
            insertVocab("Radiergummi", "rubber", 5, db);
            insertVocab("Buch", "book", 5, db);
            insertVocab("Blatt", "paper", 5, db);
            insertVocab("Mülleimer", "bin", 5, db);
            insertVocab("Schere", "scissors", 5, db);
            insertVocab("Klassenraum", "classroom", 5, db);
            insertVocab("Tafel", "blackboard", 5, db);
            insertVocab("Schwamm", "sponge", 5, db);
            insertVocab("Kreide", "chalk", 5, db);
            insertVocab("Klassenarbeit", "classtest", 5, db);
            insertVocab("Kleber", "glue", 5, db);
            insertVocab("Fenster", "window", 5, db);
            insertVocab("Schulstunde", "lesson", 5, db);
            insertVocab("Schule", "school", 5, db);
            insertVocab("Stundenplan", "schedule", 5, db);
            insertVocab("Pause", "break", 5, db);

            insertVocab("Frühstück", "breakfast", 6, db);
            insertVocab("Mittagessen", "lunch", 6, db);
            insertVocab("Abendessen", "dinner", 6, db);
            insertVocab("Eis", "icecream", 6, db);
            insertVocab("Schokolade", "chocolate", 6, db);
            insertVocab("Suppe", "soup", 6, db);
            insertVocab("Spaghetti", "spaghetti", 6, db);
            insertVocab("Pommes frites", "chips", 6, db);
            insertVocab("Apfel", "apple", 6, db);
            insertVocab("Banane", "banana", 6, db);
            insertVocab("Birne", "pear", 6, db);
            insertVocab("Trauben", "grape", 6, db);
            insertVocab("Nachtisch", "desert", 6, db);
            insertVocab("Brot", "bread", 6, db);
            insertVocab("Butter", "butter", 6, db);
            insertVocab("Käse", "cheese", 6, db);
            insertVocab("Marmelade", "jelly", 6, db);
            insertVocab("essen", "eat", 6, db);
            insertVocab("kochen", "cook", 6, db);
            insertVocab("Küche", "kitchen", 6, db);

        }

    }

    public static void insertVocabWithSpeech(final Vocab currVocab, final VocabDatabaseHelper db){
        final String text = currVocab.getForeign();

        try {

                InputStream is = glbContext.getAssets().open("speech/" + currVocab.getForeign() + ".mp3");
                int size = is.available();
                byte[] buffer = new byte[size]; //declare the size of the byte array with size of the file
                is.read(buffer); //read file
                is.close(); //close file
                currVocab.setSpeech((byte[]) buffer);


                InputStream is2 = glbContext.getAssets().open("picture/" + currVocab.getForeign() + ".png");
                int size2 = is2.available();
                byte[] buffer2 = new byte[size2]; //declare the size of the byte array with size of the file
                is2.read(buffer2); //read file
                is2.close(); //close file
                currVocab.setPicture((byte[]) buffer2);

                Log.i("Insert", currVocab.toString());





        } catch (Exception e) {
            Log.i("Insert",e.toString());
        }
        db.insert(currVocab);
    }

    private static void insertVocab(String deutsch, String german, int i, VocabDatabaseHelper db) {
        insertVocabWithSpeech(new Vocab(deutsch,german,i),db);
    }

    public static void initSQLData(Context context) {
        GeneralDatabaseHelper generalDatabaseHelper = GeneralDatabaseHelper.getInstance(context);

        //generalDatabaseHelper.dropDatabase(context);
        generalDatabaseHelper.Create();
        glbContext =context;
       if(generalDatabaseHelper.checkDataBase()){
            insertLection(context);
            insertUser(context);
            insertVocabs(context);
       }

    }
}
