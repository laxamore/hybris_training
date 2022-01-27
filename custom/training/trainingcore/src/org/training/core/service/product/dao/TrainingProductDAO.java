package org.training.core.service.product.dao;

import de.hybris.platform.core.model.product.ProductModel;

public interface TrainingProductDAO {
    ProductModel getProductByID(final String id);
}
