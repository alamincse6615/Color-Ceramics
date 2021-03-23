package com.colorceramics.soft_it_care.Presentation.ui.activities;

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

public interface ProductListingView {
    void setProducts(ProductListingResponse productListingResponse);
    void setTiles(TilesProductListingResponse tilesproductListingResponse);
    void setSanitary(SanitaryProductListingResponse sanitaryProductListingResponse);
    void setFloorTiles(FloorTilesProductListingResponse floorTilesProductListingResponse);
    void setDEpoxy(DEpoxyProductListingResponse dEpoxyProductListingResponse);
    void setStone(StoneProductListingResponse stoneProductListingResponse);
    void setSand(SandProductListingResponse sandProductListingResponse);
    void setMarble(MarbleProductListingResponse marbleProductListingResponse);
    void setGranite(GraniteProductListingResponse graniteProductListingResponse);
    void setBricks(BricksProductListingResponse bricksProductListingResponse);
}
