package com.example.retrofitecommerceapp.adapter;

import static com.example.retrofitecommerceapp.constants.Constants.DB_NAME;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.example.retrofitecommerceapp.R;
import com.example.retrofitecommerceapp.room.database.UserDatabase;
import com.example.retrofitecommerceapp.room.entity.UserEntitiesForCart;

import java.util.List;

public class RecyclerAdapterForCartItems extends RecyclerView.Adapter<RecyclerAdapterForCartItems.ViewHolder> {
    Context context;
    List<UserEntitiesForCart> settingUserEntitiesForCart;
    UserEntitiesForCart entitiesForCart;
    int count;
    float changedprice;
    float baseprice;
    UserDatabase database;

    private void databaseQuery() {
        database = Room.databaseBuilder(context, UserDatabase.class, DB_NAME).allowMainThreadQueries().build();
    }

    public RecyclerAdapterForCartItems(Context context, List<UserEntitiesForCart> settingUserEntitiesForCart) {
        this.context = context;
        this.settingUserEntitiesForCart = settingUserEntitiesForCart;
        databaseQuery();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View addQuantity, reduceQuantity;
        ImageView productImageCart, removeProductFromCart;
        TextView productTitleCart, productPriceCart, productCategoryCart, productQuantityCart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImageCart = itemView.findViewById(R.id.productImageCartItemLayoutTextView);
            productTitleCart = itemView.findViewById(R.id.titleCartItemLayoutTextView);
            productCategoryCart = itemView.findViewById(R.id.categoryCartItemLayoutTextView);
            productPriceCart = itemView.findViewById(R.id.priceCartItemLayoutTextView);
            removeProductFromCart = itemView.findViewById(R.id.removeFromCartItemLayoutTextView);
            addQuantity = itemView.findViewById(R.id.viewAddQuantity);
            reduceQuantity = itemView.findViewById(R.id.viewReduceQuantity);
            productQuantityCart = itemView.findViewById(R.id.qtyNumberTextViewCart);
        }
    }

    @NonNull
    @Override
    public RecyclerAdapterForCartItems.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_items_layout, parent, false);
        ViewHolder v = new ViewHolder(view);
        return v;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterForCartItems.ViewHolder holder, int position) {
//        holder.productImageCart.setImageResource(settingUserEntitiesForCart.get(position).getName());
        Glide
                .with(context)
                .load(settingUserEntitiesForCart.get(position).getImageUrl_ForCart())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.productImageCart);
        holder.productPriceCart.setText(settingUserEntitiesForCart.get(position).getPrice_ForCart());
        holder.productTitleCart.setText(settingUserEntitiesForCart.get(position).getTitle_ForCart());
        holder.productCategoryCart.setText(settingUserEntitiesForCart.get(position).getCategory_ForCart());
        holder.productQuantityCart.setText(String.valueOf(settingUserEntitiesForCart.get(position).getQunatity_ForCort()));

        holder.addQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = Integer.parseInt(String.valueOf(holder.productQuantityCart.getText()));
                if (count < 20) {
                    count++;
                    holder.productQuantityCart.setText(String.valueOf(count));
                    baseprice = Float.parseFloat(settingUserEntitiesForCart.get(holder.getAdapterPosition()).getPrice_ForCart());
                    changedprice = baseprice * count;
                    holder.productPriceCart.setText(String.valueOf(changedprice));
//                    notifyItemChanged(position);
//                    notifyDataSetChanged();
                }

                entitiesForCart = settingUserEntitiesForCart.get(holder.getAdapterPosition());
                entitiesForCart.setQunatity_ForCort(count);
                database.databaseAccessObject().updateUserCartInfo(entitiesForCart);

                Toast.makeText(context, "" + settingUserEntitiesForCart.get(holder.getAdapterPosition()).getQunatity_ForCort(), Toast.LENGTH_SHORT).show();
//                entitiesForCart = settingUserEntitiesForCart.get(holder.getAdapterPosition());
//                entitiesForCart.setPrice_ForCart(String.valueOf(changedprice));
//                database.databaseAccessObject().updateUserCartInfo(entitiesForCart);
            }
        });

        holder.reduceQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = Integer.parseInt(String.valueOf(holder.productQuantityCart.getText()));
                if (count > 1) {
                    count--;
                    holder.productQuantityCart.setText(String.valueOf(count));
                    baseprice = Float.parseFloat(settingUserEntitiesForCart.get(holder.getAdapterPosition()).getPrice_ForCart());
                    changedprice = baseprice * count;
                    holder.productPriceCart.setText(String.valueOf(changedprice));
//                    notifyItemChanged(position);
//                    notifyDataSetChanged();
                }

                entitiesForCart = settingUserEntitiesForCart.get(holder.getAdapterPosition());
                entitiesForCart.setQunatity_ForCort(count);
                database.databaseAccessObject().updateUserCartInfo(entitiesForCart);

                Toast.makeText(context, "" + settingUserEntitiesForCart.get(holder.getAdapterPosition()).getQunatity_ForCort(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.removeProductFromCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.databaseAccessObject().deleteUserCartInfo(settingUserEntitiesForCart.get(holder.getAdapterPosition()));
                settingUserEntitiesForCart.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return settingUserEntitiesForCart.size();
    }
}
