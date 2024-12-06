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

public class Searchadapter extends RecyclerView.Adapter<Searchadapter.MyViewHolder> {
    Context context;
    ArrayList<Product> data;
    CartViewModel viewModel;

    public Searchadapter(Context context, ArrayList<Product> data, CartViewModel viewModel) {
        this.context = context;
        this.data = data;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.searchrecyclerview,null,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            Product product = data.get(position);
            holder.Name.setText(product.getName());
            holder.Price.setText(product.getPrice());
        Glide.with(context).load(product.getImage()).into(holder.imageView);
          holder.add.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  double price = Double.parseDouble(product.getPrice());
                  CartData cat = new CartData(product.getImage(), product.getName(), 1,price);
                  viewModel.insertCartItem(cat);
              }
          });

    }

    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size(); // Safe access
        } else {
            return 0; // Or handle null case appropriately
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
       ImageView imageView;
       TextView Name, Price;
       Button add;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.searchitemImage);
            Name = itemView.findViewById(R.id.searchitemname);
            Price = itemView.findViewById(R.id.searchItemrupees);
            add = itemView.findViewById(R.id.searchadditem);
        }
    }
}
