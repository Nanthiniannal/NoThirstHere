package com.example.nothirsthere;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{


    private Context mCtx;
    private List<Product> productList;


    public ProductAdapter(Context mCtx, List<Product> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.product_list, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product product = productList.get(position);

        //loading the image


        holder.e1.setText(product.getEmail());
        holder.s1.setText("Order Date :"+product.getD());
        holder.s2.setText("No of Cans :"+product.getCan());



    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView e1,s1,s2,s3,s4,s5,s6;

        public ProductViewHolder(View itemView) {
            super(itemView);

            e1 = itemView.findViewById(R.id.e1);
            s1 = itemView.findViewById(R.id.s1);
            s2 = itemView.findViewById(R.id.s2);




        }
    }
}
