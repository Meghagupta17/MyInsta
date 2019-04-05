package com.example.myinsta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText email, username, nickname;
    Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        username = findViewById(R.id.userName);
        nickname = findViewById(R.id.nickName);

        //username.setVisibility(EditText.INVISIBLE);;
       // nickname.setEnabled(false);
        btnlogin = findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s1 = email.getText().toString();
                String s2 = username.getText().toString();
                String s3 = nickname.getText().toString();

                if (!TextUtils.isEmpty(email.getText())) {

                    if (!TextUtils.isEmpty(username.getText())) {

                        if (!TextUtils.isEmpty(nickname.getText())) {
                            Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, LogedInActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(MainActivity.this, "Fields can not be left Empty", Toast.LENGTH_SHORT).show();
                            nickname.setError("Nick Name is required!");
                        }
                    }else{
                        Toast.makeText(MainActivity.this, "Fields can not be left Empty", Toast.LENGTH_SHORT).show();
                        username.setError("User Name is required!");
                    }
                }else{
                    Toast.makeText(MainActivity.this, "Fields can not be left Empty", Toast.LENGTH_SHORT).show();
                    email.setError("Email id is required!");

                }
            }
        });


    }
}
