package com.example.myinsta;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
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
    public void onBindViewHolder(@NonNull RecyclerViewAdapterPost.PostViewHolder postViewHolder, int position) {

        Glide.with(context)
                .asBitmap()
                .load("gs://myinsta-4e85d.appspot.com/photos/"+ postList.get(position).image)
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
        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            post_parent = itemView.findViewById(R.id.post_parent);
            imagepost = itemView.findViewById(R.id.imagepost);
            post_user_tv = itemView.findViewById(R.id.post_user_tv);
            post_hashtag_tv = itemView.findViewById(R.id.post_hashtag_tv);
            post_text = itemView.findViewById(R.id.post_text);
        }
    }
}

