package com.example.myinsta;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapterPost extends RecyclerView.Adapter<RecyclerViewAdapterPost.PostViewHolder> {


    public ArrayList<Post> postList = new ArrayList<Post>();
    private Context context;

    public RecyclerViewAdapterPost(Context context3, ArrayList<Post> postList){

        this.postList = postList;
        context = context3 ;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterPost.PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.post_hashtag_user_list, parent, false);
        RecyclerViewAdapterPost.PostViewHolder postViewHolder = new RecyclerViewAdapterPost.PostViewHolder(view);
        return postViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterPost.PostViewHolder postViewHolder, final int position) {

        final String urlImage = "https://firebasestorage.googleapis.com/v0/b/myinsta-4e85d.appspot.com/o/photos%2F"+ postList.get(position).image+"?alt=media";

        postViewHolder.btnDownloadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
                StorageReference islandRef = firebaseStorage.getReference().child("photos/"+postList.get(position).image);

                final long ONE_MEGABYTE = 1024 * 1024;
                islandRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        // Data for "images/island.jpg" is returns, use this as needed
                        /*File file = new File(context.getFilesDir(), postList.get(position).image);
                        FileOutputStream outputStream;

                        try {
                            outputStream = openFileOutput(postList.get(position).image, Context.MODE_PRIVATE);
                            outputStream.write(bytes);
                            outputStream.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }*/
                        Toast.makeText(context, "Downloaded Image", Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle any errors
                    }
                });
            }
        });

        Glide.with(context)
                .asBitmap()
                .load(urlImage)
                .into(postViewHolder.imagepost);

        postViewHolder.post_user_tv.setText(postList.get(position).userNickName);
        postViewHolder.post_hashtag_tv.setText(postList.get(position).hashtag);
        postViewHolder.post_text.setText(postList.get(position).postText);

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }


    public class PostViewHolder extends RecyclerView.ViewHolder {
        ImageView imagepost;
        TextView post_user_tv;
        TextView post_hashtag_tv;
        TextView post_text;
        LinearLayout post_parent;
        Button btnDownloadImage;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            post_parent = itemView.findViewById(R.id.post_parent);
            imagepost = itemView.findViewById(R.id.imagepost);
            post_user_tv = itemView.findViewById(R.id.post_user_tv);
            post_hashtag_tv = itemView.findViewById(R.id.post_hashtag_tv);
            post_text = itemView.findViewById(R.id.post_text);
            btnDownloadImage = itemView.findViewById(R.id.btnDownloadImage);
        }
    }
}

