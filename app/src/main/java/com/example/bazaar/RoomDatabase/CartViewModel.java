package com.example.bazaar.RoomDatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CartViewModel extends AndroidViewModel {
    private CartRepository repository;
    private LiveData<List<CartData>> allCartItems;

    public CartViewModel(@NonNull Application application) {
        super(application);
        repository = new CartRepository(application);
        allCartItems = repository.getAllCartItems();
    }

    public void insertCartItem(CartData item) {
        repository.insertCartItem(item);
    }

    public void updateCartItem(CartData item) {
        repository.updateCartItem(item);
    }

    public void deleteCartItem(CartData item) {
        repository.deleteCartItem(item);
    }

    public void clearCart() {
        repository.clearCart();
    }

    public LiveData<List<CartData>> getAllCartItems() {
        return allCartItems;
    }

    public double getTotalPrice() {
        return repository.getTotalPrice();
    }
}

