package com.example.retrofitecommerceapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrofitecommerceapp.R;
import com.example.retrofitecommerceapp.model.DataModel;

import java.util.List;

public class RecyclerAdapterToGetCategoryDataFromAPI extends RecyclerView.Adapter<RecyclerAdapterToGetCategoryDataFromAPI.ViewHolder> {

    Context context;
    List<DataModel> modelList;

    public RecyclerAdapterToGetCategoryDataFromAPI(Context context, List<DataModel> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView categoryTxt;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryTxt = itemView.findViewById(R.id.categoryTextViewDataLayout);
            imageView = itemView.findViewById(R.id.cateogryImageViewDataLayout);

        }
    }

    @NonNull
    @Override
    public RecyclerAdapterToGetCategoryDataFromAPI.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_data_for_recyclerview, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterToGetCategoryDataFromAPI.ViewHolder holder, int position) {
//         holder.id.setText((int) modelList.get(position).getId());

        holder.categoryTxt.setText(modelList.get(position).getCategory());
        Glide
                .with(context)
                .load(modelList.get(position).getImageUrl())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
