package com.colorceramics.soft_it_care.Presentation.ui.activities.impl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.colorceramics.soft_it_care.Models.Product;
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
import com.colorceramics.soft_it_care.Presentation.presenters.ProductListingPresenter;
import com.colorceramics.soft_it_care.Presentation.ui.activities.ProductListingView;
import com.colorceramics.soft_it_care.Presentation.ui.adapters.ProductListingAdapter;
import com.colorceramics.soft_it_care.Presentation.ui.listeners.EndlessRecyclerOnScrollListener;
import com.colorceramics.soft_it_care.Presentation.ui.listeners.ProductClickListener;
import com.colorceramics.soft_it_care.R;
import com.colorceramics.soft_it_care.Threading.MainThreadImpl;
import com.colorceramics.soft_it_care.Utils.RecyclerViewMargin;
import com.colorceramics.soft_it_care.domain.executor.impl.ThreadExecutor;

import java.util.ArrayList;
import java.util.List;

public class ProductListingActivity extends BaseActivity implements ProductListingView, ProductClickListener {

    private List<Product> mProducts = new ArrayList<>();
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
    private SanitaryProductListingResponse sanitaryProductListingResponse = null;


    private TilesProductListingResponse tilesProductListingResponse;
    private FloorTilesProductListingResponse floorTilesProductListingResponse;
    private DEpoxyProductListingResponse dEpoxyProductListingResponse;
    private StoneProductListingResponse stoneProductListingResponse;
    private SandProductListingResponse sandProductListingResponse;
    private MarbleProductListingResponse marbleProductListingResponse;
    private GraniteProductListingResponse graniteProductListingResponse;
    private BricksProductListingResponse bricksProductListingResponse;

    private ProductListingPresenter productListingPresenter;
    private ProductListingAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TextView products_empty_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_listing);

        String title = getIntent().getStringExtra("title");
        String url = getIntent().getStringExtra("url");

        Log.d("------------url________",url.toString());

        initializeActionBar();
        setTitle(title);

        adapter = new ProductListingAdapter(getApplicationContext(), mProducts, this);
        recyclerView = findViewById(R.id.product_list);
        progressBar = findViewById(R.id.item_progress_bar);
        products_empty_text = findViewById(R.id.products_empty_text);

        GridLayoutManager horizontalLayoutManager
                = new GridLayoutManager(ProductListingActivity.this, 4);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        //adapter.setClickListener(this);
        RecyclerViewMargin decoration = new RecyclerViewMargin(convertDpToPx(this,5), 4);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                    addDataToList(productListingResponse);
            }
        });

        progressBar.setVisibility(View.VISIBLE);

        productListingPresenter = new ProductListingPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
        productListingPresenter.getProducts(url);
    }

    @Override
    public void setProducts(ProductListingResponse productListingResponse) {
        mProducts.addAll(productListingResponse.getData());
        this.productListingResponse = productListingResponse;
        progressBar.setVisibility(View.GONE);
        adapter.notifyDataSetChanged();

        if (mProducts.size() <= 0){
            products_empty_text.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setTiles(TilesProductListingResponse tilesproductListingResponse) {
        mTitles.addAll(tilesproductListingResponse.getData());
        this.tilesProductListingResponse = tilesproductListingResponse;
    }


    @Override
    public void setSanitary(SanitaryProductListingResponse sanitaryProductListingResponse) {
        mSanitary.addAll(sanitaryProductListingResponse.getData());
        this.sanitaryProductListingResponse = sanitaryProductListingResponse;

    }

    @Override
    public void setFloorTiles(FloorTilesProductListingResponse floorTilesProductListingResponse) {
        mFloorTiles.addAll(floorTilesProductListingResponse.getData());
        this.floorTilesProductListingResponse = floorTilesProductListingResponse;
    }

    @Override
    public void setDEpoxy(DEpoxyProductListingResponse dEpoxyProductListingResponse) {
        mDEpoxy.addAll(dEpoxyProductListingResponse.getData());
        this.dEpoxyProductListingResponse = dEpoxyProductListingResponse;
    }

    @Override
    public void setStone(StoneProductListingResponse stoneProductListingResponse) {
        mStone.addAll(stoneProductListingResponse.getData());
        this.stoneProductListingResponse = stoneProductListingResponse;
    }

    @Override
    public void setSand(SandProductListingResponse sandProductListingResponse) {
        mSand.addAll(sandProductListingResponse.getData());
        this.sandProductListingResponse = sandProductListingResponse;
    }

    @Override
    public void setMarble(MarbleProductListingResponse marbleProductListingResponse) {
        mMarble.addAll(marbleProductListingResponse.getData());
        this.marbleProductListingResponse = marbleProductListingResponse;
    }

    @Override
    public void setGranite(GraniteProductListingResponse graniteProductListingResponse) {
        mGranite.addAll(graniteProductListingResponse.getData());
        this.graniteProductListingResponse = graniteProductListingResponse;
    }

    @Override
    public void setBricks(BricksProductListingResponse bricksProductListingResponse) {
        mBricks.addAll(bricksProductListingResponse.getData());
        this.bricksProductListingResponse = bricksProductListingResponse;
    }


    public void addDataToList(ProductListingResponse productListingResponse){
        if (productListingResponse != null && productListingResponse.getMeta() != null && !productListingResponse.getMeta().getCurrentPage().equals(productListingResponse.getMeta().getLastPage())){
            progressBar.setVisibility(View.VISIBLE);
            productListingPresenter.getProducts(productListingResponse.getLinks().getNext().toString());
        }
    }

    @Override
    public void onProductItemClick(Product product) {
        Intent intent = new Intent(this, ProductDetailsActivity.class);
        intent.putExtra("product_name", product.getName());
        intent.putExtra("link", product.getLinks().getDetails());
        intent.putExtra("top_selling", product.getLinks().getRelated());
        startActivity(intent);
    }

    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp      A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public int convertDpToPx(Context context, float dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }
}
