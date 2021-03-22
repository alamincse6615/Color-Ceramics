package com.activeitzone.activeecommercecms.Presentation.ui.activities;

import com.activeitzone.activeecommercecms.Network.response.BricksProductListingResponse;
import com.activeitzone.activeecommercecms.Network.response.DEpoxyProductListingResponse;
import com.activeitzone.activeecommercecms.Network.response.FloorTilesProductListingResponse;
import com.activeitzone.activeecommercecms.Network.response.GraniteProductListingResponse;
import com.activeitzone.activeecommercecms.Network.response.MarbleProductListingResponse;
import com.activeitzone.activeecommercecms.Network.response.ProductListingResponse;
import com.activeitzone.activeecommercecms.Network.response.SandProductListingResponse;
import com.activeitzone.activeecommercecms.Network.response.SanitaryProductListingResponse;
import com.activeitzone.activeecommercecms.Network.response.StoneProductListingResponse;
import com.activeitzone.activeecommercecms.Network.response.TilesProductListingResponse;

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
