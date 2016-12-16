package com.example.sco.imuvo.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.sco.imuvo.HelperClasses.UserDatabaseHelper;
import com.example.sco.imuvo.HelperClasses.Helper;
import com.example.sco.imuvo.Model.User;
import com.example.sco.imuvo.R;

public class CreateUserActivity extends AppCompatActivity {

    EditText nameEditText, passwordEditText;
    public UserDatabaseHelper userDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_user);
        userDatabaseHelper = UserDatabaseHelper.getInstance(this);
        nameEditText = (EditText) findViewById(R.id.name);
        passwordEditText = (EditText) findViewById(R.id.password);
    }

    public void onClickCreateUser(View v){
        if(checkUserCorrect()){
            User user = new User(0,nameEditText.getText().toString(),passwordEditText.getText().toString());
            userDatabaseHelper.insert(user);
            Helper.makeLongToast(this,"Nutzer wurde angelegt.");
            final Intent menuIntent = new Intent(this,LogIn.class);
            startActivity(menuIntent);
        }
        else{
            Helper.makeLongToast(this,"Name oder Passwort sind falsch. Bitte versuche es erneut.");
        }
    }

    public boolean checkUserCorrect(){
        return true;
    }

}
