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

    //componenets in Logedin Activity
    Button btnmakePost;
    Button btnuserList;
    Button btnhastagList;
    Button btnPostList;
    private ArrayList<String> userNameList = new ArrayList<>();
    private ArrayList<String> hashtagList = new ArrayList<>();
    private ArrayList<String> postimage = new ArrayList<>();
    private ArrayList<String> postuser = new ArrayList<>();
    private ArrayList<String> posthasgtag = new ArrayList<>();


    //componenets of create new post Dialog box
    Button btnPost;
    Button btnuploadImage;
    ImageView image;
    EditText postText;
    EditText addHashtag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Make a post Dialog Box
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_create_post, null);

        //Chail components of dialog box make post
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

        //Creating the dialog box on click of btnpost(Make a post)
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

        final AlertDialog.Builder builderpost = new AlertDialog.Builder(this);
        final View dialogViewpost = LayoutInflater.from(this).inflate(R.layout.dialog_recyclerview_postlist, null);

        btnPostList = findViewById(R.id.btnPostList);
        btnPostList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builderpost.setView(dialogViewpost);
                AlertDialog alertDialog = builderpost.create();
                initPost();
                RecyclerView recyclerView = dialogViewpost.findViewById(R.id.rvpostList);
                RecyclerViewAdapterPost adapterPost = new RecyclerViewAdapterPost(LogedInActivity.this, postimage, postuser, posthasgtag);
                recyclerView.setAdapter(adapterPost);
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

    private void initPost(){
        postimage.add("https://www.telegraph.co.uk/content/dam/Travel/2016/August/san-diego-AP75672386.jpg?imwidth=1400");
        postuser.add("willow");
        posthasgtag.add("#san diego");
        postimage.add("https://www.trolleytours.com/wp-content/uploads/2016/06/san-diego-beaches-480x270.jpg");
        postuser.add("logan");
        posthasgtag.add("#beaches");
        postimage.add("http://www.solar-nation.org/images/sandiegocasolarpower.jpg");
        postuser.add("sofia");
        posthasgtag.add("#sunny");
    }

}
