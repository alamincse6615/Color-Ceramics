package com.activeitzone.activeecommercecms.Presentation.ui.fragments.impl;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.activeitzone.activeecommercecms.Models.AuctionProduct;
import com.activeitzone.activeecommercecms.Models.Banner;
import com.activeitzone.activeecommercecms.Models.Brand;
import com.activeitzone.activeecommercecms.Models.Category;
import com.activeitzone.activeecommercecms.Models.FlashDeal;
import com.activeitzone.activeecommercecms.Models.Product;
import com.activeitzone.activeecommercecms.Models.SliderImage;
import com.activeitzone.activeecommercecms.Network.response.AppSettingsResponse;
import com.activeitzone.activeecommercecms.Network.response.AuctionBidResponse;
import com.activeitzone.activeecommercecms.Network.response.AuthResponse;
import com.activeitzone.activeecommercecms.Network.response.ProductListingResponse;
import com.activeitzone.activeecommercecms.Network.response.SanitaryProductListingResponse;
import com.activeitzone.activeecommercecms.Presentation.presenters.HomePresenter;
import com.activeitzone.activeecommercecms.Presentation.presenters.ProductListingPresenter;
import com.activeitzone.activeecommercecms.Presentation.presenters.SanitaryProductListingPresenter;
import com.activeitzone.activeecommercecms.Presentation.ui.activities.ProductListingView;
import com.activeitzone.activeecommercecms.Presentation.ui.activities.impl.LoginActivity;
import com.activeitzone.activeecommercecms.Presentation.ui.activities.impl.ProductDetailsActivity;
import com.activeitzone.activeecommercecms.Presentation.ui.activities.impl.ProductListingActivity;
import com.activeitzone.activeecommercecms.Presentation.ui.adapters.AuctionProductAdapter;
import com.activeitzone.activeecommercecms.Presentation.ui.adapters.BestSellingAdapter;
import com.activeitzone.activeecommercecms.Presentation.ui.adapters.BrandAdapter;
import com.activeitzone.activeecommercecms.Presentation.ui.adapters.FeaturedProductAdapter;
import com.activeitzone.activeecommercecms.Presentation.ui.adapters.ProductListingAdapter;
import com.activeitzone.activeecommercecms.Presentation.ui.adapters.SanitaryProductListingAdapter;
import com.activeitzone.activeecommercecms.Presentation.ui.adapters.TodaysDealAdapter;
import com.activeitzone.activeecommercecms.Presentation.ui.adapters.TopCategoryAdapter;
import com.activeitzone.activeecommercecms.Presentation.ui.fragments.HomeView;
import com.activeitzone.activeecommercecms.Presentation.ui.listeners.AuctionClickListener;
import com.activeitzone.activeecommercecms.Presentation.ui.listeners.BrandClickListener;
import com.activeitzone.activeecommercecms.Presentation.ui.listeners.CategoryClickListener;
import com.activeitzone.activeecommercecms.Presentation.ui.listeners.EndlessRecyclerOnScrollListener;
import com.activeitzone.activeecommercecms.Presentation.ui.listeners.ProductClickListener;
import com.activeitzone.activeecommercecms.R;
import com.activeitzone.activeecommercecms.Threading.MainThreadImpl;
import com.activeitzone.activeecommercecms.Utils.AppConfig;
import com.activeitzone.activeecommercecms.Utils.CustomToast;
import com.activeitzone.activeecommercecms.Utils.RecyclerViewMargin;
import com.activeitzone.activeecommercecms.Utils.UserPrefs;
import com.activeitzone.activeecommercecms.domain.executor.impl.ThreadExecutor;
import com.bumptech.glide.Glide;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.JsonObject;
import com.kingfisher.easyviewindicator.AnyViewIndicator;
import com.thekhaeng.recyclerviewmargin.LayoutMarginDecoration;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import cn.iwgang.countdownview.CountdownView;

import static com.facebook.FacebookSdk.getApplicationContext;

public class HomeFragment extends Fragment implements HomeView, CategoryClickListener, BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener, ProductListingView, ProductClickListener, BrandClickListener, AuctionClickListener {
    private View v;
    List<SliderImage> sliderImages;
    private SliderLayout sliderLayout;
    private HomePresenter homePresenter;
    private AnyViewIndicator gridIndicator;
    private RelativeLayout auction_product_section, todays_deal_section, flash_deal_section;
    private AuthResponse authResponse;
    private ProgressDialog progressDialog;
    private BottomSheetDialog dialog;
    private RecyclerView auction_recyclerView,product_list,rv_floor_tiles,rv_epoxyFloorin,rv_sanitary,rv_stone,rv_sand,rv_marble,rv_bricks,rv_granite,rv_3d_epoxy;
    private List<AuctionProduct> mAuctionProducts = new ArrayList<>();
    private  AuctionProductAdapter adapter;
    private TextView flash_deals_text;
    private CountdownView mCvCountdownView;
    private SwipeRefreshLayout swipe_container;



    //tiles start
    String tilesUrl = "https://colorceramics.com/api/v1/products/category/46";
    //private List<Product> mProducts = new ArrayList<>();
    private List<Product> mTitles = new ArrayList<>();
    private List<Product> mSanitary = new ArrayList<>();
    private ProductListingResponse productListingResponse = null;
    private SanitaryProductListingResponse sanitaryproductListingResponse = null;
    private ProductListingPresenter productListingPresenter;
    private SanitaryProductListingPresenter sanitaryProductListingPresenter;
    private ProductListingAdapter adapterP,opoxyFlooringAdapter,tilesAdapter,stoneAdapter,sandAdapter,marbleAdapter,bricksAdapter,graniteAdapter,dEpoxyAdapter;
    private SanitaryProductListingAdapter sanitaryAdapter;
    //tiles end

    String floorTilesUrl = "https://colorceramics.com/api/v1/products/category/48";
    String epoxyFlooringUrl = "https://colorceramics.com/api/v1/products/category/75";
    String sanitaryUrl = "https://colorceramics.com/api/v1/products/category/56";

    String stoneUrl = "https://colorceramics.com/api/v1/products/category/65";
    String sandUrl = "https://colorceramics.com/api/v1/products/category/81";
    String marbleUrl = "https://colorceramics.com/api/v1/products/category/85";
    String bricksUrl = "https://colorceramics.com/api/v1/products/category/69";
    String graniteUrl = "https://colorceramics.com/api/v1/products/category/84";
    String depoxyUrl = "https://colorceramics.com/api/v1/products/category/77";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_home, null);

        //imageSliderShadow = v.findViewById(R.id.imageSliderShadow);
        //gridIndicator = v.findViewById(R.id.anyViewIndicator);
        auction_product_section = v.findViewById(R.id.auction_product_section);
        todays_deal_section = v.findViewById(R.id.todays_deal_section);
        flash_deal_section = v.findViewById(R.id.flash_deal_section);
        flash_deals_text = v.findViewById(R.id.flash_deals_text);
        mCvCountdownView = (CountdownView) v.findViewById(R.id.countdown);

        product_list = v.findViewById(R.id.product_list);
        rv_floor_tiles = v.findViewById(R.id.rv_floor_tiles);
        rv_epoxyFloorin = v.findViewById(R.id.rv_epoxyFloorin);
        rv_sanitary = v.findViewById(R.id.rv_sanitary);
        rv_stone = v.findViewById(R.id.rv_stone);
        rv_sand = v.findViewById(R.id.rv_sand);
        rv_marble = v.findViewById(R.id.rv_marble);
        rv_bricks = v.findViewById(R.id.rv_bricks);
        rv_granite = v.findViewById(R.id.rv_granite);
        rv_3d_epoxy = v.findViewById(R.id.rv_3d_epoxy);


        tilesProducts(tilesUrl);
        sanitary(sanitaryUrl);
        /*epoxyFloorin(epoxyFlooringUrl);
        sanitary(sanitaryUrl);*/

       /* stone(stoneUrl);
        sand(sandUrl);
        marble(marbleUrl);
        bricks(bricksUrl);
        granite(graniteUrl);
        dEpoxy(depoxyUrl);
*/


        swipe_container = v.findViewById(R.id.swipe_container);
        swipe_container.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });

        auction_product_section.setVisibility(View.GONE);
        todays_deal_section.setVisibility(View.GONE);
        flash_deal_section.setVisibility(View.GONE);
        progressDialog = new ProgressDialog(getContext());
        auction_recyclerView = v.findViewById(R.id.auction_products);
        GridLayoutManager horizontalLayoutManager
                = new GridLayoutManager(getActivity(), 1, RecyclerView.VERTICAL, false);
        auction_recyclerView.setLayoutManager(horizontalLayoutManager);
        adapter = new AuctionProductAdapter(getActivity(), mAuctionProducts, this);
        auction_recyclerView.addItemDecoration(new LayoutMarginDecoration( 1,  AppConfig.convertDpToPx(getContext(), 10)));
        auction_recyclerView.setAdapter(adapter);


        sliderLayout = v.findViewById(R.id.imageSlider);
        sliderLayout.setVisibility(View.GONE);
        sliderLayout.stopAutoCycle();

        homePresenter = new HomePresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
        homePresenter.getAppSettings();
        homePresenter.getSliderImages();
        homePresenter.getTopCategories();
        homePresenter.getBanners();

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        //floorTilesProducts(floorTilesUrl);

    }

    @Override
    public void onAppSettingsLoaded(AppSettingsResponse appSettingsResponse) {
        UserPrefs userPrefs = new UserPrefs(getContext());
        userPrefs.setAppSettingsPreferenceObject(appSettingsResponse, "app_settings_response");

        homePresenter.getTodaysDeal();
        homePresenter.getFlashDeal();
        homePresenter.getBestSelling();
        homePresenter.getFeaturedProducts();
        homePresenter.getPopularbrands();
        homePresenter.getAuctionProducts();
    }

    @Override
    public void setSliderImages(List<SliderImage> sliderImages) {
        this.sliderImages = sliderImages;
        for (SliderImage sliderImage : sliderImages) {
            TextSliderView textSliderView = new TextSliderView(getContext());
            textSliderView
                    .description("")
                    .image(AppConfig.ASSET_URL + sliderImage.getPhoto())
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            sliderLayout.addSlider(textSliderView);
            //Log.d("Sliders", AppConfig.ASSET_URL + sliderImage.getPhoto());
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Default);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.addOnPageChangeListener(this);
        sliderLayout.setVisibility(View.VISIBLE);
        //Glide.with(getContext()).load(AppConfig.ASSET_URL + sliderImages.get(0).getPhoto()).transform(new BlurTransformation(75, 1)).into(imageSliderShadow);
    }

    @Override
    public void setHomeCategories(List<Category> categories) {
//        RecyclerView recyclerView = v.findViewById(R.id.home_categories);
//        GridLayoutManager horizontalLayoutManager
//                = new GridLayoutManager(getActivity(), 2, GridLayoutManager.HORIZONTAL, false);
//        recyclerView.setLayoutManager(horizontalLayoutManager);
//        HomeCategoryAdapter adapter = new HomeCategoryAdapter(getActivity(), categories, HomeFragment.this);
//        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setTodaysDeal(List<Product> products) {
        if (products.size() > 0){
            RecyclerView recyclerView = v.findViewById(R.id.todays_deals);
            GridLayoutManager horizontalLayoutManager
                    = new GridLayoutManager(getActivity(), 4, GridLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(horizontalLayoutManager);
            TodaysDealAdapter adapter = new TodaysDealAdapter(getActivity(), products, this);
            recyclerView.addItemDecoration( new LayoutMarginDecoration( 4,  AppConfig.convertDpToPx(getContext(), 10)) );
            recyclerView.setAdapter(adapter);

            todays_deal_section.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setFlashDeal(FlashDeal flashDeal) {
        if (flashDeal.getProducts().getData().size() > 0){
            flash_deals_text.setText(flashDeal.getTitle());

            mCvCountdownView.start((flashDeal.getEndDate()*1000) - System.currentTimeMillis());

            RecyclerView recyclerView = v.findViewById(R.id.flash_deals);
            GridLayoutManager horizontalLayoutManager
                    = new GridLayoutManager(getActivity(), 4, GridLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(horizontalLayoutManager);
            TodaysDealAdapter adapter = new TodaysDealAdapter(getActivity(), flashDeal.getProducts().getData(), this);
            recyclerView.addItemDecoration( new LayoutMarginDecoration( 4,  AppConfig.convertDpToPx(getContext(), 5)) );
            recyclerView.setAdapter(adapter);

            flash_deal_section.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setBanners(List<Banner> banners) {
        ImageView banner_1 = v.findViewById(R.id.banner_1);
        ImageView banner_2 = v.findViewById(R.id.banner_2);
        ImageView banner_3 = v.findViewById(R.id.banner_3);

        Glide.with(getContext()).load(AppConfig.ASSET_URL + banners.get(0).getPhoto()).into(banner_1);
        Glide.with(getContext()).load(AppConfig.ASSET_URL + banners.get(1).getPhoto()).into(banner_2);
        Glide.with(getContext()).load(AppConfig.ASSET_URL + banners.get(2).getPhoto()).into(banner_3);

        banner_1.setOnClickListener(v -> {
            String url = banners.get(0).getUrl();
            if (!url.startsWith("http://") && !url.startsWith("https://"))
                url = "http://" + url;
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
        });

        banner_2.setOnClickListener(v -> {
            String url = banners.get(1).getUrl();
            if (!url.startsWith("http://") && !url.startsWith("https://"))
                url = "http://" + url;
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
        });

        banner_3.setOnClickListener(v -> {
            String url = banners.get(2).getUrl();
            if (!url.startsWith("http://") && !url.startsWith("https://"))
                url = "http://" + url;
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
        });
    }

    @Override
    public void setBestSelling(final List<Product> products) {
        RecyclerView recyclerView = v.findViewById(R.id.best_selling);
        GridLayoutManager horizontalLayoutManager
                = new GridLayoutManager(getActivity(), 1, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        //recyclerView.addItemDecoration(new CirclePagerIndicatorDecoration());
        BestSellingAdapter adapter = new BestSellingAdapter(getActivity(), products, this);
        recyclerView.addItemDecoration( new LayoutMarginDecoration( 1,  AppConfig.convertDpToPx(getContext(), 10)) );
        recyclerView.setAdapter(adapter);

        //gridIndicator.setItemCount((int) Math.ceil((float)products.size() / (float)3));
        //gridIndicator.setCurrentPosition(0);
        //LinearSnapHelper gridLayoutSnapHelper = new LinearSnapHelper();
        //gridLayoutSnapHelper.attachToRecyclerView(recyclerView);

//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                switch (newState) {
//                    case RecyclerView.SCROLL_STATE_IDLE:
//                        int position = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
//                        gridIndicator.setCurrentPosition((int) Math.ceil((position) / 3));
//                        break;
//                }
//            }
//        });
    }

    @Override
    public void setFeaturedProducts(List<Product> products) {
        Log.d("-----product----",products.toString());
        RecyclerView recyclerView = v.findViewById(R.id.featured_products);
        GridLayoutManager horizontalLayoutManager
                = new GridLayoutManager(getActivity(), 4, GridLayoutManager.VERTICAL, true);
        recyclerView.setLayoutManager(horizontalLayoutManager);

        FeaturedProductAdapter adapter = new FeaturedProductAdapter(getActivity(), products, this);
        recyclerView.addItemDecoration( new LayoutMarginDecoration( 5,  AppConfig.convertDpToPx(getContext(), 10)) );
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setTopCategories(List<Category> categories) {
        RecyclerView recyclerView = v.findViewById(R.id.top_categories);
        GridLayoutManager horizontalLayoutManager
                = new GridLayoutManager(getActivity(), 5, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        TopCategoryAdapter adapter = new TopCategoryAdapter(getActivity(), categories, HomeFragment.this);
        recyclerView.addItemDecoration( new LayoutMarginDecoration( 5,  AppConfig.convertDpToPx(getContext(), 10)) );
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setPopularBrands(List<Brand> brands) {
        RecyclerView recyclerView = v.findViewById(R.id.popular_brads);
        GridLayoutManager horizontalLayoutManager
                = new GridLayoutManager(getActivity(), 2, GridLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        BrandAdapter adapter = new BrandAdapter(getActivity(), brands, this);
        recyclerView.addItemDecoration( new LayoutMarginDecoration( 2,  AppConfig.convertDpToPx(getContext(), 10)) );
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setAuctionProducts(List<AuctionProduct> auctionProducts) {
        mAuctionProducts.clear();
        mAuctionProducts.addAll(auctionProducts);
        if (auctionProducts.size() > 0){
            auction_product_section.setVisibility(View.VISIBLE);
            adapter.notifyDataSetChanged();
        }
        else {
            auction_product_section.setVisibility(View.GONE);
        }
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //Log.d("SliderPosition", String.valueOf(position));
    }

    @Override
    public void onPageSelected(int position) {
        Log.d("SliderPosition", String.valueOf(position));
        //Glide.with(getContext()).load(AppConfig.ASSET_URL + sliderImages.get(position).getPhoto()).transform(new BlurTransformation(75, 1)).into(imageSliderShadow);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //Log.d("SliderPosition", String.valueOf(state));
    }

    @Override
    public void onCategoryItemClick(Category category) {
        Intent intent = new Intent(getContext(), ProductListingActivity.class);
        intent.putExtra("title", category.getName());
        intent.putExtra("url", category.getLinks().getProducts());
        startActivity(intent);
    }

    @Override
    public void onProductItemClick(Product product) {
        Intent intent = new Intent(getContext(), ProductDetailsActivity.class);
        intent.putExtra("product_name", product.getName());
        intent.putExtra("link", product.getLinks().getDetails());
        intent.putExtra("top_selling", product.getLinks().getRelated());
        startActivity(intent);
    }

    @Override
    public void onBrandItemClick(Brand brand) {
        Intent intent = new Intent(getContext(), ProductListingActivity.class);
        intent.putExtra("title", brand.getName());
        intent.putExtra("url", brand.getLinks().getProducts());
        startActivity(intent);
    }

    @Override
    public void onAuctionItemClick(AuctionProduct auctionProduct) {
        View view = getLayoutInflater().inflate(R.layout.fragment_bottom_sheet_dialog_auction, null);
        ImageView image = view.findViewById(R.id.product_image);
        TextView current_bid_amount = view.findViewById(R.id.current_bid_amount);
        TextView total_bids = view.findViewById(R.id.total_bids);
        TextView name = view.findViewById(R.id.product_name);
        EditText bid_amount = view.findViewById(R.id.bid_amount);
        Button place_bid = view.findViewById(R.id.place_bid);

        Glide.with(getContext()).load(AppConfig.ASSET_URL + auctionProduct.getImage()).into(image);
        current_bid_amount.setText(AppConfig.convertPrice(getContext(), auctionProduct.getCurrentPrice()));
        total_bids.setText(auctionProduct.getBidsCount()+" Bids");
        name.setText(auctionProduct.getName());


        dialog = new BottomSheetDialog(getContext());
        dialog.setContentView(view);
        dialog.show();

        place_bid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double amount = (bid_amount.getText().length() > 0) ? Double.valueOf(bid_amount.getText().toString()) : 0.0;
                if (amount > auctionProduct.getCurrentPrice()){
                    authResponse = new UserPrefs(getActivity()).getAuthPreferenceObjectJson("auth_response");
                    if(authResponse != null && authResponse.getUser() != null){
                        JsonObject jsonObject = new JsonObject();
                        jsonObject.addProperty("auction_product_id", auctionProduct.getId());
                        jsonObject.addProperty("user_id", authResponse.getUser().getId());
                        jsonObject.addProperty("amount", amount);

                        dialog.hide();
                        progressDialog.setMessage("Your bid is being submitted. Please wait.");
                        progressDialog.show();
                        homePresenter.submitBid(jsonObject, authResponse.getAccessToken());
                    }
                    else {
                        startActivityForResult(new Intent(getActivity(), LoginActivity.class), 100);
                    }
                }
                else {
                    CustomToast.showToast(getActivity(), "Your bidding amount must be greater than the current bid", R.color.colorWarning);
                    bid_amount.requestFocus();
                }
            }
        });
    }

    @Override
    public void onAuctionBidSubmitted(AuctionBidResponse auctionBidResponse) {
        progressDialog.dismiss();
        CustomToast.showToast(getActivity(), auctionBidResponse.getMessage(), R.color.colorSuccess);
        homePresenter.getAuctionProducts();
    }

    @Override
    public void setProducts(ProductListingResponse productListingResponse) {
        /*mProducts.addAll(productListingResponse.getData());
        this.productListingResponse = productListingResponse;
        //progressBar.setVisibility(View.GONE);
        adapterP.notifyDataSetChanged();*/

        /*opoxyFlooringAdapter.notifyDataSetChanged();

        stoneAdapter.notifyDataSetChanged();
        sandAdapter.notifyDataSetChanged();
        marbleAdapter.notifyDataSetChanged();
        bricksAdapter.notifyDataSetChanged();
        graniteAdapter.notifyDataSetChanged();
        dEpoxyAdapter.notifyDataSetChanged();*/


        mTitles.addAll(productListingResponse.getData());
        this.productListingResponse = productListingResponse;
        tilesAdapter.notifyDataSetChanged();



       /* if (mProducts.size() <= 0){
            //products_empty_text.setVisibility(View.VISIBLE);
        }*/


    }
    @Override
    public void setTiles(ProductListingResponse productListingResponse) {
        mTitles.addAll(productListingResponse.getData());
        tilesAdapter.notifyDataSetChanged();
    }
    @Override
    public void setSanitary(SanitaryProductListingResponse productListingResponse) {
        mSanitary.addAll(productListingResponse.getData());
        sanitaryAdapter.notifyDataSetChanged();
    }







    /*

    *//**
     tiles product show from here
     */

    public void tilesProducts(String url){

        //url  https://colorceramics.com/api/v1/products/category/46


        tilesAdapter = new ProductListingAdapter(getApplicationContext(), mTitles, this);

        GridLayoutManager horizontalLayoutManager
                = new GridLayoutManager(getApplicationContext(), 4);
        product_list.setLayoutManager(horizontalLayoutManager);
        //adapter.setClickListener(this);
        RecyclerViewMargin decoration = new RecyclerViewMargin(convertDpToPx(getApplicationContext(),5), 4);
        product_list.addItemDecoration(decoration);
        product_list.setAdapter(tilesAdapter);

        product_list.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                addDataToList(productListingResponse);
            }
        });

        productListingPresenter = new ProductListingPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
        productListingPresenter.getProducts(url);
    }
    public int convertDpToPx(Context context, float dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }
    public void addDataToList(ProductListingResponse productListingResponse){
        if (productListingResponse != null && productListingResponse.getMeta() != null && !productListingResponse.getMeta().getCurrentPage().equals(productListingResponse.getMeta().getLastPage())){
            productListingPresenter.getProducts(productListingResponse.getLinks().getNext().toString());
        }
    }

    /*

    *//**
     tiles product showend from here
     *//*


    *//**
     floorTiles product show from here
     *//*
    public void floorTilesProducts(String url){

        //url  https://colorceramics.com/api/v1/products/category/46


        adapterP = new ProductListingAdapter(getApplicationContext(), mProducts, this);
        //recyclerView = v.findViewById(R.id.product_list);


        GridLayoutManager horizontalLayoutManager
                = new GridLayoutManager(getApplicationContext(), 4);
        rv_floor_tiles.setLayoutManager(horizontalLayoutManager);
        //adapter.setClickListener(this);
        RecyclerViewMargin decoration = new RecyclerViewMargin(convertDpToPx(getApplicationContext(),5), 4);
        rv_floor_tiles.addItemDecoration(decoration);
        rv_floor_tiles.setAdapter(adapterP);

        rv_floor_tiles.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                addDataToList(productListingResponse);
            }
        });

        productListingPresenter = new ProductListingPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
        productListingPresenter.getProducts(url);
    }
    *//**
     floorTiles product showend from here
     *//*

    *//**
     epoxyFloorin product show from here
     *//*
    public void epoxyFloorin(String url){

        //url  https://colorceramics.com/api/v1/products/category/46


        opoxyFlooringAdapter = new ProductListingAdapter(getApplicationContext(), mProducts, this);
        //recyclerView = v.findViewById(R.id.product_list);


        GridLayoutManager horizontalLayoutManager
                = new GridLayoutManager(getApplicationContext(), 4);
        rv_epoxyFloorin.setLayoutManager(horizontalLayoutManager);
        //adapter.setClickListener(this);
        RecyclerViewMargin decoration = new RecyclerViewMargin(convertDpToPx(getApplicationContext(),5), 4);
        rv_epoxyFloorin.addItemDecoration(decoration);
        rv_epoxyFloorin.setAdapter(opoxyFlooringAdapter);

        rv_epoxyFloorin.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                addDataToList(productListingResponse);
            }
        });

        productListingPresenter = new ProductListingPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
        productListingPresenter.getProducts(url);
    }
    *//**
     epoxyFloorin product showend from here
     *//*



    */




    /**
     sanitary product show from here
     */
    public void sanitary(String url){

        //url  https://colorceramics.com/api/v1/products/category/46


        sanitaryAdapter = new SanitaryProductListingAdapter(getApplicationContext(), mSanitary, this);
        //recyclerView = v.findViewById(R.id.product_list);


        GridLayoutManager horizontalLayoutManager
                = new GridLayoutManager(getApplicationContext(), 4);
        rv_sanitary.setLayoutManager(horizontalLayoutManager);
        //adapter.setClickListener(this);
        RecyclerViewMargin decoration = new RecyclerViewMargin(sanitaryConvertDpToPx(getApplicationContext(),5), 4);
        rv_sanitary.addItemDecoration(decoration);
        rv_sanitary.setAdapter(sanitaryAdapter);

        rv_sanitary.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                sanitaryAddDataToList(sanitaryproductListingResponse);
            }
        });

        sanitaryProductListingPresenter = new SanitaryProductListingPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
        //sanitaryProductListingPresenter.getProducts(url);
        sanitaryProductListingPresenter.getSanitary(url);



    }
    public int sanitaryConvertDpToPx(Context context, float dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }
    public void sanitaryAddDataToList(SanitaryProductListingResponse productListingResponse){
        if (productListingResponse != null && productListingResponse.getMeta() != null && !productListingResponse.getMeta().getCurrentPage().equals(productListingResponse.getMeta().getLastPage())){
            sanitaryProductListingPresenter.getSanitary(productListingResponse.getLinks().getNext().toString());
        }
    }
    /**
     sanitary product showend from here
     */



    /*


    *//**
     stone product show from here
     *//*
    public void stone(String url){

        //url  https://colorceramics.com/api/v1/products/category/46


        stoneAdapter = new ProductListingAdapter(getApplicationContext(), mProducts, this);
        //recyclerView = v.findViewById(R.id.product_list);


        GridLayoutManager horizontalLayoutManager
                = new GridLayoutManager(getApplicationContext(), 4);
        rv_stone.setLayoutManager(horizontalLayoutManager);
        //adapter.setClickListener(this);
        RecyclerViewMargin decoration = new RecyclerViewMargin(convertDpToPx(getApplicationContext(),5), 4);
        rv_stone.addItemDecoration(decoration);
        rv_stone.setAdapter(opoxyFlooringAdapter);

        rv_stone.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                addDataToList(productListingResponse);
            }
        });

        productListingPresenter = new ProductListingPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
        productListingPresenter.getProducts(url);
    }
    *//**
     stone product showend from here
     *//*

    *//**
     sand product show from here
     *//*
    public void sand(String url){

        //url  https://colorceramics.com/api/v1/products/category/46


        sandAdapter = new ProductListingAdapter(getApplicationContext(), mProducts, this);
        //recyclerView = v.findViewById(R.id.product_list);


        GridLayoutManager horizontalLayoutManager
                = new GridLayoutManager(getApplicationContext(), 4);
        rv_sand.setLayoutManager(horizontalLayoutManager);
        //adapter.setClickListener(this);
        RecyclerViewMargin decoration = new RecyclerViewMargin(convertDpToPx(getApplicationContext(),5), 4);
        rv_sand.addItemDecoration(decoration);
        rv_sand.setAdapter(opoxyFlooringAdapter);

        rv_sand.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                addDataToList(productListingResponse);
            }
        });

        productListingPresenter = new ProductListingPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
        productListingPresenter.getProducts(url);
    }
    *//**
     sand product showend from here
     *//*

    *//**
     marble product show from here
     *//*
    public void marble(String url){

        //url  https://colorceramics.com/api/v1/products/category/46


        marbleAdapter = new ProductListingAdapter(getApplicationContext(), mProducts, this);
        //recyclerView = v.findViewById(R.id.product_list);


        GridLayoutManager horizontalLayoutManager
                = new GridLayoutManager(getApplicationContext(), 4);
        rv_marble.setLayoutManager(horizontalLayoutManager);
        //adapter.setClickListener(this);
        RecyclerViewMargin decoration = new RecyclerViewMargin(convertDpToPx(getApplicationContext(),5), 4);
        rv_marble.addItemDecoration(decoration);
        rv_marble.setAdapter(opoxyFlooringAdapter);

        rv_marble.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                addDataToList(productListingResponse);
            }
        });

        productListingPresenter = new ProductListingPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
        productListingPresenter.getProducts(url);
    }
    *//**
     marble product showend from here
     *//*

    *//**
     bricks product show from here
     *//*
    public void bricks(String url){

        //url  https://colorceramics.com/api/v1/products/category/46


        bricksAdapter = new ProductListingAdapter(getApplicationContext(), mProducts, this);
        //recyclerView = v.findViewById(R.id.product_list);


        GridLayoutManager horizontalLayoutManager
                = new GridLayoutManager(getApplicationContext(), 4);
        rv_bricks.setLayoutManager(horizontalLayoutManager);
        //adapter.setClickListener(this);
        RecyclerViewMargin decoration = new RecyclerViewMargin(convertDpToPx(getApplicationContext(),5), 4);
        rv_bricks.addItemDecoration(decoration);
        rv_bricks.setAdapter(opoxyFlooringAdapter);

        rv_bricks.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                addDataToList(productListingResponse);
            }
        });

        productListingPresenter = new ProductListingPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
        productListingPresenter.getProducts(url);
    }
    *//**
     bricks product showend from here
     *//*

    *//**
     granite product show from here
     *//*
    public void granite(String url){

        //url  https://colorceramics.com/api/v1/products/category/46


        graniteAdapter = new ProductListingAdapter(getApplicationContext(), mProducts, this);
        //recyclerView = v.findViewById(R.id.product_list);


        GridLayoutManager horizontalLayoutManager
                = new GridLayoutManager(getApplicationContext(), 4);
        rv_granite.setLayoutManager(horizontalLayoutManager);
        //adapter.setClickListener(this);
        RecyclerViewMargin decoration = new RecyclerViewMargin(convertDpToPx(getApplicationContext(),5), 4);
        rv_granite.addItemDecoration(decoration);
        rv_granite.setAdapter(opoxyFlooringAdapter);

        rv_granite.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                addDataToList(productListingResponse);
            }
        });

        productListingPresenter = new ProductListingPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
        productListingPresenter.getProducts(url);
    }
    *//**
     granite product showend from here
     *//*

    *//**
     3dEpoxy product show from here
     *//*
    public void dEpoxy(String url){

        //url  https://colorceramics.com/api/v1/products/category/46


        dEpoxyAdapter = new ProductListingAdapter(getApplicationContext(), mProducts, this);
        //recyclerView = v.findViewById(R.id.product_list);


        GridLayoutManager horizontalLayoutManager
                = new GridLayoutManager(getApplicationContext(), 4);
        rv_3d_epoxy.setLayoutManager(horizontalLayoutManager);
        //adapter.setClickListener(this);
        RecyclerViewMargin decoration = new RecyclerViewMargin(convertDpToPx(getApplicationContext(),5), 4);
        rv_3d_epoxy.addItemDecoration(decoration);
        rv_3d_epoxy.setAdapter(opoxyFlooringAdapter);

        rv_3d_epoxy.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                addDataToList(productListingResponse);
            }
        });

        productListingPresenter = new ProductListingPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
        productListingPresenter.getProducts(url);
    }
    *//**
     3dEpoxy product showend from here
     *//*

*/

}