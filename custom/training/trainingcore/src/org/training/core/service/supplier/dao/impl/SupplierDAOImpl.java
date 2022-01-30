package org.training.core.service.supplier.dao.impl;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import org.apache.commons.collections.CollectionUtils;
import org.training.core.model.SupplierModel;
import org.training.core.service.supplier.dao.SupplierDAO;

import javax.annotation.Resource;
import java.util.List;

public class SupplierDAOImpl implements SupplierDAO {
    private static final String QERY_SUPPLIER  = "SELECT {pk} FROM {Supplier}";

    @Resource
    private FlexibleSearchService flexibleSearchService;

    @Override
    public List<SupplierModel> getAllSupplier() {
        final FlexibleSearchQuery flexibleSearchForQuery = new FlexibleSearchQuery(QERY_SUPPLIER);
        final SearchResult<SupplierModel> supplier = flexibleSearchService.search(flexibleSearchForQuery);

        if (CollectionUtils.isNotEmpty(supplier.getResult())) {
            return supplier.getResult();
        }
        return null;
    }
}
