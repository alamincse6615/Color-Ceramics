package com.activeitzone.activeecommercecms.Presentation.ui.activities;

import com.activeitzone.activeecommercecms.Network.response.FloorTilesProductListingResponse;
import com.activeitzone.activeecommercecms.Network.response.ProductListingResponse;
import com.activeitzone.activeecommercecms.Network.response.SanitaryProductListingResponse;
import com.activeitzone.activeecommercecms.Network.response.TilesProductListingResponse;

public interface ProductListingView {
    void setProducts(ProductListingResponse productListingResponse);
    void setTiles(TilesProductListingResponse tilesproductListingResponse);
    void setSanitary(SanitaryProductListingResponse sanitaryProductListingResponse);
    void setFloorTiles(FloorTilesProductListingResponse floorTilesProductListingResponse);
}
