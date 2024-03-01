package com.example.retrofitecommerceapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.retrofitecommerceapp.R;
import com.example.retrofitecommerceapp.model.ViewPagerModelClass;

import java.util.List;

public class SliderAdapterViewPagerOnCategoryFragment extends RecyclerView.Adapter<SliderAdapterViewPagerOnCategoryFragment.SliderViewHolder> {

    ViewPager2 viewpager2;
    List<ViewPagerModelClass> pagerModelClasses;
    Context context;

    public SliderAdapterViewPagerOnCategoryFragment(List<ViewPagerModelClass> pagerModelClasses, ViewPager2 viewpager2, Context context) {
        this.viewpager2 = viewpager2;
        this.pagerModelClasses = pagerModelClasses;
        this.context = context;
    }

    class SliderViewHolder extends RecyclerView.ViewHolder {
     ImageView imageView;
        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);
           imageView  = itemView.findViewById(R.id.imageSlideLayoutForCategoryFragent);
        }
    }

    @NonNull
    @Override
    public SliderAdapterViewPagerOnCategoryFragment.SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewpagercategorylayout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SliderAdapterViewPagerOnCategoryFragment.SliderViewHolder holder, int position) {
        Glide
                .with(context)
                .load(pagerModelClasses.get(position).getImage())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.imageView);

        if (position == pagerModelClasses.size() - 2) {
            viewpager2.post(runnable);
        }

    }

    @Override
    public int getItemCount() {
        return pagerModelClasses.size();
    }

    Runnable runnable = new Runnable() {

        @Override
        public void run() {
            pagerModelClasses.addAll(pagerModelClasses);
            notifyDataSetChanged();
        }
    };
}
