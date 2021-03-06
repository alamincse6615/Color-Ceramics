package com.colorceramics.soft_it_care.Presentation.ui.fragments.impl;

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

import com.colorceramics.soft_it_care.Models.AuctionProduct;
import com.colorceramics.soft_it_care.Models.Banner;
import com.colorceramics.soft_it_care.Models.Brand;
import com.colorceramics.soft_it_care.Models.Category;
import com.colorceramics.soft_it_care.Models.FlashDeal;
import com.colorceramics.soft_it_care.Models.Product;
import com.colorceramics.soft_it_care.Models.SliderImage;
import com.colorceramics.soft_it_care.Network.response.AppSettingsResponse;
import com.colorceramics.soft_it_care.Network.response.AuctionBidResponse;
import com.colorceramics.soft_it_care.Network.response.AuthResponse;
import com.colorceramics.soft_it_care.Network.response.BricksProductListingResponse;
import com.colorceramics.soft_it_care.Network.response.DEpoxyProductListingResponse;
import com.colorceramics.soft_it_care.Network.response.FloorTilesProductListingResponse;
import com.colorceramics.soft_it_care.Network.response.GraniteProductListingResponse;
import com.colorceramics.soft_it_care.Network.response.MarbleProductListingResponse;
import com.colorceramics.soft_it_care.Network.response.ProductListingResponse;
import com.colorceramics.soft_it_care.Network.response.SandProductListingResponse;
import com.colorceramics.soft_it_care.Network.response.SanitaryProductListingResponse;
import com.colorceramics.soft_it_care.Network.response.StoneProductListingResponse;
import com.colorceramics.soft_it_care.Network.response.TilesProductListingResponse;
import com.colorceramics.soft_it_care.Presentation.presenters.BricksProductListingPresenter;
import com.colorceramics.soft_it_care.Presentation.presenters.DEpoxyProductListingPresenter;
import com.colorceramics.soft_it_care.Presentation.presenters.FloorTilesProductListingPresenter;
import com.colorceramics.soft_it_care.Presentation.presenters.GraniteProductListingPresenter;
import com.colorceramics.soft_it_care.Presentation.presenters.HomePresenter;
import com.colorceramics.soft_it_care.Presentation.presenters.MarbleProductListingPresenter;
import com.colorceramics.soft_it_care.Presentation.presenters.ProductListingPresenter;
import com.colorceramics.soft_it_care.Presentation.presenters.SandProductListingPresenter;
import com.colorceramics.soft_it_care.Presentation.presenters.SanitaryProductListingPresenter;
import com.colorceramics.soft_it_care.Presentation.presenters.StoneProductListingPresenter;
import com.colorceramics.soft_it_care.Presentation.presenters.TilesProductListingPresenter;
import com.colorceramics.soft_it_care.Presentation.ui.activities.ProductListingView;
import com.colorceramics.soft_it_care.Presentation.ui.activities.impl.LoginActivity;
import com.colorceramics.soft_it_care.Presentation.ui.activities.impl.ProductDetailsActivity;
import com.colorceramics.soft_it_care.Presentation.ui.activities.impl.ProductListingActivity;
import com.colorceramics.soft_it_care.Presentation.ui.adapters.AuctionProductAdapter;
import com.colorceramics.soft_it_care.Presentation.ui.adapters.BestSellingAdapter;
import com.colorceramics.soft_it_care.Presentation.ui.adapters.BrandAdapter;
import com.colorceramics.soft_it_care.Presentation.ui.adapters.BricksProductListingAdapter;
import com.colorceramics.soft_it_care.Presentation.ui.adapters.DEpoxyProductListingAdapter;
import com.colorceramics.soft_it_care.Presentation.ui.adapters.FeaturedProductAdapter;
import com.colorceramics.soft_it_care.Presentation.ui.adapters.FloorTilesProductListingAdapter;
import com.colorceramics.soft_it_care.Presentation.ui.adapters.GraniteProductListingAdapter;
import com.colorceramics.soft_it_care.Presentation.ui.adapters.MarbleProductListingAdapter;
import com.colorceramics.soft_it_care.Presentation.ui.adapters.ProductListingAdapter;
import com.colorceramics.soft_it_care.Presentation.ui.adapters.SandProductListingAdapter;
import com.colorceramics.soft_it_care.Presentation.ui.adapters.SanitaryProductListingAdapter;
import com.colorceramics.soft_it_care.Presentation.ui.adapters.StoneProductListingAdapter;
import com.colorceramics.soft_it_care.Presentation.ui.adapters.TilesProductListingAdapter;
import com.colorceramics.soft_it_care.Presentation.ui.adapters.TodaysDealAdapter;
import com.colorceramics.soft_it_care.Presentation.ui.adapters.TopCategoryAdapter;
import com.colorceramics.soft_it_care.Presentation.ui.fragments.HomeView;
import com.colorceramics.soft_it_care.Presentation.ui.listeners.AuctionClickListener;
import com.colorceramics.soft_it_care.Presentation.ui.listeners.BrandClickListener;
import com.colorceramics.soft_it_care.Presentation.ui.listeners.CategoryClickListener;
import com.colorceramics.soft_it_care.Presentation.ui.listeners.EndlessRecyclerOnScrollListener;
import com.colorceramics.soft_it_care.Presentation.ui.listeners.ProductClickListener;
import com.colorceramics.soft_it_care.R;
import com.colorceramics.soft_it_care.Threading.MainThreadImpl;
import com.colorceramics.soft_it_care.Utils.AppConfig;
import com.colorceramics.soft_it_care.Utils.CustomToast;
import com.colorceramics.soft_it_care.Utils.RecyclerViewMargin;
import com.colorceramics.soft_it_care.Utils.UserPrefs;
import com.colorceramics.soft_it_care.domain.executor.impl.ThreadExecutor;
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
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
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
    private RelativeLayout auction_product_section, todays_deal_section, flash_deal_section,rl_sand;
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

    //private List<Product> mProducts = new ArrayList<>();
    private List<Product> mTitles = new ArrayList<>();
    private List<Product> mSanitary = new ArrayList<>();
    private List<Product> mFloorTiles = new ArrayList<>();
    private List<Product> mDEpoxy = new ArrayList<>();
    private List<Product> mStone = new ArrayList<>();
    private List<Product> mSand = new ArrayList<>();
    private List<Product> mMarble = new ArrayList<>();
    private List<Product> mBricks = new ArrayList<>();
    private List<Product> mGranite = new ArrayList<>();

    private ProductListingResponse productListingResponse = null;
    private SanitaryProductListingResponse sanitaryproductListingResponse = null;
    private TilesProductListingResponse tilesproductListingResponse = null;
    private FloorTilesProductListingResponse floorTilesproductListingResponse = null;
    private DEpoxyProductListingResponse dEpoxyProductListingResponse = null;
    private StoneProductListingResponse stoneProductListingResponse = null;
    private SandProductListingResponse sandProductListingResponse = null;
    private MarbleProductListingResponse marbleProductListingResponse = null;
    private BricksProductListingResponse bricksProductListingResponse = null;
    private GraniteProductListingResponse graniteProductListingResponse = null;


    private ProductListingPresenter productListingPresenter;
    private TilesProductListingPresenter tilesproductListingPresenter;
    private SanitaryProductListingPresenter sanitaryProductListingPresenter;
    private FloorTilesProductListingPresenter floorTilesproductListingPresenter;
    private DEpoxyProductListingPresenter dEpoxyProductListingPresenter;
    private StoneProductListingPresenter stoneProductListingPresenter;
    private SandProductListingPresenter sandProductListingPresenter;
    private MarbleProductListingPresenter marbleProductListingPresenter;
    private BricksProductListingPresenter bricksProductListingPresenter;
    private GraniteProductListingPresenter graniteProductListingPresenter;

    private ProductListingAdapter adapterP;
    private SanitaryProductListingAdapter sanitaryAdapter;
    private TilesProductListingAdapter tilesAdapter;
    private FloorTilesProductListingAdapter floorTilesAdapter;
    private DEpoxyProductListingAdapter dEPoxyAdapter;
    private StoneProductListingAdapter stoneAdapter;
    private SandProductListingAdapter sandAdapter;
    private MarbleProductListingAdapter marbleAdapter;
    private GraniteProductListingAdapter graniteAdapter;
    private BricksProductListingAdapter bricksAdapter;
    //tiles end

    String tilesUrl = "https://colorceramics.com/api/v1/products/category/46";
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

        rl_sand = v.findViewById(R.id.rl_sand);

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
        floorTilesProducts(floorTilesUrl);
        epoxyFloorin(epoxyFlooringUrl);
        stone(stoneUrl);
        sand(sandUrl);
        marble(marbleUrl);
        granite(graniteUrl);
        bricks(bricksUrl);


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

        RecyclerView recyclerView = v.findViewById(R.id.featured_products);
        GridLayoutManager horizontalLayoutManager
                = new GridLayoutManager(getActivity(), 4, GridLayoutManager.VERTICAL, true);
        recyclerView.setLayoutManager(horizontalLayoutManager);

        FeaturedProductAdapter adapter = new FeaturedProductAdapter(getActivity(), products, this);
        recyclerView.addItemDecoration( new LayoutMarginDecoration( 5,  AppConfig.convertDpToPx(getContext(), 5)) );
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setTopCategories(List<Category> categories) {
        RecyclerView recyclerView = v.findViewById(R.id.top_categories);
        GridLayoutManager horizontalLayoutManager
                = new GridLayoutManager(getActivity(), 5, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        TopCategoryAdapter adapter = new TopCategoryAdapter(getActivity(), categories, HomeFragment.this);
        recyclerView.addItemDecoration( new LayoutMarginDecoration( 5,  AppConfig.convertDpToPx(getContext(), 5)) );
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setPopularBrands(List<Brand> brands) {
        RecyclerView recyclerView = v.findViewById(R.id.popular_brads);
        GridLayoutManager horizontalLayoutManager
                = new GridLayoutManager(getActivity(), 2, GridLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        BrandAdapter adapter = new BrandAdapter(getActivity(), brands, this);
        recyclerView.addItemDecoration( new LayoutMarginDecoration( 2,  AppConfig.convertDpToPx(getContext(), 5)) );
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
    public void setTiles(TilesProductListingResponse tilesproductListingResponse) {
        mTitles.addAll(tilesproductListingResponse.getData());
        tilesAdapter.notifyDataSetChanged();
    }

    @Override
    public void setSanitary(SanitaryProductListingResponse productListingResponse) {
        mSanitary.addAll(productListingResponse.getData());
        sanitaryAdapter.notifyDataSetChanged();
    }

    @Override
    public void setFloorTiles(FloorTilesProductListingResponse floorTilesProductListingResponse) {
        mFloorTiles.addAll(floorTilesProductListingResponse.getData());
        floorTilesAdapter.notifyDataSetChanged();
    }

    @Override
    public void setDEpoxy(DEpoxyProductListingResponse dEpoxyProductListingResponse) {
        mDEpoxy.addAll(dEpoxyProductListingResponse.getData());
        dEPoxyAdapter.notifyDataSetChanged();
    }

    @Override
    public void setStone(StoneProductListingResponse stoneProductListingResponse) {
        mStone.addAll(stoneProductListingResponse.getData());
        stoneAdapter.notifyDataSetChanged();
    }

    @Override
    public void setSand(SandProductListingResponse sandProductListingResponse) {
        mSand.addAll(sandProductListingResponse.getData());
        sandAdapter.notifyDataSetChanged();

        if (mSand.size()==0){
            rl_sand.setVisibility(View.GONE);
        }
    }

    @Override
    public void setMarble(MarbleProductListingResponse marbleProductListingResponse) {
        mMarble.addAll(marbleProductListingResponse.getData());
        marbleAdapter.notifyDataSetChanged();
    }

    @Override
    public void setGranite(GraniteProductListingResponse graniteProductListingResponse) {
        mGranite.addAll(graniteProductListingResponse.getData());
        graniteAdapter.notifyDataSetChanged();
    }

    @Override
    public void setBricks(BricksProductListingResponse bricksProductListingResponse) {
        mBricks.addAll(bricksProductListingResponse.getData());
        bricksAdapter.notifyDataSetChanged();
    }

    /**
     tiles product show from here
     */
    public void tilesProducts(String url){
        tilesAdapter = new TilesProductListingAdapter(getApplicationContext(), mTitles, this);
        GridLayoutManager horizontalLayoutManager
                = new GridLayoutManager(getApplicationContext(), 4);
        product_list.setLayoutManager(horizontalLayoutManager);
        RecyclerViewMargin decoration = new RecyclerViewMargin(tilesconvertDpToPx(getApplicationContext(),5), 4);
        product_list.addItemDecoration(decoration);
        product_list.setAdapter(tilesAdapter);

        product_list.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                tilesaddDataToList(tilesproductListingResponse);
            }
        });
        tilesproductListingPresenter = new TilesProductListingPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
        tilesproductListingPresenter.getTiles(url);
    }
    public int tilesconvertDpToPx(Context context, float dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }
    public void tilesaddDataToList(TilesProductListingResponse tilesproductListingResponse){
        if (tilesproductListingResponse != null && tilesproductListingResponse.getMeta() != null && !tilesproductListingResponse.getMeta().getCurrentPage().equals(tilesproductListingResponse.getMeta().getLastPage())){
            tilesproductListingPresenter.getTiles(tilesproductListingResponse.getLinks().getNext().toString());
        }
    }


    public int convertDpToPx(Context context, float dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }
    public void addDataToList(ProductListingResponse productListingResponse){
        if (productListingResponse != null && productListingResponse.getMeta() != null && !productListingResponse.getMeta().getCurrentPage().equals(productListingResponse.getMeta().getLastPage())){
            productListingPresenter.getProducts(productListingResponse.getLinks().getNext().toString());
        }
    }


    public void floorTilesProducts(String f_url){

        floorTilesAdapter = new FloorTilesProductListingAdapter(getApplicationContext(), mFloorTiles, this);

        GridLayoutManager horizontalLayoutManager
                = new GridLayoutManager(getApplicationContext(), 4);
        rv_floor_tiles.setLayoutManager(horizontalLayoutManager);
        RecyclerViewMargin decoration = new RecyclerViewMargin(floorTilesconvertDpToPx(getApplicationContext(),5), 4);
        rv_floor_tiles.addItemDecoration(decoration);
        rv_floor_tiles.setAdapter(floorTilesAdapter);

        rv_floor_tiles.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                floorTilesaddDataToList(floorTilesproductListingResponse);
            }
        });

        floorTilesproductListingPresenter = new FloorTilesProductListingPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
        floorTilesproductListingPresenter.getFloorTiles(f_url);
    }

    public int floorTilesconvertDpToPx(Context context, float dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }
    public void floorTilesaddDataToList(FloorTilesProductListingResponse floorTilesproductListingResponse){
        if (floorTilesproductListingResponse != null && floorTilesproductListingResponse.getMeta() != null && !floorTilesproductListingResponse.getMeta().getCurrentPage().equals(floorTilesproductListingResponse.getMeta().getLastPage())){
            floorTilesproductListingPresenter.getFloorTiles(floorTilesproductListingResponse.getLinks().getNext().toString());
        }
    }


    public void epoxyFloorin(String url){

        dEPoxyAdapter = new DEpoxyProductListingAdapter(getApplicationContext(), mDEpoxy, this);
        GridLayoutManager horizontalLayoutManager
                = new GridLayoutManager(getApplicationContext(), 4);
        rv_epoxyFloorin.setLayoutManager(horizontalLayoutManager);
        RecyclerViewMargin decoration = new RecyclerViewMargin(dEpoxyConvertDpToPx(getApplicationContext(),5), 4);
        rv_epoxyFloorin.addItemDecoration(decoration);
        rv_epoxyFloorin.setAdapter(dEPoxyAdapter);

        rv_epoxyFloorin.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                dEpoxyAddDataToList(dEpoxyProductListingResponse);
            }
        });

        dEpoxyProductListingPresenter = new DEpoxyProductListingPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
        dEpoxyProductListingPresenter.getDEpoxy(url);
    }
    public int dEpoxyConvertDpToPx(Context context, float dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }
    public void dEpoxyAddDataToList(DEpoxyProductListingResponse dEpoxyProductListingResponse){
        if (dEpoxyProductListingResponse != null && dEpoxyProductListingResponse.getMeta() != null && !dEpoxyProductListingResponse.getMeta().getCurrentPage().equals(dEpoxyProductListingResponse.getMeta().getLastPage())){
            dEpoxyProductListingPresenter.getDEpoxy(dEpoxyProductListingResponse.getLinks().getNext().toString());
        }
    }




    public void stone(String url){

        stoneAdapter = new StoneProductListingAdapter(getApplicationContext(), mStone, this);
        GridLayoutManager horizontalLayoutManager
                = new GridLayoutManager(getApplicationContext(), 4);
        rv_stone.setLayoutManager(horizontalLayoutManager);
        //RecyclerViewMargin decoration = new RecyclerViewMargin(dEpoxyConvertDpToPx(getApplicationContext(),5), 4);
        RecyclerViewMargin decoration = new RecyclerViewMargin(StoneConvertDpToPx(getApplicationContext(),5), 4);
        rv_stone.addItemDecoration(decoration);
        rv_stone.setAdapter(stoneAdapter);

        rv_stone.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                StoneAddDataToList(stoneProductListingResponse);
            }
        });

        stoneProductListingPresenter = new StoneProductListingPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
        stoneProductListingPresenter.getStone(url);
    }

    public int StoneConvertDpToPx(Context context, float dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }
    public void StoneAddDataToList(StoneProductListingResponse stoneProductListingResponse){
        if (stoneProductListingResponse != null && stoneProductListingResponse.getMeta() != null && !stoneProductListingResponse.getMeta().getCurrentPage().equals(stoneProductListingResponse.getMeta().getLastPage())){
            stoneProductListingPresenter.getStone(stoneProductListingResponse.getLinks().getNext().toString());
        }
    }


    public void sand(String r_url){

        sandAdapter = new SandProductListingAdapter(getApplicationContext(), mSand, this);
        GridLayoutManager horizontalLayoutManager
                = new GridLayoutManager(getApplicationContext(), 4);
        rv_sand.setLayoutManager(horizontalLayoutManager);
        RecyclerViewMargin decoration = new RecyclerViewMargin(SandConvertDpToPx(getApplicationContext(),5), 4);
        rv_sand.addItemDecoration(decoration);
        rv_sand.setAdapter(sandAdapter);

        rv_sand.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                SandAddDataToList(sandProductListingResponse);
            }
        });

        sandProductListingPresenter = new SandProductListingPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
        sandProductListingPresenter.getSand(r_url);
    }
    public int SandConvertDpToPx(Context context, float dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }
    public void SandAddDataToList(SandProductListingResponse sandProductListingResponse){
        if (sandProductListingResponse != null && sandProductListingResponse.getMeta() != null && !sandProductListingResponse.getMeta().getCurrentPage().equals(sandProductListingResponse.getMeta().getLastPage())){
            sandProductListingPresenter.getSand(sandProductListingResponse.getLinks().getNext().toString());
        }
    }


    public void marble(String url){

        marbleAdapter = new MarbleProductListingAdapter(getApplicationContext(), mMarble, this);

        GridLayoutManager horizontalLayoutManager
                = new GridLayoutManager(getApplicationContext(), 4);
        rv_marble.setLayoutManager(horizontalLayoutManager);
        //adapter.setClickListener(this);
        RecyclerViewMargin decoration = new RecyclerViewMargin(MarbleConvertDpToPx(getApplicationContext(),5), 4);
        rv_marble.addItemDecoration(decoration);
        rv_marble.setAdapter(marbleAdapter);

        rv_marble.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                MarbleAddDataToList(marbleProductListingResponse);
            }
        });

        marbleProductListingPresenter = new MarbleProductListingPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
        marbleProductListingPresenter.getMarble(url);
    }
    public int MarbleConvertDpToPx(Context context, float dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }
    public void MarbleAddDataToList(MarbleProductListingResponse marbleProductListingResponse){
        if (marbleProductListingResponse != null && marbleProductListingResponse.getMeta() != null && !marbleProductListingResponse.getMeta().getCurrentPage().equals(marbleProductListingResponse.getMeta().getLastPage())){
            marbleProductListingPresenter.getMarble(marbleProductListingResponse.getLinks().getNext().toString());
        }
    }







    public void bricks(String url){


        bricksAdapter = new BricksProductListingAdapter(getApplicationContext(), mBricks, this);


        GridLayoutManager horizontalLayoutManager
                = new GridLayoutManager(getApplicationContext(), 4);
        rv_bricks.setLayoutManager(horizontalLayoutManager);
        //adapter.setClickListener(this);
        RecyclerViewMargin decoration = new RecyclerViewMargin(BriksConvertDpToPx(getApplicationContext(),5), 4);
        rv_bricks.addItemDecoration(decoration);
        rv_bricks.setAdapter(bricksAdapter);

        rv_bricks.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                BriksAddDataToList(bricksProductListingResponse);
            }
        });

        bricksProductListingPresenter = new BricksProductListingPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
        bricksProductListingPresenter.getBricks(url);
    }
    public int BriksConvertDpToPx(Context context, float dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }
    public void BriksAddDataToList(BricksProductListingResponse bricksProductListingResponse){
        if (bricksProductListingResponse != null && bricksProductListingResponse.getMeta() != null && !bricksProductListingResponse.getMeta().getCurrentPage().equals(bricksProductListingResponse.getMeta().getLastPage())){
            bricksProductListingPresenter.getBricks(bricksProductListingResponse.getLinks().getNext().toString());
        }
    }





    public void granite(String url){

        //url  https://colorceramics.com/api/v1/products/category/46


        graniteAdapter = new GraniteProductListingAdapter(getApplicationContext(), mGranite, this);
        //recyclerView = v.findViewById(R.id.product_list);


        GridLayoutManager horizontalLayoutManager
                = new GridLayoutManager(getApplicationContext(), 4);
        rv_granite.setLayoutManager(horizontalLayoutManager);
        //adapter.setClickListener(this);
        RecyclerViewMargin decoration = new RecyclerViewMargin(GraniteConvertDpToPx(getApplicationContext(),5), 4);
        rv_granite.addItemDecoration(decoration);
        rv_granite.setAdapter(graniteAdapter);

        rv_granite.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                GraniteAddDataToList(graniteProductListingResponse);
            }
        });

        graniteProductListingPresenter = new GraniteProductListingPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
        graniteProductListingPresenter.getGranite(url);
    }
    public int GraniteConvertDpToPx(Context context, float dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }
    public void GraniteAddDataToList(GraniteProductListingResponse graniteProductListingResponse){
        if (graniteProductListingResponse != null && graniteProductListingResponse.getMeta() != null && !graniteProductListingResponse.getMeta().getCurrentPage().equals(graniteProductListingResponse.getMeta().getLastPage())){
            graniteProductListingPresenter.getGranite(graniteProductListingResponse.getLinks().getNext().toString());
        }
    }






    /**
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
     */
    /**
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
     */

    /*
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
    */

    /**
     3dEpoxy product showend from here
     *//*

*/

}