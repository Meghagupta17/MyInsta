package com.example.myinsta;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.UUID;

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
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    private int PICK_IMAGE_REQUEST = 1;
    String userNickName;

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
    TextView userid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userNickName = getIntent().getExtras().getString("nickname");

        //Firebase init
        FirebaseApp.initializeApp(this);
        firebaseDatabase  = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference("posts");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                postList.clear();
                for (DataSnapshot ds:dataSnapshot.getChildren()) {
                    Post p = ds.getValue(Post.class);
                    postList.add(p);
                    if (!hashtagList.contains(p.hashtag)){
                        hashtagList.add(p.hashtag);
                    }
                    if (!userNameList.contains(p.userNickName)){
                        userNameList.add(p.userNickName);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference("photos");

        //Make a post Dialog Box
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_create_post, null);

        //components of dialog box to make post
        image = dialogView.findViewById(R.id.image);
        postText = dialogView.findViewById(R.id.postText);
        addHashtag = dialogView.findViewById(R.id.addHashtag);
        userid = dialogView.findViewById(R.id.userid);
        userid.setText(userNickName);

        btnuploadImage = dialogView.findViewById(R.id.btnuploadImage);
        btnuploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });


        btnmakePost = dialogView.findViewById(R.id.btnpostOnline);
        btnmakePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageUri.toString() !="") {
                    String key = UUID.randomUUID().toString();
                    Post p = new Post(addHashtag.getText().toString(), userNickName, postText.getText().toString(), key);
                    String keyDb = myRef.push().getKey();
                    myRef.child(keyDb).setValue(p);
                    StorageReference filepath = storageReference.child(key);
                    filepath.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(LogedInActivity.this, "Upload Done", Toast.LENGTH_LONG).show();
                        }
                    });
                    alertDialog.dismiss();
                    image.setImageResource(R.mipmap.ic_launcher);
                    postText.getText().clear();
                    addHashtag.getText().clear();
                }
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
        final AlertDialog.Builder builderuser = new AlertDialog.Builder(this);
        final View dialogViewuser = LayoutInflater.from(this).inflate(R.layout.dialog_recyclerview_userlist, null);

        builderuser.setView(dialogViewuser);
        alertDialoguser = builderuser.create();

        btnuserList = findViewById(R.id.btnuserList);
        btnuserList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //initUser();
                RecyclerView recyclerView = dialogViewuser.findViewById(R.id.rvuserList);
                RecyclerViewAdapterUser adapterUser = new RecyclerViewAdapterUser(LogedInActivity.this, userNameList, postList);
                recyclerView.setAdapter(adapterUser);
                recyclerView.setLayoutManager(new LinearLayoutManager(LogedInActivity.this));
                alertDialoguser.show();
            }
        });

        //Dialogbox for hashtag list
        final AlertDialog.Builder builderhashtag = new AlertDialog.Builder(this);
        final View dialogViewhashtag = LayoutInflater.from(this).inflate(R.layout.dialog_recycleview_hashtaglist, null);

        builderhashtag.setView(dialogViewhashtag);
        alertDialoghashtag = builderhashtag.create();

        btnhastagList = findViewById(R.id.btnhastagList);
        btnhastagList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //initHashtag();
                RecyclerView recyclerView = dialogViewhashtag.findViewById(R.id.rvhashtagList);
                RecyclerViewAdapterHashtag adapterHashtag = new RecyclerViewAdapterHashtag(LogedInActivity.this, hashtagList, postList);
                recyclerView.setAdapter(adapterHashtag);
                recyclerView.setLayoutManager(new LinearLayoutManager(LogedInActivity.this));
                alertDialoghashtag.show();
            }
        });

        ////Dialogbox for post list
        final AlertDialog.Builder builderpost = new AlertDialog.Builder(this);
        final View dialogViewpost = LayoutInflater.from(this).inflate(R.layout.dialog_recyclerview_postlist, null);

        builderpost.setView(dialogViewpost);
        alertDialogpost = builderpost.create();

        btnPostList = findViewById(R.id.btnPostList);
        btnPostList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // initPost();
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
        userNameList.clear();
        userNameList.add("Willow");
        userNameList.add("Logan");
        userNameList.add("Sofia");
    }

    //Dummy data for hasttag list
    private void initHashtag() {
        hashtagList.clear();
        hashtagList.add("#SanDiedo");
        hashtagList.add("#sunny");
        hashtagList.add("#beaches");
    }

    ////Dummy data for postlist
    private void initPost() {
        postList.clear();
        Post newpost = new Post();
        newpost.image = "https://www.telegraph.co.uk/content/dam/Travel/2016/August/san-diego-AP75672386.jpg?imwidth=1400";
        newpost.userNickName = "willow";
        newpost.hashtag = "#san diego";
        newpost.postText = "Enjoy";
        postList.add(newpost);

        newpost = new Post();
        newpost.image = "https://www.trolleytours.com/wp-content/uploads/2016/06/san-diego-beaches-480x270.jpg";
        newpost.userNickName = "logan";
        newpost.hashtag = "#beaches";
        newpost.postText = "Beautiful";
        postList.add(newpost);


        newpost = new Post();
        newpost.image = "http://www.solar-nation.org/images/sandiegocasolarpower.jpg";
        newpost.userNickName = "sofia";
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
