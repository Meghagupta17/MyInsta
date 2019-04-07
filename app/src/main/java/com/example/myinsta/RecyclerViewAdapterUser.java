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

public class RecyclerViewAdapterUser extends RecyclerView.Adapter<RecyclerViewAdapterUser.UserViewHolder> {

    private ArrayList<String> userList = new ArrayList<>();
    private ArrayList<Post> postList = new ArrayList<>();
    private Context context;

    public RecyclerViewAdapterUser(Context context2, ArrayList<String> userList2) {
        userList = userList2;
        context = context2;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.user_hashtag_tag, parent, false);
        UserViewHolder userViewHolder = new UserViewHolder(view);
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int position) {

        userViewHolder.userHashtagTv.setText(userList.get(position));
        final AlertDialog.Builder builderpost = new AlertDialog.Builder(context);
        final View dialogViewpost = LayoutInflater.from(context).inflate(R.layout.dialog_recyclerview_postlist, null);
        userViewHolder.userHashtagTv.setOnClickListener(new View.OnClickListener() {
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
        return userList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView userHashtagTv;

        public UserViewHolder(@NonNull View itemView) {
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