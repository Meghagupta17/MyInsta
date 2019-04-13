package com.example.myinsta;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText email;
    EditText username;
    EditText nickname;
    Button btnlogin;
    Button btnsignup;
    Button btnback;
    TextView newuser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    List<String> emailidList;
    List<String> usernameList;
    List<String> nicknameList;

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
        emailidList = new ArrayList<>();
        usernameList = new ArrayList<>();
        nicknameList = new ArrayList<>();


        FirebaseApp.initializeApp(this);
        firebaseDatabase  = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference("users");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                emailidList.clear();
                usernameList.clear();
                nicknameList.clear();
                for (DataSnapshot ds:dataSnapshot.getChildren()) {
                    String key = ds.getKey().toString();
                    Users u = ds.getValue(Users.class);
                    emailidList.add(u.emailId);
                    usernameList.add(u.userName);
                    nicknameList.add(u.nickName);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //On first screen email id, signin button, new user?, signup button is visible
        username.setVisibility(EditText.INVISIBLE);
        nickname.setVisibility(EditText.INVISIBLE);
        btnback.setVisibility(EditText.INVISIBLE);

        btnlogin = findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = email.getText().toString();

                if (!TextUtils.isEmpty(email.getText())) {
                    if (!emailidList.contains(s1)) {
                            email.setError("User not Found");
                    }else {
                            int index = emailidList.indexOf(s1);
                            Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, LogedInActivity.class);
                            intent.putExtra("nickname", nicknameList.get(index));
                            startActivity(intent);
                    }
                }else{
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
                           if (emailidList.contains(s1)){
                               email.setError("User Id already exists");
                           }else {
                               String uniqueKey = myRef.push().getKey();
                               Users u = new Users(s1,s2,s3);
                               myRef.child(uniqueKey).setValue(u);
                               Toast.makeText(MainActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                               Intent intent = new Intent(MainActivity.this, LogedInActivity.class);
                               intent.putExtra("nickname", s3);
                               startActivity(intent);
                           }

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
