package com.example.myinsta;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewAdapterUser extends RecyclerView.Adapter<RecyclerViewAdapterUser.UserViewHolder> {

    private final Object Context;
    private ArrayList<String> UserList = new ArrayList<>();

    public RecyclerViewAdapterUser(Context context, ArrayList<String> userList, Context context1){
        UserList = userList;
        Context = context;
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
        userViewHolder.userHashtagTv.setText(UserList.get(position));
    }

    @Override
    public int getItemCount() {
        return UserList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView userHashtagTv;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            userHashtagTv = itemView.findViewById(R.id.user_hashtag_tv);
        }
    }
}
