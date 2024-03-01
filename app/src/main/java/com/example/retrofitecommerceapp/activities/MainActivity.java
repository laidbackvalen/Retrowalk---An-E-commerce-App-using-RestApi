package com.example.retrofitecommerceapp.activities;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofitecommerceapp.R;
import com.example.retrofitecommerceapp.fragments.CategoriesFragment;
import com.example.retrofitecommerceapp.fragments.HomeFragment;
import com.example.retrofitecommerceapp.fragments.OrdersFragment;
import com.example.retrofitecommerceapp.fragments.ProfileFragment;
import com.example.retrofitecommerceapp.room.database.UserDatabase;
import com.example.retrofitecommerceapp.session.SessionManagement;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    View home, category, orders, profile, viewtop, viewbottom;
    FrameLayout frameLayout;
    ImageView homeimageView, categoryimageView, orderimageView, profileimageView, cartImage, notificationImage, favouriteImage, imageCheck;
    TextView hometextView, categorytextView, ordertextView, profiletextView, cart, notification, favourites, payments, settings, log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//      DRAWER LAYOUT MAIN ACTIVITY
        drawerLayout = findViewById(R.id.drawerLayout);
        cart = findViewById(R.id.cartTextview);
        notification = findViewById(R.id.notificationTextView);
        favourites = findViewById(R.id.favouritesTextView);
        payments = findViewById(R.id.paymentTextView);
        settings = findViewById(R.id.settingsTextView);
        viewtop = findViewById(R.id.topView);
        viewbottom = findViewById(R.id.bottomView);
        cartImage = findViewById(R.id.cartImageHomeTopBar);
        notificationImage = findViewById(R.id.notifyImageHomeTopBar);
        favouriteImage = findViewById(R.id.likedImageHomeTopBar);
        log = findViewById(R.id.logTextView);

        imageCheck = findViewById(R.id.logImageHomeTopBar);
        imageCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "" + new SessionManagement(MainActivity.this).checkSession(), Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, "" + new SessionManagement(MainActivity.this).getUserInfo(), Toast.LENGTH_SHORT).show();
            }
        });

//        boolean session = new SessionManagement(MainActivity.this).checkSession();
//        if (session == true) {
            log.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new SessionManagement(MainActivity.this).logOut();
                    Toast.makeText(MainActivity.this, "" + new SessionManagement(MainActivity.this).checkSession(), Toast.LENGTH_SHORT).show();
                }
            });
//        } else {
//            log.setVisibility(View.GONE);
//        }

        //MAINACTIVITY TO MAINACTIVITYTWO CartFragment
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainTwoActivity.class);
                intent.putExtra("cart", "cart");
                startActivity(intent);

                //cart on click design
                cart.setBackgroundColor(WHITE);
                cart.setTextColor(BLACK);
                cartImage.setImageResource(R.drawable.bag);
                cartImage.setBackgroundColor(WHITE);
                notification.setBackgroundColor(getResources().getColor(R.color.themebrown));
                notification.setTextColor(WHITE);
                notificationImage.setImageResource(R.drawable.bellsvg_white);
                notificationImage.setBackgroundColor(getResources().getColor(R.color.themebrown));
                favourites.setBackgroundColor(getResources().getColor(R.color.themebrown));
                favourites.setTextColor(WHITE);
                favouriteImage.setImageResource(R.drawable.baseline_favorite_border_white);
                favouriteImage.setBackgroundColor(getResources().getColor(R.color.themebrown));
                payments.setTextColor(WHITE);
                settings.setTextColor(WHITE);
            }
        });
        notification.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainTwoActivity.class);
                intent.putExtra("notification", "notification");
                startActivity(intent);

                //Notifications on click design
                notification.setBackgroundColor(WHITE);
                notification.setTextColor(BLACK);
                notificationImage.setImageResource(R.drawable.bellsvg);
                notificationImage.setBackgroundColor(WHITE);
                cart.setBackgroundColor(getResources().getColor(R.color.themebrown));
                cart.setTextColor(WHITE);
                cartImage.setImageResource(R.drawable.bag_white);
                cartImage.setBackgroundColor(getResources().getColor(R.color.themebrown));
                favourites.setBackgroundColor(getResources().getColor(R.color.themebrown));
                favourites.setTextColor(WHITE);
                favouriteImage.setImageResource(R.drawable.baseline_favorite_border_white);
                favouriteImage.setBackgroundColor(getResources().getColor(R.color.themebrown));
                payments.setTextColor(WHITE);
            }
        });
        favourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainTwoActivity.class);
                intent.putExtra("fav", "fav");
                startActivity(intent);

                //Favourites on click design
                notification.setBackgroundColor(getResources().getColor(R.color.themebrown));
                notification.setTextColor(WHITE);
                notificationImage.setImageResource(R.drawable.bellsvg_white);
                notificationImage.setBackgroundColor(getResources().getColor(R.color.themebrown));
                cart.setBackgroundColor(getResources().getColor(R.color.themebrown));
                cart.setTextColor(WHITE);
                cartImage.setImageResource(R.drawable.bag_white);
                cartImage.setBackgroundColor(getResources().getColor(R.color.themebrown));
                favouriteImage.setImageResource(R.drawable.baseline_favorite_border_24);
                favouriteImage.setBackgroundColor(WHITE);
                favourites.setBackgroundColor(WHITE);
                favourites.setTextColor(BLACK);

            }
        });
        payments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        viewtop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        viewbottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        //VIEWS FROM BOTTOM NAV LAYOUT
        home = findViewById(R.id.viewBottomNavLayout);
        category = findViewById(R.id.view2BottomNavLayout);
        orders = findViewById(R.id.view3BottomNavLayout);
        profile = findViewById(R.id.view4BottomNavLayout);
        frameLayout = findViewById(R.id.frameLayout);

        //IMAGEVIEW FROM BOTTOM NAV LAYOUT
        homeimageView = findViewById(R.id.homeimageBottomNavLayout);
        categoryimageView = findViewById(R.id.categoryimageBottomNavLayout);
        orderimageView = findViewById(R.id.ordersImageBottomNavLayout);
        profileimageView = findViewById(R.id.profileimageBottomNavLayout);

        //TEXTVIEW FROM BOTTOM NAV LAYOUT
        hometextView = findViewById(R.id.homeTextBottomNavLayout);
        categorytextView = findViewById(R.id.categoryTextBottomNavLayout);
        ordertextView = findViewById(R.id.orderTextBottomNavLayout);
        profiletextView = findViewById(R.id.profileTextBottomNavLayout);

        //this is use to open HomeFragment ONCREATE
        setFragment(new HomeFragment());
//        home.setBackgroundColor(getColor(R.color.white));
        homeimageView.setImageResource(R.drawable.home_white_svg);
        hometextView.setTextColor(getResources().getColor(R.color.theme));

        //OnClicks

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new HomeFragment());

//                home.setBackgroundColor(getColor(R.color.white));
                homeimageView.setImageResource(R.drawable.home_white_svg);
                hometextView.setTextColor(getResources().getColor(R.color.theme));

//                category.setBackgroundColor(getColor(R.color.white));
                categoryimageView.setImageResource(R.drawable.category);
                categorytextView.setTextColor(WHITE);

                orderimageView.setImageResource(R.drawable.orderbox);
                ordertextView.setTextColor(WHITE);

                profileimageView.setImageResource(R.drawable.baseline_person_24);
                profiletextView.setTextColor(WHITE);

            }
        });
        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new CategoriesFragment());

//                home.setBackgroundColor(getColor(R.color.white));
                homeimageView.setImageResource(R.drawable.baseline_home_filled_24);
                hometextView.setTextColor(WHITE);

                //     category.setBackgroundColor(getColor(R.color.white));
                categoryimageView.setImageResource(R.drawable.category_onclick_theme);
                categorytextView.setTextColor(getResources().getColor(R.color.theme));

                orderimageView.setImageResource(R.drawable.orderbox);
                ordertextView.setTextColor(WHITE);

                profileimageView.setImageResource(R.drawable.baseline_person_24);
                profiletextView.setTextColor(WHITE);
            }
        });
        orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new OrdersFragment());

//                home.setBackgroundColor(getColor(R.color.white));
                homeimageView.setImageResource(R.drawable.baseline_home_filled_24);
                hometextView.setTextColor(WHITE);

//                category.setBackgroundColor(getColor(R.color.white));
                categoryimageView.setImageResource(R.drawable.category);
                categorytextView.setTextColor(WHITE);

                orderimageView.setImageResource(R.drawable.orderbox_onclick);
                ordertextView.setTextColor(getResources().getColor(R.color.theme));

                profileimageView.setImageResource(R.drawable.baseline_person_24);
                profiletextView.setTextColor(WHITE);

            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new ProfileFragment());

//                home.setBackgroundColor(getColor(R.color.white));
                homeimageView.setImageResource(R.drawable.baseline_home_filled_24);
                hometextView.setTextColor(WHITE);

//                category.setBackgroundColor(getColor(R.color.white));
                categoryimageView.setImageResource(R.drawable.category);
                categorytextView.setTextColor(WHITE);

                orderimageView.setImageResource(R.drawable.orderbox);
                ordertextView.setTextColor(WHITE);

                profileimageView.setImageResource(R.drawable.profile_person_onclick);
                profiletextView.setTextColor(getResources().getColor(R.color.theme));
            }
        });

//        String p = getIntent().getStringExtra("profile");
//        if (p != null) {
//            setFragment(new ProfileFragment());
//            //                home.setBackgroundColor(getColor(R.color.white));
//            homeimageView.setImageResource(R.drawable.baseline_home_filled_24);
//            hometextView.setTextColor(WHITE);
//
////                category.setBackgroundColor(getColor(R.color.white));
//            categoryimageView.setImageResource(R.drawable.category);
//            categorytextView.setTextColor(WHITE);
//
//            orderimageView.setImageResource(R.drawable.orderbox);
//            ordertextView.setTextColor(WHITE);
//
//            profileimageView.setImageResource(R.drawable.profile_person_onclick);
//            profiletextView.setTextColor(getResources().getColor(R.color.theme));
//        }

    }


    public void setFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(frameLayout.getId(), fragment).commit();
    }

    //OUTSIDE ONCREATE
    public void openDrawer() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawer.openDrawer(GravityCompat.START);
    }

    private void closeDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    protected void onPause() {
        closeDrawer(drawerLayout);
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}