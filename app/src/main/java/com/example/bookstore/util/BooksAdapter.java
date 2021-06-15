package com.example.bookstore.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookstore.R;
import com.example.bookstore.entity.Book;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.ViewHolder> {
    private final Context context;
    private final List<Book> booksList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView book_image;
        private final TextView book_name;
        private final TextView book_description;
        private final TextView book_author;

        public ViewHolder (View view) {
            super(view);

            book_image = view.findViewById(R.id.booksThumbnailImageView);
            book_name = view.findViewById(R.id.booksTitleTextView);
            book_description = view.findViewById(R.id.booksDetailTextView);
            book_author = view.findViewById(R.id.booksAuthorTextView);
        }
    }

    public BooksAdapter(List<Book> booksList, Context context) {
        this.booksList = booksList;
        this.context = context;
    }

    @Override
    public long getItemId(int position) {
        return booksList.get(position).getId();
    }

    @Override
    public int getItemCount(){
        return booksList.size();
    }


    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_books, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        Book book = booksList.get(position);
        holder.book_image.setImageBitmap(new FileHelper().loadImageBitmap(
                context, "book", book.getId() + ""));
        holder.book_name.setText(book.getName());
        holder.book_description.setText(book.getDescription());
        holder.book_author.setText(book.getAuthor());
    }
}
