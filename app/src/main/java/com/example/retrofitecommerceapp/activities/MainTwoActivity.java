package com.example.retrofitecommerceapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.retrofitecommerceapp.fragments.FavouriteFragment;
import com.example.retrofitecommerceapp.fragments.NotificationFragment;
import com.example.retrofitecommerceapp.R;
import com.example.retrofitecommerceapp.fragments.CartFragment;
import com.example.retrofitecommerceapp.fragments.ProductFragment;
import com.example.retrofitecommerceapp.fragments.SearchFragment;
import com.example.retrofitecommerceapp.model.DataModel;
import com.razorpay.PaymentResultListener;

public class MainTwoActivity extends AppCompatActivity implements PaymentResultListener {
    FrameLayout frameLayoutTwo;

    TextView textView;
    DataModel model;

    //    UserDatabase database;
//    List<UserEntitiesForCart> entitiesForCart = new ArrayList<>();
//    public static final String DB_NAME = "usersCartInfo";
//
//    void databaseQuery() {
//        database = Room.databaseBuilder(getApplicationContext(), UserDatabase.class, DB_NAME).allowMainThreadQueries().build();
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_two);

        frameLayoutTwo = findViewById(R.id.frameLayoutTwo);

        Intent intent = getIntent();
        String d = intent.getStringExtra("cart");
        String e = intent.getStringExtra("fav");
        String f = intent.getStringExtra("notification");
        String g = intent.getStringExtra("search");




        //getting data using intent from RecyclerAdapterToGetDataFromAPI (Adapter class)
        model = (DataModel) getIntent().getSerializableExtra("value");


        if (d != null) {
            setFragment(new CartFragment());
        } else if (e != null) {
            setFragment(new FavouriteFragment());
        } else if (f != null) {
            setFragment(new NotificationFragment());
        } else if (g != null) {
            setFragment(new SearchFragment());
        } else if (model != null) {

            Bundle arguments = new Bundle();
            arguments.putString("IMAGE", model.getImageUrl());
            arguments.putString("CATEGORY", model.getCategory());
            arguments.putString("TITLE", model.getTitle());
            arguments.putString("PRICE", String.valueOf(model.getPrice()));
            arguments.putString("RATING", String.valueOf(model.getRating().getRate()));
            arguments.putString("REVIEWS", String.valueOf(model.getRating().getCount()));
            arguments.putString("DESCRIPTION", model.getDescription());

            Fragment myFragment = new ProductFragment();  //sending this data to product fragment, which we got from adapter to this MainTwoActivity
            myFragment.setArguments(arguments);
            setFragment(myFragment);
//            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutTwo, myFragment).commit();


//            TextView textView = findViewById(R.id.trialTextView);   //setting data on textview
//            textView.setText((CharSequence) model.getTitle());
//            Log.d("aaa", "onCreate: " + model);

        }
//        entitiesForCart = database.databaseAccessObject().getUserCartInfo();
////        categorycartText.setText(entitiesForCart.getPrice_ForCart());
//        Log.d("cartTest", "onCreateView: "+entitiesForCart);


    }

    public void setFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(frameLayoutTwo.getId(), fragment).commit();
    }

//    @Override
//    public void onDataPassed(String data) {
//        Intent intent = new Intent(MainTwoActivity.this, PaymentActivity.class);
//        intent.putExtra("amount",data);
//        startActivity(intent);
//    }

    @Override
    public void onPaymentSuccess(String s) {

    }

    @Override
    public void onPaymentError(int i, String s) {

    }
}