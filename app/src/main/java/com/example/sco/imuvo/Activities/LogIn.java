package com.example.sco.imuvo.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sco.imuvo.HelperClasses.GeneralDatabaseHelper;
import com.example.sco.imuvo.HelperClasses.InitData;
import com.example.sco.imuvo.HelperClasses.UserDatabaseHelper;
import com.example.sco.imuvo.HelperClasses.Helper;
import com.example.sco.imuvo.R;
import com.example.sco.imuvo.Model.User;

public class LogIn extends AppCompatActivity {

    TextView welcomeTextView, bubbleTextView;
    Button startButton;
    EditText nameEditText, passwordEditText;
    public UserDatabaseHelper userDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        getElements();
        testFunction();
        setInitData();
        initSQLData();

    }

    private void initSQLData() {
        InitData.initSQLData(this);
    }

    private void setInitData() {
        bubbleTextView.setText("Wilkommen! Ich bin Imuvo. Wie ist dein Name? " +
                "TÖRÖÖÖÖÖ!");
        bubbleTextView.setTextColor(Color.parseColor("#FFFFFF"));
    }

    private void testFunction() {
        nameEditText.setText("S");
        passwordEditText.setText("0");
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
            Bundle bundle = new Bundle();
            bundle.putString("username",nameEditText.getText().toString());
            menuIntent.putExtras(bundle);
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
        Bundle bundle = new Bundle();
        bundle.putString("username",nameEditText.getText().toString());
        menuIntent.putExtras(bundle);
        startActivity(menuIntent);
    }
}
