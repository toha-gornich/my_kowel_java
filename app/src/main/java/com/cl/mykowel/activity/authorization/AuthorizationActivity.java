package com.cl.mykowel.activity.authorization;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.cl.mykowel.R;
import com.cl.mykowel.activity.main.MainActivity;
import com.cl.mykowel.activity.reg.RegActivity;
import com.cl.mykowel.model.model_my_kovel.model_user.User;
@SuppressLint("SourceLockedOrientationActivity")
public class AuthorizationActivity extends AppCompatActivity {
    private AuthorizationViewModel viewModel;
    private EditText loginEditText;
    private EditText passwordEditText;
    private TextView textReg;
    private  View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        viewModel = new ViewModelProvider(this).get(AuthorizationViewModel.class);

        loginEditText = (EditText)findViewById(R.id.loginEditText);
        passwordEditText = (EditText)findViewById(R.id.passwordEditText);

        textReg = (TextView)findViewById(R.id.textRegTextView);
//        view.setBackgroundResource(R.drawable.edit_text_border_error);
        initViewModel();

        textReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AuthorizationActivity.this, RegActivity.class));
            }
        });


    }

    public void PushToMainScreen(View view) {

        if(loginEditText.length()==0){

            loginEditText.setBackgroundResource(R.drawable.edit_text_border_error);
            loginEditText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_user_add_error,0,0,0);

            loginEditText.setError("Заповніть це поле");
            return;
        }else {changeBackground(loginEditText);
            loginEditText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.selector_ic_login,0,0,0);
        }

        if(passwordEditText.length()==0){
            passwordEditText.setBackgroundResource(R.drawable.edit_text_border_error);
            passwordEditText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_lock_error,0,0,0);

            passwordEditText.setError("Заповніть це поле");
            return;
        }else {
            changeBackground(passwordEditText);
            passwordEditText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.selector_ic_password,0,0,0);

        }


        createNewUser();


    }


     private void changeBackground(@NonNull EditText editText){
         editText.setBackgroundResource(R.drawable.edit_text_border);
    }


    private void initViewModel(){

        viewModel.getCreateUserObserver().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User userResponse) {
                if(userResponse == null) {
                    Toast.makeText(AuthorizationActivity.this, "Пароль або логін введені не правильно", Toast.LENGTH_LONG).show();
                } else {
                    //https://gorest.co.in/public-api/users/1383
                    //{"code":201,"meta":null,"data":{"id":1383,"name":"TestPOST123","email":"TestPOST123@Yahoo.com","gender":"male","status":"active"}}
//                    Toast.makeText(AuthorizationActivity.this, "Successfully created new user", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(AuthorizationActivity.this, MainActivity.class));
                }
            }
        });

    }
    private void createNewUser() {
        String login  = loginEditText.getText().toString();

        String password  = passwordEditText.getText().toString();
        User user = new User(login, password);

        viewModel.createNewUser(user,this);
    }


}