package com.example.retrofitecommerceapp.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.retrofitecommerceapp.R;
import com.example.retrofitecommerceapp.activities.MainTwoActivity;
import com.example.retrofitecommerceapp.adapter.RecyclerAdapterToGetCategoryDataFromAPI;
import com.example.retrofitecommerceapp.adapter.SliderAdapterViewPagerOnCategoryFragment;
import com.example.retrofitecommerceapp.api.RetroFit_API_NetworkCall;
import com.example.retrofitecommerceapp.model.DataModel;
import com.example.retrofitecommerceapp.model.ViewPagerModelClass;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CategoriesFragment extends Fragment {
    ViewPager2 viewPagerMain;
    RecyclerView recyclerView;
    List<DataModel> dataModel;
    RecyclerAdapterToGetCategoryDataFromAPI adapter;
    View searchViewCategory;

    ImageView cartCategoryImageView, notificationCategoryImageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        viewPagerMain = view.findViewById(R.id.viewPagerCategoryFragment);
        recyclerView = view.findViewById(R.id.recyclerCategory);
        searchViewCategory = view.findViewById(R.id.searchViewCategories);
        cartCategoryImageView = view.findViewById(R.id.cartImageCategoryTopBar);
        notificationCategoryImageView = view.findViewById(R.id.notificationImageHomeTopBar);

        cartCategoryImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MainTwoActivity.class);
                intent.putExtra("cart", "cart");
                startActivity(intent);
            }
        });

        notificationCategoryImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MainTwoActivity.class);
                intent.putExtra("notification", "notification");
                startActivity(intent);
            }
        });

        searchViewCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MainTwoActivity.class);
                intent.putExtra("search", "search");
                startActivity(intent);
            }
        });


        dataModel = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerCategory);
        getAllCategoryData();

        List<ViewPagerModelClass> sliderModelClassViewpager = new ArrayList<>();

        sliderModelClassViewpager.add(new ViewPagerModelClass(R.drawable.cateimagethree));
        sliderModelClassViewpager.add(new ViewPagerModelClass(R.drawable.cateimagefour));
        sliderModelClassViewpager.add(new ViewPagerModelClass(R.drawable.cateimagefive));
        sliderModelClassViewpager.add(new ViewPagerModelClass(R.drawable.cateimagesix));
        sliderModelClassViewpager.add(new ViewPagerModelClass(R.drawable.cateimageseven));
        sliderModelClassViewpager.add(new ViewPagerModelClass(R.drawable.cateimageeight));
        sliderModelClassViewpager.add(new ViewPagerModelClass(R.drawable.cateimagenine));

        viewPagerMain.setAdapter(new SliderAdapterViewPagerOnCategoryFragment(sliderModelClassViewpager, viewPagerMain, getContext()));
        viewPagerMain.setClipToPadding(false);
        viewPagerMain.setClipChildren(false);
        viewPagerMain.setOffscreenPageLimit(2);
        viewPagerMain.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(20));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                Float r = 1 - Math.abs(position);
                page.setScaleX(0.85f + r * 0.15f);
            }
        });
        viewPagerMain.setPageTransformer(compositePageTransformer);
        viewPagerMain.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                slideHandler.removeCallbacks(sliderRunnable);
                slideHandler.postDelayed(sliderRunnable, 2500); //SLIDE duration 2 Secs
            }
        });

//VIEW PAGER SETTINGS ENDS HERE, there is code in the end where the on create function ends, please checkout
        return view;
    }

    private void getAllCategoryData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetroFit_API_NetworkCall.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetroFit_API_NetworkCall networkCall = retrofit.create(RetroFit_API_NetworkCall.class);
        Call<List<DataModel>> call = networkCall.getAllData();

        call.enqueue(new Callback<List<DataModel>>() {
            @Override
            public void onResponse(Call<List<DataModel>> call, Response<List<DataModel>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        dataModel = response.body();

                        for (int i = 0; i < dataModel.size(); i++) {
                            adapter = new RecyclerAdapterToGetCategoryDataFromAPI(getContext(), dataModel);
                            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<DataModel>> call, Throwable t) {

            }
        });
    }

    //Outside Oncreate ViewPager Code
    Handler slideHandler = new Handler();
    Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPagerMain.setCurrentItem(viewPagerMain.getCurrentItem() + 1);
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        slideHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        slideHandler.postDelayed(sliderRunnable, 3000);
    }
}