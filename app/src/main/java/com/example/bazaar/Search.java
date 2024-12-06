package com.example.bazaar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bazaar.Adapter.Searchadapter;
import com.example.bazaar.Model.Product;
import com.example.bazaar.RoomDatabase.CartViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Search extends AppCompatActivity {
EditText search;
Button searchbutton;
RecyclerView recyclerView;
FirebaseFirestore db = FirebaseFirestore.getInstance();
ArrayList<Product> products;
Searchadapter searchadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        CartViewModel viewModel = new ViewModelProvider.AndroidViewModelFactory(this.getApplication()).create(CartViewModel.class);
        search = findViewById(R.id.searcheditext);
        searchbutton = findViewById(R.id.searchButton);
        recyclerView = findViewById(R.id.searchRecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        search.requestFocus();
        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchterm = search.getText().toString();
//                if (searchterm.isEmpty()||searchterm.length()<3){
//                    Toast.makeText(Search.this, "enter the item name", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                setupSearchRecyclerView(searchterm);
                searchadapter = new Searchadapter(Search.this,products,viewModel);
                recyclerView.setAdapter(searchadapter);
                searchadapter.notifyDataSetChanged();
            }


        });

    }
    private void setupSearchRecyclerView(String searchterm) {
       db.collection("Product").whereGreaterThanOrEqualTo("Name",searchterm).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
           @Override
           public void onComplete(@NonNull Task<QuerySnapshot> task) {
               products = new ArrayList<>();
              if (task.isSuccessful()){
                  for (QueryDocumentSnapshot dc:task.getResult()){
                      Product data = dc.toObject(Product.class);
                      Log.d("data", "onComplete: "+data.getName());
                      products.add(data);
                      searchadapter.notifyDataSetChanged();
                  }
              }
              else {
                  Toast.makeText(Search.this, "Search Error"+task.getException().toString(), Toast.LENGTH_SHORT).show();
              }
           }
       });
    }
}