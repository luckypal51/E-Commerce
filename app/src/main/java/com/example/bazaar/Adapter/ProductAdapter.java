package com.example.bazaar.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bazaar.Model.Product;
import com.example.bazaar.R;
import com.example.bazaar.RoomDatabase.CartData;
import com.example.bazaar.RoomDatabase.CartViewModel;

import java.util.ArrayList;
import java.util.jar.Attributes;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<Product> data;
    private CartViewModel viewModel;

    public ProductAdapter(Context context, ArrayList<Product> data, CartViewModel viewModel) {
        this.context = context;
        this.data = data;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product,null,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
              Product product = data.get(position);
              holder.Name.setText(product.getName());
              holder.Price.setText(product.getPrice());
        Glide.with(context).load(product.getImage()).into(holder.imageView);
        holder.add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double s = Double.parseDouble(product.getPrice());
                CartData cat = new CartData(product.getImage(), product.getName(), 1,s);
                viewModel.insertCartItem(cat);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView Name ,Price;
        Button add_to_cart;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView2);
            Name = itemView.findViewById(R.id.Product_name);
            Price = itemView.findViewById(R.id.price);
            add_to_cart = itemView.findViewById(R.id.Add_to_cart);

        }
    }
}
