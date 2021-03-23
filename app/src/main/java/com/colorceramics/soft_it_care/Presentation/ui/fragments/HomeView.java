package com.colorceramics.soft_it_care.Presentation.ui.fragments;

import com.colorceramics.soft_it_care.Models.AuctionProduct;
import com.colorceramics.soft_it_care.Models.Banner;
import com.colorceramics.soft_it_care.Models.Brand;
import com.colorceramics.soft_it_care.Models.Category;
import com.colorceramics.soft_it_care.Models.FlashDeal;
import com.colorceramics.soft_it_care.Models.Product;
import com.colorceramics.soft_it_care.Models.SliderImage;
import com.colorceramics.soft_it_care.Network.response.AppSettingsResponse;
import com.colorceramics.soft_it_care.Network.response.AuctionBidResponse;

import java.util.List;

public interface HomeView {
    void onAppSettingsLoaded(AppSettingsResponse appSettingsResponse);

    void setSliderImages(List<SliderImage> sliderImages);

    void setHomeCategories(List<Category> categories);

    void setTodaysDeal(List<Product> products);

    void setFlashDeal(FlashDeal flashDeal);

    void setBanners(List<Banner> banners);

    void setBestSelling(List<Product> products);

    void setFeaturedProducts(List<Product> products);

    void setTopCategories(List<Category> categories);

    void setPopularBrands(List<Brand> brands);

    void setAuctionProducts(List<AuctionProduct> auctionProducts);

    void onAuctionBidSubmitted(AuctionBidResponse auctionBidResponse);
}