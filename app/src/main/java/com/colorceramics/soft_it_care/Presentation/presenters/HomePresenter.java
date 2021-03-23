package com.colorceramics.soft_it_care.Presentation.presenters;

import com.colorceramics.soft_it_care.Models.AuctionProduct;
import com.colorceramics.soft_it_care.Models.Banner;
import com.colorceramics.soft_it_care.Models.Brand;
import com.colorceramics.soft_it_care.Models.Category;
import com.colorceramics.soft_it_care.Models.FlashDeal;
import com.colorceramics.soft_it_care.Models.Product;
import com.colorceramics.soft_it_care.Models.SliderImage;
import com.colorceramics.soft_it_care.Network.response.AppSettingsResponse;
import com.colorceramics.soft_it_care.Network.response.AuctionBidResponse;
import com.colorceramics.soft_it_care.Presentation.ui.fragments.HomeView;
import com.colorceramics.soft_it_care.domain.executor.Executor;
import com.colorceramics.soft_it_care.domain.executor.MainThread;
import com.colorceramics.soft_it_care.domain.interactors.AppSettingsInteractor;
import com.colorceramics.soft_it_care.domain.interactors.AuctionBidInteractor;
import com.colorceramics.soft_it_care.domain.interactors.AuctionProductInteractor;
import com.colorceramics.soft_it_care.domain.interactors.BannerInteractor;
import com.colorceramics.soft_it_care.domain.interactors.BestSellingInteractor;
import com.colorceramics.soft_it_care.domain.interactors.BrandInteractor;
import com.colorceramics.soft_it_care.domain.interactors.FeaturedProductInteractor;
import com.colorceramics.soft_it_care.domain.interactors.FlashDealInteractor;
import com.colorceramics.soft_it_care.domain.interactors.HomeCategoriesInteractor;
import com.colorceramics.soft_it_care.domain.interactors.SliderInteractor;
import com.colorceramics.soft_it_care.domain.interactors.TodaysDealInteractor;
import com.colorceramics.soft_it_care.domain.interactors.TopCategoryInteractor;
import com.colorceramics.soft_it_care.domain.interactors.impl.AppSettingsInteractorImpl;
import com.colorceramics.soft_it_care.domain.interactors.impl.AuctionBidInteractorImpl;
import com.colorceramics.soft_it_care.domain.interactors.impl.AuctionProductInteractorImpl;
import com.colorceramics.soft_it_care.domain.interactors.impl.BannerInteractorImpl;
import com.colorceramics.soft_it_care.domain.interactors.impl.BestSellingInteractorImpl;
import com.colorceramics.soft_it_care.domain.interactors.impl.BrandInteractorImpl;
import com.colorceramics.soft_it_care.domain.interactors.impl.FeaturedProductInteractorImpl;
import com.colorceramics.soft_it_care.domain.interactors.impl.FlashDealInteractorImpl;
import com.colorceramics.soft_it_care.domain.interactors.impl.HomeCategoriesInteractorImpl;
import com.colorceramics.soft_it_care.domain.interactors.impl.SliderInteractorImpl;
import com.colorceramics.soft_it_care.domain.interactors.impl.TodaysDealInteractorImpl;
import com.colorceramics.soft_it_care.domain.interactors.impl.TopCategoriesInteractorImpl;
import com.google.gson.JsonObject;

import java.util.List;

public class HomePresenter extends AbstractPresenter implements AppSettingsInteractor.CallBack, SliderInteractor.CallBack, HomeCategoriesInteractor.CallBack, TodaysDealInteractor.CallBack, FlashDealInteractor.CallBack, BestSellingInteractor.CallBack, BannerInteractor.CallBack, FeaturedProductInteractor.CallBack, BrandInteractor.CallBack, TopCategoryInteractor.CallBack, AuctionProductInteractor.CallBack, AuctionBidInteractor.CallBack {
    private HomeView homeView;

    public HomePresenter(Executor executor, MainThread mainThread, HomeView homeView) {
        super(executor, mainThread);
        this.homeView = homeView;
    }

    public void getAppSettings(){
        new AppSettingsInteractorImpl(mExecutor, mMainThread, this).execute();
    }

    public void getSliderImages() {
        new SliderInteractorImpl(mExecutor, mMainThread, this).execute();
    }

    public void getHomeCategories() {
        new HomeCategoriesInteractorImpl(mExecutor, mMainThread, this).execute();
    }

    public void getTodaysDeal() {
        new TodaysDealInteractorImpl(mExecutor, mMainThread, this).execute();
    }

    public void getFlashDeal() {
        new FlashDealInteractorImpl(mExecutor, mMainThread, this).execute();
    }

    public void getBestSelling() {
        new BestSellingInteractorImpl(mExecutor, mMainThread, this).execute();
    }

    public void getBanners() {
        new BannerInteractorImpl(mExecutor, mMainThread, this).execute();
    }

    public void getFeaturedProducts() {
        new FeaturedProductInteractorImpl(mExecutor, mMainThread, this).execute();
    }

    public void getTopCategories() {
        new TopCategoriesInteractorImpl(mExecutor, mMainThread, this).execute();
    }

    public void getPopularbrands() {
        new BrandInteractorImpl(mExecutor, mMainThread, this).execute();
    }

    public void getAuctionProducts() {
        new AuctionProductInteractorImpl(mExecutor, mMainThread, this).execute();
    }

    public void submitBid(JsonObject jsonObject, String token){
        new AuctionBidInteractorImpl(mExecutor, mMainThread, this, jsonObject, token).execute();
    }

    @Override
    public void onAppSettingsLoaded(AppSettingsResponse appSettingsResponse) {
        if (homeView != null){
            homeView.onAppSettingsLoaded(appSettingsResponse);
        }
    }

    @Override
    public void onAppSettingsLoadedError() {

    }

    @Override
    public void onSliderDownloaded(List<SliderImage> sliderImages) {
        if (homeView != null) {
            homeView.setSliderImages(sliderImages);
        }
    }

    @Override
    public void onSliderDownloadError() {

    }

    @Override
    public void onHomeCategoriesDownloaded(List<Category> categories) {
        if (homeView != null) {
            homeView.setHomeCategories(categories);
        }
    }

    @Override
    public void onHomeCategoriesDownloadError() {

    }

    @Override
    public void onTodaysDealProductDownloaded(List<Product> products) {
        if (homeView != null) {
            homeView.setTodaysDeal(products);
        }
    }

    @Override
    public void onTodaysDealProductDownloadError() {

    }

    @Override
    public void onBestSellingProductDownloaded(List<Product> products) {
        if (homeView != null) {
            homeView.setBestSelling(products);
        }
    }

    @Override
    public void onBestSellingProductDownloadError() {

    }

    @Override
    public void onFeaturedProductDownloaded(List<Product> products) {
        if (homeView != null) {
            homeView.setFeaturedProducts(products);
        }
    }

    @Override
    public void onFeaturedProductDownloadError() {

    }

    @Override
    public void onTopCategoriesDownloaded(List<Category> categories) {
        if (homeView != null) {
            homeView.setTopCategories(categories);
        }
    }

    @Override
    public void onTopCategoriesDownloadError() {

    }

    @Override
    public void onAuctionProductDownloaded(List<AuctionProduct> auctionProducts) {
        if (homeView != null) {
            homeView.setAuctionProducts(auctionProducts);
        }
    }

    @Override
    public void onAuctionProductDownloadError() {

    }

    @Override
    public void onBannersDownloaded(List<Banner> banners) {
        if (homeView != null){
            homeView.setBanners(banners);
        }
    }

    @Override
    public void onBannersDownloadError() {

    }

    @Override
    public void onBrandsDownloaded(List<Brand> brands) {
        if (homeView != null){
            homeView.setPopularBrands(brands);
        }
    }

    @Override
    public void onBrandsDownloadError() {

    }

    @Override
    public void onBidSubmitted(AuctionBidResponse auctionBidResponse) {
        if (homeView != null){
            homeView.onAuctionBidSubmitted(auctionBidResponse);
        }
    }

    @Override
    public void onBidSubmittedError() {

    }

    @Override
    public void onFlashDealProductDownloaded(FlashDeal flashDeal) {
        if (homeView != null) {
            homeView.setFlashDeal(flashDeal);
        }
    }

    @Override
    public void onFlashDealProductDownloadError() {

    }
}