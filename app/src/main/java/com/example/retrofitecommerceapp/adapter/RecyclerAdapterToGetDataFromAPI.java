package com.example.retrofitecommerceapp.adapter;

import static com.example.retrofitecommerceapp.constants.Constants.DB_NAME;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.example.retrofitecommerceapp.R;
import com.example.retrofitecommerceapp.activities.MainTwoActivity;
import com.example.retrofitecommerceapp.model.DataModel;
import com.example.retrofitecommerceapp.room.database.UserDatabase;
import com.example.retrofitecommerceapp.room.entity.UserEntitiesForCart;

import java.io.Serializable;
import java.util.List;

public class RecyclerAdapterToGetDataFromAPI extends RecyclerView.Adapter<RecyclerAdapterToGetDataFromAPI.ViewHolder> {

    Context context;
    List<DataModel> modelList;

    UserEntitiesForCart entitiesForCart;
    UserDatabase database;


    void databaseQuery() {
        database = Room.databaseBuilder(context, UserDatabase.class, DB_NAME).allowMainThreadQueries().build();
    }

    public RecyclerAdapterToGetDataFromAPI(Context context, List<DataModel> modelList) {
        this.context = context;
        this.modelList = modelList;
        databaseQuery();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView, addToCartImage;
        TextView id, title, description, price, rating;
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageDataLayout);
            title = itemView.findViewById(R.id.titleDataLayout);
//            description = itemView.findViewById(R.id.descriptionDataLayout);
            price = itemView.findViewById(R.id.priceDataLayout);
            rating = itemView.findViewById(R.id.categoryDataLayout);
            view = itemView.findViewById(R.id.viewDataLayout);
            addToCartImage = itemView.findViewById(R.id.addToCartImageDataLayout);

        }
    }

    @NonNull
    @Override
    public RecyclerAdapterToGetDataFromAPI.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_data_for_recyclerview, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterToGetDataFromAPI.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
//         holder.id.setText((int) modelList.get(position).getId());
        Glide
                .with(context)
                .load(modelList.get(position).getImageUrl())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.imageView);

        holder.title.setText(modelList.get(position).getTitle());
//       holder.description.setText(modelList.get(position).getDescription());
        holder.price.setText(String.valueOf("INR " + modelList.get(position).getPrice()));
        holder.rating.setText(modelList.get(position).getCategory());

        holder.addToCartImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String price = String.valueOf(modelList.get(position).getPrice());
                String title = String.valueOf(modelList.get(holder.getAdapterPosition()).getTitle());
                String url = String.valueOf(modelList.get(holder.getAdapterPosition()).getImageUrl());
                String category = String.valueOf(modelList.get(holder.getAdapterPosition()).getCategory());
//               int quantity = modelList.get(holder.getAdapterPosition()).getQuantity();
//
//                if (quantity == 0) {
//                    quantity = 1; // Set a default quantity value

                    entitiesForCart = new UserEntitiesForCart(url, title, price, category, 1);
                    database.databaseAccessObject().insertUserCartInfo(entitiesForCart);
                    holder.addToCartImage.setImageResource(R.drawable.done);
//                }
            }
        });

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //sending this data to an activity
                Intent intent = new Intent(context, MainTwoActivity.class);
                intent.putExtra("value", (Serializable) modelList.get(holder.getAdapterPosition()));
                context.startActivity(intent);
//               context.startActivity(new Intent(context, MainTwoActivity.class));

            }
        });

        // POP UP MENU
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu popupMenu = new PopupMenu(context, view);
                popupMenu.getMenu().add("Download Image");

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        // Toast message on menu item clicked
                        Toast.makeText(context, "item deleted successfully", Toast.LENGTH_SHORT).show();
                        String url = modelList.get(position).getImageUrl();
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setData(Uri.parse(url));
                        context.startActivity(intent);
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
