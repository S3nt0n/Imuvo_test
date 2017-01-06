package com.example.sco.imuvo.Activities;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sco.imuvo.HelperClasses.InitData;
import com.example.sco.imuvo.HelperClasses.TypefaceUtil;
import com.example.sco.imuvo.HelperClasses.UserDatabaseHelper;
import com.example.sco.imuvo.HelperClasses.Helper;
import com.example.sco.imuvo.Model.SingletonUser;
import com.example.sco.imuvo.R;
import com.example.sco.imuvo.Model.User;

public class LogIn extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    TextView welcomeTextView, bubbleTextView;
    Button startButton;
    EditText nameEditText, passwordEditText;
    public UserDatabaseHelper userDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);


            }
        }
        super.onCreate(savedInstanceState);
        TypefaceUtil.overrideFont(this,"SERIF","fonts");
        setContentView(R.layout.activity_log_in);
        getElements();
        //testFunction();
        setInitData();
        initSQLData(this);

    }

    private void initSQLData(final Context context) {
        new Thread(new Runnable() {
            public void run() {
                InitData.initSQLData(context);
            }
        }).start();


    }

    private void setInitData() {
        bubbleTextView.setText("Wilkommen! Ich bin Imuvo. Wie ist dein Name? " +
                "TÖRÖÖÖÖÖ!");
        bubbleTextView.setTextColor(Color.parseColor("#FFFFFF"));
        welcomeTextView.setText(Helper.colorsString("Lernen mit Imuvo", ContextCompat.getColor(this, R.color.colorMenuTextLeft),ContextCompat.getColor(this, R.color.colorMenuTextMiddle),ContextCompat.getColor(this, R.color.colorMenuTextRight)));
    }

    private void testFunction() {
        nameEditText.setText("Simon");
        passwordEditText.setText("Simon");
    }

    public void getElements(){
        welcomeTextView = (TextView) findViewById(R.id.welcomeText);
        startButton = (Button) findViewById(R.id.start);
        nameEditText = (EditText) findViewById(R.id.name);
        passwordEditText = (EditText) findViewById(R.id.password);
        bubbleTextView = (TextView) findViewById(R.id.bubbleTextLogIn);
        userDatabaseHelper = UserDatabaseHelper.getInstance(this);
    }

    public void onClickStart(View v){
        if(checkUserCorrect()){
            final Intent menuIntent = new Intent(this,MenuImuvo.class);
            String username = nameEditText.getText().toString();
            SingletonUser.data = username;
            nameEditText.setText("");
            passwordEditText.setText("");
            startActivity(menuIntent);
        }
        else{
            Helper.makeLongToast(this,"Name oder Passwort sind falsch. Bitte versuche es erneut.");
        }
    }

    private boolean checkUserCorrect(){
        User user = null;
        user = userDatabaseHelper.get(nameEditText.getText().toString());
        try{
            if (user.getPassword().contentEquals(passwordEditText.getText())){
                return true;
            }
            else
                return false;
        }
        catch (NullPointerException e){
            return false;
        }

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menulogin,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.create_new:
                newUser();
                return true;
            case R.id.showVocabs:
                showVocabs();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showVocabs() {
        final Intent menuIntent = new Intent(this,LectionList.class);
        startActivity(menuIntent);
    }

    private void newUser() {
        final Intent menuIntent = new Intent(this,CreateUserActivity.class);
        //Bundle bundle = new Bundle();
        //bundle.putString("username",nameEditText.getText().toString());
        //menuIntent.putExtras(bundle);
        String username = nameEditText.getText().toString();
        SingletonUser.data = username;
        startActivity(menuIntent);
    }
}
