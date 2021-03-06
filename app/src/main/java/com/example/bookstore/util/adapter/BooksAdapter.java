package com.example.bookstore.util.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bookstore.R;
import com.example.bookstore.entity.Book;
import com.example.bookstore.ui.BookDetailActivity;
import com.example.bookstore.util.FileHelper;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.ViewHolder> {
    private final Context context;
    private final List<Book> booksList;

    public BooksAdapter(List<Book> booksList, Context context) {
        this.booksList = booksList;
        this.context = context;
    }

    @Override
    public long getItemId(int position) {
        return booksList.get(position).getId();
    }

    @Override
    public int getItemCount() {
        return booksList.size();
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_books, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Book book = booksList.get(position);
        holder.book.setOnClickListener(view -> {
            Intent intent = new Intent(context, BookDetailActivity.class);
            intent.putExtra("book_id", book.getId() + "");
            intent.putExtra("book_name", book.getName());
            intent.putExtra("book_author", book.getAuthor());
            intent.putExtra("book_description", book.getDescription());
            intent.putExtra("book_category_id", book.getCategory_id() + "");
            context.startActivity(intent);
        });
        holder.book_image.setImageBitmap(new FileHelper().loadImageBitmap(
                context, "book", book.getId() + ""));
        holder.book_name.setText(book.getName());
        holder.book_author.setText(book.getAuthor());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final RelativeLayout book;
        private final ImageView book_image;
        private final TextView book_name;
        private final TextView book_author;

        public ViewHolder(View view) {
            super(view);
            book = view.findViewById(R.id.book);
            book_image = view.findViewById(R.id.booksThumbnailImageView);
            book_name = view.findViewById(R.id.booksTitleTextView);
            book_author = view.findViewById(R.id.booksAuthorTextView);
        }
    }
}
