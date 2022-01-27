package org.training.core.service.product.impl;

import de.hybris.platform.core.model.product.ProductModel;
import org.training.core.service.product.TrainingProductService;
import org.training.core.service.product.dao.TrainingProductDAO;
import org.training.facades.product.data.ProductData;

import javax.annotation.Resource;

public class TrainingProductServiceImpl implements TrainingProductService {

    @Resource
    public TrainingProductDAO trainingProductDAO;

    @Override
    public ProductData getProductByID(final String id) {
        ProductModel productModel = trainingProductDAO.getProductByID(id);

        ProductData productData = new ProductData();

        productData.setId(productModel.getCode());
        productData.setName(productModel.getName());

        return productData;
    }
}
