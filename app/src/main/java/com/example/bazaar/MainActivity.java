package com.example.bazaar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bazaar.Fragment.CartFragment;
import com.example.bazaar.Fragment.ProfileFragment;
import com.example.bazaar.Fragment.homeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
BottomNavigationView bottomNavigationView;
FrameLayout frameLayout;
com.example.bazaar.Fragment.homeFragment homeFragment;
ProfileFragment profileFragment;
CartFragment cartFragment;
ImageView searchbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        frameLayout = findViewById(R.id.frame_layout);
        searchbar = findViewById(R.id.searchbar);
        homeFragment = new homeFragment();
        profileFragment = new ProfileFragment();
        cartFragment = new CartFragment();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.home_nav){
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,homeFragment).commit();
                }
                if (item.getItemId()==R.id.profile_nav){
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,profileFragment).commit();
                }
                if (item.getItemId()==R.id.cart_nav){
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,cartFragment).commit();
                }
                return true;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.home_nav);
        searchbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Search.class);
                startActivity(i);

            }
        });
    }
}