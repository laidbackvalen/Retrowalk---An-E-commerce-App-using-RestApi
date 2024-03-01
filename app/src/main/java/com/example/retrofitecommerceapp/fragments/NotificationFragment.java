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

public class NotificationFragment extends Fragment {
    ImageView gobackNotificationImgView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        gobackNotificationImgView = view.findViewById(R.id.goBackImageViewNotificationsFragment);
        gobackNotificationImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), MainActivity.class));
            }
        });
        return view;
    }
}