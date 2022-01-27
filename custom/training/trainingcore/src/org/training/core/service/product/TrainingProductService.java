package org.training.core.service.product;

import org.training.facades.product.data.ProductData;

public interface TrainingProductService {
    ProductData getProductByID(final String id);
}
