package com.example.bazaar.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bazaar.R;
import com.example.bazaar.RoomDatabase.CartData;
import com.example.bazaar.RoomDatabase.CartViewModel;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<CartData> cartData;
    private CartViewModel viewModel;

    public CartAdapter(Context context, ArrayList<CartData> cartData, CartViewModel viewModel) {
        this.context = context;
        this.cartData = cartData;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart,null,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        CartData cart = cartData.get(position);
        holder.Name.setText(cart.getProductName());
        holder.Price.setText(String.valueOf(cart.getPrice()));
        Glide.with(context).load(cart.getImage()).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartData del = new CartData(cart.getImage(), cart.getProductName(), 1, cart.getPrice());
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Are you sure you want to delete all cart items?")
                        .setPositiveButton("Yes", (dialog, which) -> {
                            viewModel.deleteCartItem(cart); // Call the deletion method
                            cartData.remove(cart);
                           notifyDataSetChanged();

                        })
                        .setNegativeButton("No", (dialog, which) -> {
                            dialog.dismiss(); // Dismiss the dialog if user cancels
                        })
                        .show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return cartData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView Name,Price;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.cart_image);
            Name = itemView.findViewById(R.id.cart_name);
            Price = itemView.findViewById(R.id.cart_price);
        }
    }
}
