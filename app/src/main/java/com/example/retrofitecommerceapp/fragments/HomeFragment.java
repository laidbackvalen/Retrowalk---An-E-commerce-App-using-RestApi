package com.example.retrofitecommerceapp.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.retrofitecommerceapp.R;
import com.example.retrofitecommerceapp.activities.MainActivity;
import com.example.retrofitecommerceapp.activities.MainTwoActivity;
import com.example.retrofitecommerceapp.adapter.RecyclerAdapterToGetDataFromAPI;
import com.example.retrofitecommerceapp.api.RetroFit_API_NetworkCall;
import com.example.retrofitecommerceapp.model.DataModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HomeFragment extends Fragment {

    View searchView, menuViewOnTop;
    FrameLayout frameLayout;
    RecyclerView recyclerView;
    RecyclerAdapterToGetDataFromAPI adapter;
    List<DataModel> dataModel;
    ImageView cartImageVIew, favImageview, notificationImageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        menuViewOnTop = view.findViewById(R.id.viewInsideTopViewHomeLayout);
        frameLayout = view.findViewById(R.id.frameLayout);
//        cartImageVIew = view.findViewById(R.id.cartImageHomeTopBar);
//        favImageview = view.findViewById(R.id.likedImageHomeTopBar);
//        notificationImageView = view.findViewById(R.id.notifyImageHomeTopBar);
        searchView = view.findViewById(R.id.searchView);

        //opening Drawer from home fragment which is set on main activity not on home fragment
        menuViewOnTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) requireActivity()).openDrawer();
            }
        });

        //Home fragment to Mainactivitytwo Search fragment
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MainTwoActivity.class);
                intent.putExtra("search", "search");
                startActivity(intent);
//                getActivity().finish();
            }
        });
//        cartImageVIew.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getContext(), MainTwoActivity.class);
//                intent.putExtra("cart", "cart");
//                startActivity(intent);
//            }
//        });
//        favImageview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getContext(), MainTwoActivity.class);
//                intent.putExtra("fav", "fav");
//                startActivity(intent);
//            }
//        });
//        notificationImageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getContext(), MainTwoActivity.class);
//                intent.putExtra("notification", "notification");
//                startActivity(intent);
//            }
//        });

        dataModel = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerMain);
        getAllUsers();

        return view;
    }

    private void getAllUsers() {
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
                            adapter = new RecyclerAdapterToGetDataFromAPI(getContext(), dataModel);
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


}
