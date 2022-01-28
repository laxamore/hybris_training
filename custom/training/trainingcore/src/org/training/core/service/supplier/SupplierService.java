package org.training.core.service.supplier;

import org.training.core.model.SupplierModel;

public interface SupplierService {
    SupplierModel getAllSupplier();
    boolean updateSupplierStatus(SupplierModel supplierModel);
}
