package com.example.android.fridger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogInActivity extends AppCompatActivity {
//    private static int SPLASH_TIME_OUT = 4000;

    EditText name;
    EditText password;
    Button login;

    UserInfo[] userInfos = {
            new UserInfo("admin", "pass"),
            new UserInfo("Raadhesh", "R123"),
            new UserInfo("Jerin", "zeba1"),
            new UserInfo("Ahmed", "D@kir")
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        name = (EditText)findViewById(R.id.editTextName);
        password = (EditText)findViewById(R.id.editTextPassword);
        login = (Button)findViewById(R.id.submitButton);

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
//                startActivity(loginIntent);
//                finish();
//            }
//        }, SPLASH_TIME_OUT);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(name.getText().toString(), password.getText().toString());
            }
        });
    }


    private void validate(String userName, String userPassword){

        for(int i = 0; i < userInfos.length ; i++){
            if (userName.equals(userInfos[i].getUserName())) {
                if (userPassword.equals(userInfos[i].getUserPassword())) {
                    Toast.makeText(LogInActivity.this, "Welcome, " + userName, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                    startActivity(intent);
                    break;
                }
                else {
//                    Toast.makeText(LogInActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(LogInActivity.this, "Wrong Username or Password", Toast.LENGTH_SHORT).show();
            }
        }
//
//        Jerin's way
//        if((userName.equals("Raadhesh")) && (userPassword.equals("R123"))||
//                (userName.equals("Jerin")) && (userPassword.equals("zeba1"))||
//                (userName.equals("Ahmed")) && (userPassword.equals("D@kir"))){
//            Intent intent = new Intent(LogInActivity.this,MainActivity.class);
//            startActivity(intent);
//        }
//        else{
//            String msg = "WRONG PASSWORD!";
//            Toast. makeText (LogInActivity.this, msg, Toast. LENGTH_SHORT ).show();
//
//        }
    }
}
