package com.example.bazaar.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bazaar.Model.Order;
import com.example.bazaar.R;

import java.util.ArrayList;

public class Order_Details_Recyclerview extends RecyclerView.Adapter<Order_Details_Recyclerview.MyViewHolder> {
    Context context;
    ArrayList<Order> datalist;

    public Order_Details_Recyclerview(Context context, ArrayList<Order> datalist) {
        this.context = context;
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_detail_recyclerview,null,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
           Order data = datalist.get(position);
           holder.address.setText(data.getAddress());
           holder.phone.setText(data.getPhone_no());
           holder.product_name.setText(data.getProduct_Name());
           holder.price.setText(data.getProduct_price());
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView address,phone,product_name,price;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            address = itemView.findViewById(R.id.order_address_detail);
            phone = itemView.findViewById(R.id.order_Phone_detail);
            product_name = itemView.findViewById(R.id.order_product_name_details);
            price = itemView.findViewById(R.id.order_price_details);
        }
    }
}
