package com.example.bookstore.util;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bookstore.R;
import com.example.bookstore.entity.Comment;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {
    private final Context context;
    private final List<Comment> commentList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView username;
        private final TextView send_date;
        private final TextView content;

        public ViewHolder (View view) {
            super(view);
            username = view.findViewById(R.id.comment_username);
            send_date = view.findViewById(R.id.send_date);
            content = view.findViewById(R.id.comment_content);
        }
    }
    public CommentsAdapter(List<Comment> booksList, Context context) {
        this.commentList = booksList;
        this.context = context;
    }

    @Override
    public long getItemId(int position) {
        return commentList.get(position).getId();
    }

    @Override
    public int getItemCount(){
        return commentList.size();
    }

    @NotNull
    @Override
    public CommentsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_comment, parent,false);
        return new CommentsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommentsAdapter.ViewHolder holder, int position){
        Comment comment = commentList.get(position);
        holder.username.setText(comment.getUsername());
        holder.send_date.setText(DateConverters.getFormatDate(comment.getSend_date()));
        holder.content.setText(comment.getContent());
    }
}
