package com.cl.mykowel.activity.reg;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.cl.mykowel.R;
import com.cl.mykowel.activity.main.MainActivity;
import com.cl.mykowel.activity.authorization.AuthorizationActivity;
import com.cl.mykowel.model.model_my_kovel.model_user.User;

@SuppressLint("SourceLockedOrientationActivity")

public class RegActivity extends AppCompatActivity {
    private EditText loginEditText;
    private EditText nameEditText;
    private EditText emailEditText;
    private EditText phoneEditText;
    private EditText passwordEditText;
    private TextView textView;

    private RegActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //ініціалізація edit texts;
        loginEditText = (EditText) findViewById(R.id.loginRegEditText);
        nameEditText = (EditText) findViewById(R.id.nameRegEditText);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        phoneEditText = (EditText) findViewById(R.id.phone_numberEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);

        textView = findViewById(R.id.textRegTextView);
        initViewModel();
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegActivity.this, AuthorizationActivity.class));

            }
        });
    }

    // збирає дані із edit texts і створює обєкт класу User

    private void createNewUser() {
        String login = loginEditText.getText().toString();
        String name = nameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String phone = phoneEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        User user = new User(login, name, email, phone, password);

        viewModel.createNewUser(user, this);
    }

    //стоїть наблюдач як дивиться за  MutableLiveData<User> і якшо в нього зміни то він виводить тост відповідний а також ініціалізує ViewModel

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(RegActivityViewModel.class);
        viewModel.getCreateUserObserver().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User userResponse) {
//                if(userResponse == null) {
//                    Toast.makeText(RegActivity.this, "failed to create new user", Toast.LENGTH_LONG).show();
//                } else {
//                    //https://gorest.co.in/public-api/users/1383
//                    //{"code":201,"meta":null,"data":{"id":1383,"name":"TestPOST123","email":"TestPOST123@Yahoo.com","gender":"male","status":"active"}}
//                    Toast.makeText(RegActivity.this, "Successfully created new user", Toast.LENGTH_LONG).show();
//                }
            }
        });
    }
    //кнопка яка перевірєю едіт тексти і якшо вони не пусті тоді ми робим пост запит і створюєм нового користувача


    public void RegUser(View view) {
        if (loginEditText.length() == 0) {

            loginEditText.setBackgroundResource(R.drawable.edit_text_border_error);
            loginEditText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_user_add_error, 0, 0, 0);

            loginEditText.setError("Заповніть це поле");
            return;
        } else {
            changeBackground(loginEditText);
            loginEditText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.selector_ic_login, 0, 0, 0);
        }


        if (nameEditText.length() == 0) {

            nameEditText.setBackgroundResource(R.drawable.edit_text_border_error);
            nameEditText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_user_edit_text_error, 0, 0, 0);

            nameEditText.setError("Заповніть це поле");
            return;
        } else {
            changeBackground(nameEditText);
            nameEditText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.selector_ic_name, 0, 0, 0);
        }

        if (emailEditText.length() == 0) {

            emailEditText.setBackgroundResource(R.drawable.edit_text_border_error);
            emailEditText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_sms_error, 0, 0, 0);

            emailEditText.setError("Заповніть це поле");
            return;
        } else {
            changeBackground(emailEditText);
            emailEditText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.selector_ic_email, 0, 0, 0);
        }


        if (phoneEditText.length() == 0) {

            phoneEditText.setBackgroundResource(R.drawable.edit_text_border_error);
            phoneEditText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_call_edit_text_error, 0, 0, 0);

            phoneEditText.setError("Заповніть це поле");
            return;
        } else {
            changeBackground(phoneEditText);
            phoneEditText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.selector_ic_phone, 0, 0, 0);
        }


        if (passwordEditText.length() == 0) {

            passwordEditText.setBackgroundResource(R.drawable.edit_text_border_error);
            passwordEditText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_lock_error, 0, 0, 0);

            passwordEditText.setError("Заповніть це поле");
            return;
        } else {
            changeBackground(passwordEditText);
            passwordEditText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.selector_ic_password, 0, 0, 0);
        }


        createNewUser();
        startActivity(new Intent(RegActivity.this, MainActivity.class));
    }

    private void changeBackground(@NonNull EditText editText) {
        editText.setBackgroundResource(R.drawable.edit_text_border);
    }


}