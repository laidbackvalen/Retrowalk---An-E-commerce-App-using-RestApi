package com.example.retrofitecommerceapp.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.retrofitecommerceapp.R;
import com.example.retrofitecommerceapp.activities.MainTwoActivity;


public class ProfileFragment extends Fragment {

    ImageView searchImage;
View  viewL;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        searchImage = view.findViewById(R.id.searchImageView);
        searchImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MainTwoActivity.class);
                intent.putExtra("search", "search");
                startActivity(intent);
            }
        });

        return view;
    }
}