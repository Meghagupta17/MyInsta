package com.example.myinsta;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class LogedInActivity extends AppCompatActivity {

    //components in LogedIn Activity
    Button btnmakePost;
    Button btnuserList;
    Button btnhastagList;
    Button btnPostList;
    private ArrayList<String> userNameList = new ArrayList<>();
    private ArrayList<String> hashtagList = new ArrayList<>();
    private ArrayList<Post> postList = new ArrayList<>();
    private Uri imageUri;
    private int PICK_IMAGE_REQUEST = 1;

    //components of create new post Dialog box
    Button btnPost;
    Button btnuploadImage;
    ImageView image;
    EditText postText;
    EditText addHashtag;
    AlertDialog alertDialog;
    AlertDialog alertDialoguser;
    AlertDialog alertDialoghashtag;
    AlertDialog alertDialogpost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Make a post Dialog Box
        final AlertDialog.Builder builder = new AlertDialog.Builder(this, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        final View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_create_post, null);

        //components of dialog box to make post
        image = dialogView.findViewById(R.id.image);
        postText = dialogView.findViewById(R.id.postText);
        addHashtag = dialogView.findViewById(R.id.addHashtag);

        btnuploadImage = dialogView.findViewById(R.id.btnuploadImage);
        btnuploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        btnmakePost = dialogView.findViewById(R.id.btnmakePost);
        btnmakePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //Creating the dialog box on click of btnpost(Make a post)

        builder.setView(dialogView);
        alertDialog = builder.create();

        btnPost = findViewById(R.id.btnPost);
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.show();
            }
        });

        //Dialog box for userlist button
        final AlertDialog.Builder builderuser = new AlertDialog.Builder(this, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        final View dialogViewuser = LayoutInflater.from(this).inflate(R.layout.dialog_recyclerview_userlist, null);

        builderuser.setView(dialogViewuser);
        alertDialoguser = builderuser.create();

        btnuserList = findViewById(R.id.btnuserList);
        btnuserList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initUser();
                RecyclerView recyclerView = dialogViewuser.findViewById(R.id.rvuserList);
                RecyclerViewAdapterUser adapterUser = new RecyclerViewAdapterUser(LogedInActivity.this, userNameList);
                recyclerView.setAdapter(adapterUser);
                recyclerView.setLayoutManager(new LinearLayoutManager(LogedInActivity.this));
                alertDialoguser.show();
            }
        });

        //Dialogbox for hashtag list
        final AlertDialog.Builder builderhashtag = new AlertDialog.Builder(this, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        final View dialogViewhashtag = LayoutInflater.from(this).inflate(R.layout.dialog_recycleview_hashtaglist, null);

        builderhashtag.setView(dialogViewhashtag);
        alertDialoghashtag = builderhashtag.create();

        btnhastagList = findViewById(R.id.btnhastagList);
        btnhastagList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initHashtag();
                RecyclerView recyclerView = dialogViewhashtag.findViewById(R.id.rvhashtagList);
                RecyclerViewAdapterHashtag adapterHashtag = new RecyclerViewAdapterHashtag(LogedInActivity.this, hashtagList);
                recyclerView.setAdapter(adapterHashtag);
                recyclerView.setLayoutManager(new LinearLayoutManager(LogedInActivity.this));
                alertDialoghashtag.show();
            }
        });

        ////Dialogbox for post list
        final AlertDialog.Builder builderpost = new AlertDialog.Builder(this, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        final View dialogViewpost = LayoutInflater.from(this).inflate(R.layout.dialog_recyclerview_postlist, null);

        builderpost.setView(dialogViewpost);
        alertDialogpost = builderpost.create();

        btnPostList = findViewById(R.id.btnPostList);
        btnPostList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPost();
                RecyclerView recyclerView = dialogViewpost.findViewById(R.id.rvpostList);
                RecyclerViewAdapterPost adapterPost = new RecyclerViewAdapterPost(LogedInActivity.this, postList);
                recyclerView.setAdapter(adapterPost);
                recyclerView.setLayoutManager(new LinearLayoutManager(LogedInActivity.this));
                alertDialogpost.show();
            }
        });
    }

    //Dummy data for userlist
    private void initUser() {
        userNameList.add("Willow");
        userNameList.add("Logan");
        userNameList.add("Sofia");
    }

    //Dummy data for hasttag list
    private void initHashtag() {
        hashtagList.add("#SanDiedo");
        hashtagList.add("#sunny");
        hashtagList.add("#beaches");
    }

    ////Dummy data for postlist
    private void initPost() {

        Post newpost = new Post();
        newpost.image = "https://www.telegraph.co.uk/content/dam/Travel/2016/August/san-diego-AP75672386.jpg?imwidth=1400";
        newpost.user = "willow";
        newpost.hashtag = "#san diego";
        newpost.postText = "Enjoy";
        postList.add(newpost);

        newpost = new Post();
        newpost.image = "https://www.trolleytours.com/wp-content/uploads/2016/06/san-diego-beaches-480x270.jpg";
        newpost.user = "logan";
        newpost.hashtag = "#beaches";
        newpost.postText = "Beautiful";
        postList.add(newpost);


        newpost = new Post();
        newpost.image = "http://www.solar-nation.org/images/sandiegocasolarpower.jpg";
        newpost.user = "sofia";
        newpost.hashtag = "#sunny";
        newpost.postText = "San Diego";
        postList.add(newpost);
    }

    //sub activity to upload pic
    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            imageUri = data.getData();
            Glide.with(this)
                    .asBitmap()
                    .load(imageUri)
                    .into(image);
        }

    }
}
