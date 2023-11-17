package com.example.crud.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;
import com.example.crud.models.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{
    private final Context context;
    private final List<Category> categoryList;
    private final OnClickListener onClickListener;

    public CategoryAdapter(Context context, List<Category> categoryList, OnClickListener onClickListener) {
        this.context = context;
        this.categoryList = categoryList;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View currentView = LayoutInflater.from(context).inflate(R.layout.category_item_layout, null, false);
        return new CategoryViewHolder(currentView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = categoryList.get(position);
        if (category != null) {
            if (category.getName() != null) {
                holder.name .setText(category.getName());
            }
            holder.itemCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickListener.onClickItem(view, category);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final CardView itemCard;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txtCategory);
            itemCard = itemView.findViewById(R.id.itemCard);
        }
    }

    public interface OnClickListener{
        void onClickItem(View view, Object object);
    }
}
