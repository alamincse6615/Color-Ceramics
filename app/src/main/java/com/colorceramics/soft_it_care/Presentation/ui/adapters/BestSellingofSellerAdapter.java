package com.colorceramics.soft_it_care.Presentation.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.colorceramics.soft_it_care.Models.Product;
import com.colorceramics.soft_it_care.Presentation.ui.listeners.ProductClickListener;
import com.colorceramics.soft_it_care.R;

import java.util.List;

public class BestSellingofSellerAdapter  extends BestSellingAdapter{

    private LayoutInflater mInflater;

    public BestSellingofSellerAdapter(Context context, List<Product> mProducts, ProductClickListener productClickListener) {
        super(context, mProducts, productClickListener);
        this.mInflater = LayoutInflater.from(context);
    }

    // inflates the row layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.best_selling_of_seller_product_box, parent, false);
        return new ViewHolder(view);
    }
}
