package com.example.bazaar.RoomDatabase;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {CartData.class}, version = 1)
public abstract class CartDatabase extends RoomDatabase {
    public abstract dao cartDao();
    public static  final int Number_of_Thread =4;
    private static volatile CartDatabase Instance;
    private static final ExecutorService dataExecution = Executors.newFixedThreadPool(Number_of_Thread);
    public static  CartDatabase getDatabase(final Context context){
        if (Instance==null){
            synchronized(CartDatabase.class){
                if (Instance ==null){
                    Instance = Room.databaseBuilder(context.getApplicationContext(),CartDatabase.class,"Cart_database").addCallback(sRoomDatabasecallback).build();
                }
            }
        }
        return Instance;
    }
    private static final RoomDatabase.Callback sRoomDatabasecallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            dataExecution.execute(() -> {
                dao cartdao = Instance.cartDao();
                cartdao.clearCart();

            });
        }
    };
}
