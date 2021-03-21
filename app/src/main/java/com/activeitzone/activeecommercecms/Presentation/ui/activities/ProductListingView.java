package com.activeitzone.activeecommercecms.Presentation.ui.activities;

import com.activeitzone.activeecommercecms.Network.response.ProductListingResponse;
import com.activeitzone.activeecommercecms.Network.response.SanitaryProductListingResponse;

public interface ProductListingView {
    void setProducts(ProductListingResponse productListingResponse);
    void setTiles(ProductListingResponse productListingResponse);
    void setSanitary(SanitaryProductListingResponse sanitaryProductListingResponse);
}
