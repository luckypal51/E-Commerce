package com.example.bazaar.Fragment;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bazaar.Adapter.CategoryAdapter;
import com.example.bazaar.Adapter.ProductAdapter;
import com.example.bazaar.Model.Category;
import com.example.bazaar.Model.Product;
import com.example.bazaar.R;
import com.example.bazaar.RoomDatabase.CartViewModel;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link homeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class homeFragment extends Fragment {
FirebaseFirestore db;
RecyclerView category_recycler;
CategoryAdapter categoryAdapter;
ArrayList<Category> data;

RecyclerView product_recyclerview;
ProductAdapter productAdapter;
ArrayList<Product> products;
    CartViewModel viewModel;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public homeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment homeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static homeFragment newInstance(String param1, String param2) {
        homeFragment fragment = new homeFragment();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        db = FirebaseFirestore.getInstance();
        Application application = (Application) getContext().getApplicationContext();
        CartViewModel viewModel = new ViewModelProvider.AndroidViewModelFactory(application).create(CartViewModel.class);

        data = new ArrayList<>();
        product_recyclerview = view.findViewById(R.id.recyclerview_product);
        products = new ArrayList<>();
        product_recyclerview.setLayoutManager(new GridLayoutManager(getContext(),2));

        categoryAdapter = new CategoryAdapter(getContext(),data);
        category_recycler = view.findViewById(R.id.recyclerview_category);
        category_recycler.setLayoutManager(new GridLayoutManager(getContext(),4));
        category_recycler.setAdapter(categoryAdapter);
        productAdapter = new ProductAdapter(getContext(),products,viewModel);
        product_recyclerview.setAdapter(productAdapter);
        db.collection("Category").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error!=null){
                    Log.d("data", "onEvent: "+error.toString());
                }
                else{
                    if (value != null && !value.isEmpty()) {
                    for(DocumentChange dc:value.getDocumentChanges()){
                        if (dc.getType()==DocumentChange.Type.ADDED){
                            data.add(dc.getDocument().toObject(Category.class));
                        }
                        categoryAdapter.notifyDataSetChanged();
                    }}
                }
            }
        });

        db.collection("Product").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error!=null){
                    Log.d("data", "onEvent: "+error.toString());
                }
                else{
                    assert value != null;
                    if (value != null && !value.isEmpty()) {
                        for (DocumentChange dc : value.getDocumentChanges()) {
                            if (dc.getType()==DocumentChange.Type.ADDED){
                                products.add(dc.getDocument().toObject(Product.class));
                            }
                            productAdapter.notifyDataSetChanged();
                        }
                    }




                }
            }
        });

        return view;
    }
}