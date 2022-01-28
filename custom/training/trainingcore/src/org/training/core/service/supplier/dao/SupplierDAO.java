package org.training.core.service.supplier.dao;

import org.training.core.model.SupplierModel;

public interface SupplierDAO {
    SupplierModel getAllSupplier();
    boolean updateSupplierStatus(SupplierModel supplierModel);
}
