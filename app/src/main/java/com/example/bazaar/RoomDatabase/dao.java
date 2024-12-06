package com.example.bazaar.RoomDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@androidx.room.Dao
public interface dao {

    @Insert
    void insert(CartData cartItem);

    @Update
    void update(CartData cartItem);

    @Delete
    void delete(CartData cartItem);

    @Query("DELETE FROM Cart")
    void clearCart();

    @Query("SELECT * FROM Cart")
    LiveData<List<CartData>> getAllCartItems();

    @Query("SELECT SUM(quantity * price) FROM Cart")
    double getTotalPrice();
}