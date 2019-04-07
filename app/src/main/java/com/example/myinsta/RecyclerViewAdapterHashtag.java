package com.example.myinsta;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewAdapterHashtag extends RecyclerView.Adapter<RecyclerViewAdapterHashtag.HashtagViewHolder> {

    private ArrayList<String> hasgtagList = new ArrayList<>();
    private ArrayList<Post> postList = new ArrayList<>();
    private Context context;

    public RecyclerViewAdapterHashtag(Context context1, ArrayList<String>hasgtagList1){
        hasgtagList = hasgtagList1;
        context = context1;
    }

    @NonNull
    @Override
    public HashtagViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.user_hashtag_tag, parent, false);
        HashtagViewHolder hashtagViewHolder = new HashtagViewHolder(view);
        return hashtagViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HashtagViewHolder hashtagViewHolder, int position) {
        hashtagViewHolder.userHashtagTv.setText(hasgtagList.get(position));
        final AlertDialog.Builder builderpost = new AlertDialog.Builder(context);
        final View dialogViewpost = LayoutInflater.from(context).inflate(R.layout.dialog_recyclerview_postlist, null);

        hashtagViewHolder.userHashtagTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builderpost.setView(dialogViewpost);
                AlertDialog alertDialog = builderpost.create();
                initPost();
                RecyclerView recyclerView = dialogViewpost.findViewById(R.id.rvpostList);
                RecyclerViewAdapterPost adapterPost = new RecyclerViewAdapterPost(context, postList);
                recyclerView.setAdapter(adapterPost);
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                alertDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return hasgtagList.size();
    }

    public class HashtagViewHolder extends RecyclerView.ViewHolder {
        TextView userHashtagTv;
        public HashtagViewHolder(@NonNull View itemView) {
            super(itemView);
            userHashtagTv = itemView.findViewById(R.id.user_hashtag_tv);
        }
    }

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
}
