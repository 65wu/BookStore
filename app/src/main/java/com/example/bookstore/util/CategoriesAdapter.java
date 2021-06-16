package com.example.bookstore.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bookstore.entity.Category;
import com.example.bookstore.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder>{
    private final Context context;
    private final List<Category> categoryList;
    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView category_image;
        private final TextView category_name;

        public ViewHolder (View view) {
            super(view);
            category_image = view.findViewById(R.id.category_image);
            category_name = view.findViewById(R.id.category_name);
        }
    }

    public CategoriesAdapter(List<Category> categoryList, Context context) {
        this.categoryList = categoryList;
        this.context = context;
    }

    @Override
    public long getItemId(int position) {
        return categoryList.get(position).getId();
    }

    @Override
    public int getItemCount(){
        return categoryList.size();
    }

    @NotNull
    @Override
    public CategoriesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_categories, parent,false);
        return new CategoriesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoriesAdapter.ViewHolder holder, int position){
        Category category = categoryList.get(position);
        holder.category_image.setImageBitmap(new FileHelper().loadImageBitmap(
                context, "category", category.getId() + ""));
        holder.category_name.setText(category.getName());
    }
}