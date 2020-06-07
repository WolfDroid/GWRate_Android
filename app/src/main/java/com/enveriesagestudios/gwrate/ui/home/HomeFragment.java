package com.enveriesagestudios.gwrate.ui.home;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.enveriesagestudios.gwrate.HomeAdapter;
import com.enveriesagestudios.gwrate.R;
import com.enveriesagestudios.gwrate.model.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    ViewFlipper productBanner;
    private RecyclerView mRecyclerView;
    private HomeAdapter mAdapter;

    //Firebase Database
    private DatabaseReference mDatabaseRef;
    private List<Product> mProducts;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        //Banner Product
        productBanner = root.findViewById(R.id.img_Banner);
        int slider[]={
                R.drawable.common_google_signin_btn_icon_dark,
                R.drawable.common_full_open_on_phone
        };

        //Call the Flipping Function
        for (int slide:slider){
            bannerFlipper(slide);
        }

        showProductList();

        return root;
    }

    private void showProductList() {
        mRecyclerView = getView().findViewById(R.id.rvProduct);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        mProducts = new ArrayList<>();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Product_Database");
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapShot: dataSnapshot.getChildren() ){
                    Product product = postSnapShot.getValue(Product.class);
                    mProducts.add(product);
                }
                mAdapter = new HomeAdapter(getActivity().getApplicationContext(), mProducts);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity().getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }

    //Banner Flipping Function
    public void bannerFlipper(int image){
        ImageView imageView = new ImageView(getContext());
        imageView.setImageResource(image);
        productBanner.setFlipInterval(6000);
        productBanner.setAutoStart(true);
        productBanner.setInAnimation(getContext(), android.R.anim.fade_in);
        productBanner.setOutAnimation(getContext(), android.R.anim.fade_out);
    }
}
