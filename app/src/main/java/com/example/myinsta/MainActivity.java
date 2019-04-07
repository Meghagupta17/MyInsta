package com.example.myinsta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText email;
    EditText username;
    EditText nickname;
    Button btnlogin;
    Button btnsignup;
    Button btnback;
    TextView newuser;
    char SigninbtnState;
    char SignupbtnState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        username = findViewById(R.id.userName);
        nickname = findViewById(R.id.nickName);
        newuser = findViewById(R.id.newuser);
        btnsignup = findViewById(R.id.btnsignup);
        btnback = findViewById(R.id.btnback);
        SigninbtnState = 'L';
        SignupbtnState = 'S';

        //On first screen email id, signin button, new user?, signup button is visible
        username.setVisibility(EditText.INVISIBLE);
        nickname.setVisibility(EditText.INVISIBLE);
        btnback.setVisibility(EditText.INVISIBLE);
        //btnsignup.setVisibility(View.INVISIBLE);
        //newuser.setVisibility(EditText.INVISIBLE);

        btnlogin = findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String s1 = email.getText().toString();
                    //String s2 = username.getText().toString();
                    //String s3 = nickname.getText().toString();

                    if (!TextUtils.isEmpty(email.getText())) {
                        Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, LogedInActivity.class);
                        startActivity(intent);

                    } else {
                        email.setError("Email id is required!");

                    }
                }
        });

        //onSignup button Signin button and new user textview is Invisible
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s1 = email.getText().toString();
                String s2 = username.getText().toString();
                String s3 = nickname.getText().toString();
                btnlogin.setVisibility(EditText.INVISIBLE);
                newuser.setVisibility(TextView.INVISIBLE);
                username.setVisibility(EditText.VISIBLE);
                nickname.setVisibility(EditText.VISIBLE);
                btnback.setVisibility(EditText.VISIBLE);

                if (!TextUtils.isEmpty(email.getText())) {

                    if (!TextUtils.isEmpty(username.getText())) {

                        if (!TextUtils.isEmpty(nickname.getText())) {
                            Toast.makeText(MainActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, LogedInActivity.class);
                            startActivity(intent);
                        }else{
                            nickname.setError("Nick Name is required!");
                        }
                    }else{
                        username.setError("User Name is required!");
                    }
                }else{
                    email.setError("Email id is required!");
                }
            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = email.getText().toString();
                username.setVisibility(EditText.INVISIBLE);
                nickname.setVisibility(EditText.INVISIBLE);
                btnback.setVisibility(EditText.INVISIBLE);
                btnlogin.setVisibility(EditText.VISIBLE);
                btnsignup.setVisibility(EditText.VISIBLE);
                newuser.setVisibility(EditText.VISIBLE);

            }
        });
    }
}
