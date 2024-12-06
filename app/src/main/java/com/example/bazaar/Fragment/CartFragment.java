package com.example.bazaar.Fragment;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bazaar.Adapter.CartAdapter;
import com.example.bazaar.Order_Activity;
import com.example.bazaar.R;
import com.example.bazaar.RoomDatabase.CartData;
import com.example.bazaar.RoomDatabase.CartViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment {
Button buynow;
RecyclerView recyclerView;
CartAdapter cartAdapter;
CartViewModel viewModel;
ArrayList<CartData> cart;
TextView amount;
    int Total;
double sum;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_cart, container, false);
        Application application = (Application) getContext().getApplicationContext();
        buynow = view.findViewById(R.id.buynow);
        viewModel = new ViewModelProvider.AndroidViewModelFactory(application).create(CartViewModel.class);
        recyclerView = view.findViewById(R.id.recyclerview_room);
        amount = view.findViewById(R.id.amount);

        cart = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        cartAdapter = new CartAdapter(getContext(),cart,viewModel);
        recyclerView.setAdapter(cartAdapter);
        viewModel.getAllCartItems().observe((LifecycleOwner) getContext(), cartData -> {

                cart.clear();               // Clear existing data
                cart.addAll(cartData);
            // Add new data
                cartAdapter.notifyDataSetChanged();


        });
        viewModel.getAllCartItems().observe((LifecycleOwner) getContext(), cartData -> {
            Total =0;
            sum =0;
            for(CartData data:cartData){
                sum =data.getPrice();
                Total+=sum;
            }
            Log.d("show", "onCreateView: "+ Total);
            amount.setText(String.valueOf(Total));
            cartAdapter.notifyDataSetChanged();
        });
        buynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), Order_Activity.class);
//                i.putExtra("key_list",cart);
                i.putExtra("key_amount",String.valueOf(Total));
                startActivity(i);
            }
        });

        return  view;
    }
}