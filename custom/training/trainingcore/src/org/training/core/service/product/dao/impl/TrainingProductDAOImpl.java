package org.training.core.service.product.dao.impl;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import org.training.core.service.product.dao.TrainingProductDAO;

import javax.annotation.Resource;

public class TrainingProductDAOImpl implements TrainingProductDAO {
    private final String QUERY_TRAINING_USER = "SELECT {name} FROM {Product} WHERE {code} = 'PB-SHIRT-1'";

    @Resource
    public FlexibleSearchService flexibleSearchService;

    @Override
    public ProductModel getProductByID(final String id) {
        final FlexibleSearchQuery flexibleSearchForQuery = new FlexibleSearchQuery(QUERY_TRAINING_USER);
        final SearchResult<ProductModel> products = flexibleSearchService.search(flexibleSearchForQuery);
        return products.getResult().get(0);
    }

}
