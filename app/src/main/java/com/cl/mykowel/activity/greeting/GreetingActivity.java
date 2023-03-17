package com.cl.mykowel.activity.greeting;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.cl.mykowel.R;
import com.cl.mykowel.activity.MainActivity;
import com.cl.mykowel.activity.authorization.AuthorizationActivity;
@SuppressLint("SourceLockedOrientationActivity")

public class GreetingActivity extends AppCompatActivity {

    private Button button_login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greeting);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        Settings.System.putInt(getContentResolver(), Settings.System.ACCELEROMETER_ROTATION, 0);
        haveAccount();

        //button with greeting screen to login screen
        button_login = findViewById(R.id.loginButton);
    }

    //method for button loginButton with greeting screen to login screen
    public void PushToLoginScreen(View view) {
        startActivity(new Intent(GreetingActivity.this, AuthorizationActivity.class));

    }


    //відкривається SharedPreferences і якшо в нього є збережений токен тоді запускається MainScreen а якшо нема тоді greeting

    private void haveAccount(){
        SharedPreferences sharedPref = this.getSharedPreferences(String.valueOf(R.string.shared_preferences_user_data), Context.MODE_PRIVATE);
        //перевірка на наявність ключа "token"
        Log.d("token", sharedPref.getAll().toString());
        if (sharedPref.contains("token")) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}