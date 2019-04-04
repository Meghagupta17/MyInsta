package com.example.myinsta;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewAdapterHashtag extends RecyclerView.Adapter<RecyclerViewAdapterHashtag.HashtagViewHolder> {

    private ArrayList<String> hasgtagList = new ArrayList<>();
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
}
