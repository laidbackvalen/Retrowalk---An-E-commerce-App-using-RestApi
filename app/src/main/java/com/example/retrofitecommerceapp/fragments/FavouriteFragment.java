package com.example.retrofitecommerceapp.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.retrofitecommerceapp.R;
import com.example.retrofitecommerceapp.activities.MainActivity;
import com.example.retrofitecommerceapp.activities.MainTwoActivity;

public class FavouriteFragment extends Fragment {
    ImageView gobackfavImg, searchImageViewFavouriteFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);

        searchImageViewFavouriteFragment = view.findViewById(R.id.searchImageFavouriteFragment);

        searchImageViewFavouriteFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MainTwoActivity.class);
                intent.putExtra("search", "search");
                startActivity(intent);
            }
        });
        gobackfavImg = view.findViewById(R.id.goBackImageViewFavouriteFragment);
        gobackfavImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), MainActivity.class));
            }
        });
        return view;
    }
}