package com.example.bazaar.RoomDatabase;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CartRepository extends Application {
    private dao cartDao;
    private ExecutorService executorService;
    private LiveData<List<CartData>> getallItem;

    public CartRepository(Context context) {
        CartDatabase database = CartDatabase.getDatabase(context);
        cartDao = database.cartDao();
        executorService = Executors.newFixedThreadPool(2);
    }

    public void insertCartItem(CartData item) {
        executorService.execute(() -> cartDao.insert(item));
    }

    public void updateCartItem(CartData item) {
        executorService.execute(() -> cartDao.update(item));
    }

    public void deleteCartItem(CartData item) {
        executorService.execute(() -> cartDao.delete(item));
    }

    public void clearCart() {
        executorService.execute(cartDao::clearCart);
    }

    public LiveData<List<CartData>> getAllCartItems() {
        return cartDao.getAllCartItems(); // This should ideally be LiveData for observing data changes
    }

    public double getTotalPrice() {
        return cartDao.getTotalPrice();
    }
}
