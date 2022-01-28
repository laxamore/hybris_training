package org.training.core.service.supplier.dao.impl;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import org.apache.commons.collections.CollectionUtils;
import org.training.core.model.SupplierModel;
import org.training.core.service.supplier.dao.SupplierDAO;

import javax.annotation.Resource;

public class SupplierDAOImpl implements SupplierDAO {
    private static final String QUERY_TRAINING_USER = "SELECT {pk} FROM {User}";

    @Resource
    private FlexibleSearchService flexibleSearchService;

    @Override
    public SupplierModel getAllSupplier() {
        final FlexibleSearchQuery flexibleSearchForQuery = new FlexibleSearchQuery(QUERY_TRAINING_USER);
        final SearchResult<SupplierModel> supplier = flexibleSearchService.search(flexibleSearchForQuery);

        if (CollectionUtils.isNotEmpty(supplier.getResult())) {
            return supplier.getResult().get(0);
        }
        return null;
    }

    // Need to be implemented
    @Override
    public boolean updateSupplierStatus(SupplierModel supplierModel) {
        return false;
    }
}
