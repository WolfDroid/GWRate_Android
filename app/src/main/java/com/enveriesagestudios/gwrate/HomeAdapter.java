package com.enveriesagestudios.gwrate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.enveriesagestudios.gwrate.model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ImageViewHolder> {

    //Some Variables Declaration
    private Context mContext;
    private List<Product> mProducts;

    //Constructor
    public HomeAdapter (Context context, List<Product> products) {
        mContext = context;
        mProducts = products;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.product_item, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Product productCurrent = mProducts.get(position);
        holder.prod_name.setText(productCurrent.getName());
        holder.prod_price.setText(productCurrent.getPrice());
        Picasso.get().load(productCurrent.getPicture1()).placeholder(R.drawable.ic_menu_camera).fit().centerCrop().into(holder.prod_image);
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{

        TextView prod_name, prod_price;
        ImageView prod_image;
        Button btnPurchase;

        public ImageViewHolder(@NonNull View itemView){
            super(itemView);
            //Product Card
            prod_name = itemView.findViewById(R.id.tvProductName);
            prod_price = itemView.findViewById(R.id.tvProductPrice);
            prod_image = itemView.findViewById(R.id.ivProduct);
            btnPurchase = itemView.findViewById(R.id.btnBuyNow);

        }

    }
}
