package com.example.sco.imuvo.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sco.imuvo.CustomViews.ButtonJokerman;
import com.example.sco.imuvo.HelperClasses.UserDatabaseHelper;
import com.example.sco.imuvo.HelperClasses.Helper;
import com.example.sco.imuvo.R;
import com.example.sco.imuvo.Model.User;

public class LogIn extends AppCompatActivity {

    TextView welcomeTextView;
    Button startButton;
    EditText nameEditText, passwordEditText;
    public UserDatabaseHelper userDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        getElements();
        testFunction();
        userDatabaseHelper = UserDatabaseHelper.getInstance(this);
        userDatabaseHelper.Create();
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
    }

    public void onClickStart(View v){
        if(checkUserCorrect()){
            final Intent menuIntent = new Intent(this,Menu.class);
            Bundle bundle = new Bundle();
            bundle.putString("username",nameEditText.getText().toString());
            menuIntent.putExtras(bundle);
            startActivity(menuIntent);
        }
        else{
            Helper.makeLongToast(this,"Name oder Passwort sind falsch. Bitte versuche es erneut.");
        }
    }

    public void onClickRegister(View v){
        final Intent menuIntent = new Intent(this,CreateUserActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("username",nameEditText.getText().toString());
        menuIntent.putExtras(bundle);
        startActivity(menuIntent);
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
}