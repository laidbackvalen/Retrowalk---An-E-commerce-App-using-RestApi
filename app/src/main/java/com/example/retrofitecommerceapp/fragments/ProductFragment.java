package com.example.retrofitecommerceapp.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.retrofitecommerceapp.R;
import com.example.retrofitecommerceapp.model.DataModel;


public class ProductFragment extends Fragment {
    DataModel model1;
    ImageView productImage;
    TextView productCategory, productTitle, productPrice, productReviews, productDescriptions;
    RatingBar ratingBar;

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product, container, false);

        productImage = view.findViewById(R.id.productImageProductFragment);
        productCategory = view.findViewById(R.id.categoryProductFragment);
        productTitle = view.findViewById(R.id.titleProductFragment);
        productPrice = view.findViewById(R.id.priceProductFragment);
        productReviews = view.findViewById(R.id.reviewsCountProductFragment);
        productDescriptions = view.findViewById(R.id.productDescriptionProductFragment);
        ratingBar = view.findViewById(R.id.ratingProductFragment);

        Bundle bundle = this.getArguments();

        if (bundle != null) {

            Glide
                    .with(getContext())
                    .load(bundle.getString("IMAGE"))
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(productImage);

            String category = bundle.getString("CATEGORY");
            String title = bundle.getString("TITLE");
            String price = bundle.getString("PRICE");
            String rating = bundle.getString("RATING");
            String reviews = bundle.getString("REVIEWS");
            String description = bundle.getString("DESCRIPTION");

            productCategory.setText(category);
            productTitle.setText(title);
            productPrice.setText(price);
            productDescriptions.setText(description);

            ratingBar.setRating(Float.parseFloat(rating));
            productReviews.setText(reviews);
        }


        return view;
    }
}