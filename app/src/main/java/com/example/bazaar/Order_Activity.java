package com.example.bazaar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;

import com.example.bazaar.RoomDatabase.CartData;
import com.example.bazaar.RoomDatabase.CartViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Order_Activity extends AppCompatActivity {
TextView amount;
EditText address,phone;
Button button;
CartViewModel viewModel;
ArrayList<CartData> cart;
FirebaseFirestore db;
FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        db =FirebaseFirestore.getInstance();
        mAuth =FirebaseAuth.getInstance();


        viewModel = new ViewModelProvider.AndroidViewModelFactory(Order_Activity.this.getApplication()).create(CartViewModel.class);
        amount = findViewById(R.id.Totalamount);
        address = findViewById(R.id.order_address);
        phone = findViewById(R.id.order_mobile_number);
        button = findViewById(R.id.order_now);
        viewModel.getAllCartItems().observe(Order_Activity.this, cartData -> {
            cart = new ArrayList<>(cartData);
            cart.addAll(cartData);
            // Add new data

        });
        Intent i = getIntent();
        String price = i.getStringExtra("key_amount");

        amount.setText("Total amount : "+price);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((address.getText().toString()).isEmpty()){
                    Toast.makeText(Order_Activity.this, "enter the address", Toast.LENGTH_SHORT).show();
                }
                else if ((phone.getText().toString()).isEmpty()){
                    Toast.makeText(Order_Activity.this, "enter the phone number", Toast.LENGTH_SHORT).show();

                }
                else {


                    for(CartData cartData:cart){
                        FirebaseUser user = mAuth.getCurrentUser();
                        String userId = user.getUid();
                        Map<String,String> map = new HashMap<>();
                        map.put("UserId",userId);
                        map.put("Product_Name",cartData.getProductName());
                        map.put("Product_price",String.valueOf(cartData.getPrice()));
                        map.put("Address",address.getText().toString());
                        map.put("Phone_no",phone.getText().toString());
                        db.collection("Orders").document().set(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(Order_Activity.this, "Order successfull", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                }
            }
        });

    }
}