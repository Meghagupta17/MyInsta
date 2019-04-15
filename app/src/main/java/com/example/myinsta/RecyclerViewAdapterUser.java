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
    private ArrayList<Post> postListUser = new ArrayList<>();
    private Context context;
    private AlertDialog alertDialogpost;

    public RecyclerViewAdapterUser(Context context, ArrayList<String> userList, ArrayList<Post>postList) {
        this.userList = userList;
        this.context = context;
        this.postList = postList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.user_hashtag_tag, parent, false);
        UserViewHolder userViewHolder = new UserViewHolder(view);
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final UserViewHolder userViewHolder, int position) {

        userViewHolder.userHashtagTv.setText(userList.get(position));

        final AlertDialog.Builder builderpost = new AlertDialog.Builder(context);
        final View dialogViewpost = LayoutInflater.from(context).inflate(R.layout.dialog_recyclerview_postlist, null);

        builderpost.setView(dialogViewpost);

        userViewHolder.userHashtagTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogpost = builderpost.create();
                for (Post p : postList) {
                   if (p.userNickName == userViewHolder.userHashtagTv.getText().toString()){
                       postListUser.add(p);
                   }
                }
                RecyclerView recyclerView = dialogViewpost.findViewById(R.id.rvpostList);
                RecyclerViewAdapterPost adapterPost = new RecyclerViewAdapterPost(context, postListUser);
                recyclerView.setAdapter(adapterPost);
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                alertDialogpost.onBackPressed();
                alertDialogpost.show();
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
}