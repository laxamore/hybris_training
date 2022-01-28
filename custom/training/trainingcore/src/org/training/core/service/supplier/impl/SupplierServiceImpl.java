package org.training.core.service.supplier.impl;

import org.training.core.model.SupplierModel;
import org.training.core.service.supplier.SupplierService;
import org.training.core.service.supplier.dao.SupplierDAO;

import javax.annotation.Resource;

public class SupplierServiceImpl implements SupplierService {
    @Resource
    SupplierDAO supplierDAO;

    @Override
    public SupplierModel getAllSupplier() {
        SupplierModel supplierModel = supplierDAO.getAllSupplier();

        if (supplierModel != null)
            return supplierModel;
        return null;
    }

    // Need to be implemented
    @Override
    public boolean updateSupplierStatus(SupplierModel supplierModel) {
        return false;
    }
}
