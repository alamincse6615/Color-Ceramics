package com.colorceramics.soft_it_care.Presentation.ui.activities;

import com.colorceramics.soft_it_care.Models.Product;
import com.colorceramics.soft_it_care.Models.ProductDetails;
import com.colorceramics.soft_it_care.Network.response.AddToCartResponse;
import com.colorceramics.soft_it_care.Network.response.AddToWishlistResponse;
import com.colorceramics.soft_it_care.Network.response.CheckWishlistResponse;
import com.colorceramics.soft_it_care.Network.response.RemoveWishlistResponse;

import java.util.List;

public interface ProductDetailsView {
    void setProductDetails(ProductDetails productDetails);
    void setRelatedProducts(List<Product> relatedProducts);
    void setTopSellingProducts(List<Product> topSellingProducts);
    void setAddToCartMessage(AddToCartResponse addToCartResponse);
    void setAddToWishlistMessage(AddToWishlistResponse addToWishlistMessage);
    void onCheckWishlist(CheckWishlistResponse checkWishlistResponse);
    void onRemoveFromWishlist(RemoveWishlistResponse removeWishlistResponse);
}
