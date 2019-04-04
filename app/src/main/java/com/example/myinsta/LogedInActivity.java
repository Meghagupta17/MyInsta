package com.example.myinsta;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;

public class LogedInActivity extends AppCompatActivity {

    Button btnPost;
    Button btnuploadImage;
    Button btnmakePost;
    Button btnuserList;
    Button btnhastagList;
    ImageView image;
    EditText postText, addHashtag, rvtext, rvhashtag;
    private ArrayList<String> userNameList = new ArrayList<>();
    private ArrayList<String> hashtagList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_create_post, null);

        image = dialogView.findViewById(R.id.image);
        postText = dialogView.findViewById(R.id.postText);
        addHashtag = dialogView.findViewById(R.id.addHashtag);

        btnuploadImage = dialogView.findViewById(R.id.btnuploadImage);
        btnuploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnmakePost = dialogView.findViewById(R.id.btnmakePost);
        btnmakePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        btnPost = findViewById(R.id.btnPost);
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setView(dialogView);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        //Dialog box for userlist button
        final AlertDialog.Builder builderuser = new AlertDialog.Builder(this);
        final View dialogViewuser = LayoutInflater.from(this).inflate(R.layout.dialog_recyclerview_userlist, null);

        btnuserList = findViewById(R.id.btnuserList);
        btnuserList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builderuser.setView(dialogViewuser);
                AlertDialog alertDialog = builderuser.create();
                initUser();
                RecyclerView recyclerView = dialogViewuser.findViewById(R.id.rvuserList);
                RecyclerViewAdapterUser adapterUser = new RecyclerViewAdapterUser(LogedInActivity.this, userNameList);
                recyclerView.setAdapter(adapterUser);
                recyclerView.setLayoutManager(new LinearLayoutManager(LogedInActivity.this ));
                alertDialog.show();
            }
        });

        //Dialogbox for hashtag list
        final AlertDialog.Builder builderhashtag = new AlertDialog.Builder(this);
        final View dialogViewhashtag = LayoutInflater.from(this).inflate(R.layout.dialog_recycleview_hashtaglist, null);

        btnhastagList = findViewById(R.id.btnhastagList);
        btnhastagList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builderhashtag.setView(dialogViewhashtag);
                AlertDialog alertDialog = builderhashtag.create();
                initHashtag();
                RecyclerView recyclerView = dialogViewhashtag.findViewById(R.id.rvhashtagList);
                RecyclerViewAdapterHashtag adapterHashtag = new RecyclerViewAdapterHashtag(LogedInActivity.this, hashtagList);
                recyclerView.setAdapter(adapterHashtag);
                recyclerView.setLayoutManager(new LinearLayoutManager(LogedInActivity.this ));
                alertDialog.show();
            }
        });
    }

    private void initUser(){
        userNameList.add("Willow");
        userNameList.add("Logan");
        userNameList.add("Sofia");
    }

    private void initHashtag(){
        hashtagList.add("#SanDiedo");
        hashtagList.add("#sunny");
        hashtagList.add("#beaches");

    }

    /*private void initRecyclerView(){
        RecyclerView recyclerView = dialogViewuser.findViewById(R.id.rvuserList);
        RecyclerViewAdapterUser adapterUser = new RecyclerViewAdapterUser(LogedInActivity.this, userNameList);
        recyclerView.setAdapter(adapterUser);
        recyclerView.setLayoutManager(new LinearLayoutManager(LogedInActivity.this));
    }*/
}
