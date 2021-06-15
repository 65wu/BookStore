package com.example.bookstore.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bookstore.R;
import com.example.bookstore.entity.Book;

import java.util.List;

public class BooksAdapter extends BaseAdapter {
    private final List<Book> mData;
    private final Context mContext;

    static class ViewHolder {
        private ImageView book_image;
        private TextView book_name;
        private TextView book_description;
        private TextView book_author;
    }

    public BooksAdapter(List<Book> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mData.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder;
        if(convertView == null) {
            mViewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_books, parent,false);
            mViewHolder.book_image = convertView.findViewById(R.id.booksThumbnailImageView);
            mViewHolder.book_name = convertView.findViewById(R.id.booksTitleTextView);
            mViewHolder.book_description = convertView.findViewById(R.id.booksDetailTextView);
            mViewHolder.book_author = convertView.findViewById(R.id.booksAuthorTextView);
        } else {
            mViewHolder = (ViewHolder)convertView.getTag();
        }

        mViewHolder.book_image.setImageBitmap(new FileHelper().loadImageBitmap(
                    mContext, mData.get(position).getId() + ""));
        mViewHolder.book_name.setText(mData.get(position).getName());
        mViewHolder.book_description.setText(mData.get(position).getDescription());
        mViewHolder.book_author.setText(mData.get(position).getAuthor());
        return convertView;
    }
}
