package com.example.retrofitecommerceapp.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofitecommerceapp.R;
import com.example.retrofitecommerceapp.activities.MainActivity;
import com.example.retrofitecommerceapp.activities.MainTwoActivity;
import com.example.retrofitecommerceapp.adapter.RecyclerAdapterForCartItems;
import com.example.retrofitecommerceapp.room.database.UserDatabase;
import com.example.retrofitecommerceapp.room.entity.UserEntitiesForCart;
import com.razorpay.Checkout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {
    ImageView gobackImgView, searchImageViewCart;
    TextView totalPrice;
    Button buttonCheckOut;
    RecyclerView recyclerView;
    List<UserEntitiesForCart> entitiesList = new ArrayList<>();
    RecyclerAdapterForCartItems adapter;
    UserDatabase database;
    float sum;
    public static final String DB_NAME = "UsersCartInfo";

    private void databaseQuery() {
        database = Room.databaseBuilder(requireContext(), UserDatabase.class, DB_NAME)
                .allowMainThreadQueries()
                .build();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        searchImageViewCart = view.findViewById(R.id.searchIconImageCartTopBar);
        gobackImgView = view.findViewById(R.id.goBackImageViewCartFragment);
        totalPrice = view.findViewById(R.id.totalPriceCartFragment);
        buttonCheckOut = view.findViewById(R.id.checkOutButtonCartFragment);

        recyclerView = view.findViewById(R.id.recyclerViewInCart);
        databaseQuery();
        entitiesList = database.databaseAccessObject().getUserCartInfo();


        sum = 0;
        for (int i = 0; i < entitiesList.size(); i++) {
            sum = sum += Float.parseFloat(entitiesList.get(i).getPrice_ForCart()) * entitiesList.get(i).getQunatity_ForCort();
            int price = (int) Math.round(sum) * 100 / 100;
            totalPrice.setText(String.valueOf(price));

            adapter = new RecyclerAdapterForCartItems(getContext(), entitiesList);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(adapter);
            adapter.notifyItemChanged(i);
            Toast.makeText(getContext(), "" + entitiesList.get(i).getQunatity_ForCort(), Toast.LENGTH_SHORT).show();
        }

        searchImageViewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MainTwoActivity.class);
                intent.putExtra("search", "search");
                startActivity(intent);
            }
        });

        gobackImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), MainActivity.class));
            }
        });

        buttonCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Checkout checkout = new Checkout();

                int amount = (int) Math.round(sum) * 100;
                // set your id as below
                checkout.setKeyID("rzp_test_2UJRrSsSi1WxJV");

                // set image
                checkout.setImage(R.drawable.ic_launcher_background);

                // initialize json object
                JSONObject object = new JSONObject();
                try {
                    // to put name
                    object.put("name", "The Ecommerce App");
                    // put description
                    object.put("description", "Test payment");
                    // to set theme color
                    object.put("theme.color", R.color.black);
                    // put the currency
                    object.put("currency", "INR");
                    // put amount
                    object.put("amount", amount);
                    // put mobile number
                    object.put("prefill.contact", "7776030399");
                    // put email
                    object.put("prefill.email", "chait@gmail.com");
                    // open razorpay to checkout activity
                    checkout.open((Activity) getContext(), object);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        return view;
    }


//SENDING DATA FROM FRGMENT TO HOSTING DATA
//    interface MyFragmentListener {
//        void onDataPassed(String sum);
//    }
//    private MyFragmentListener mListener;
//
//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//        if (context instanceof MyFragmentListener) {
//            mListener = (MyFragmentListener) context;
//        } else {
//            throw new ClassCastException(context.toString() + " must implement MyFragmentListener");
//        }
//    }
//
//    // Use this method to pass data
//    private void sendDataToActivity(String data) {
//        if (mListener != null) {
//            mListener.onDataPassed(data);
//        }
//    }
}