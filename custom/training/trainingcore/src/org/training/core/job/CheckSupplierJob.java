package org.training.core.job;

import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.model.ModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.training.core.enums.SupplierStatus;
import org.training.core.model.SupplierModel;
import org.training.core.service.supplier.dao.SupplierDAO;

import javax.annotation.Resource;
import java.util.List;

public class CheckSupplierJob extends AbstractJobPerformable<CronJobModel> {
    private static final Logger LOG = LoggerFactory.getLogger(CheckSupplierJob.class);

    @Resource
    ModelService modelService;

    @Resource
    SupplierDAO supplierDAO;

    @Override
    public PerformResult perform(final CronJobModel cronJobModel) {
        List<SupplierModel> supplierModels = supplierDAO.getAllSupplier();

        if (supplierModels != null) {
            for (SupplierModel supplierModel : supplierModels) {
                String supplierName = supplierModel.getName();
                SupplierStatus supplierStatus = supplierModel.getStatus();
                LOG.info("{} {}", supplierName, supplierStatus);

                if (supplierStatus != SupplierStatus.VERIFIED && supplierStatus != SupplierStatus.IN_REVIEW) {
                    supplierModel.setStatus(SupplierStatus.IN_REVIEW);
                    modelService.save(supplierModel);
                    LOG.info("Updating {} to {}", supplierName, SupplierStatus.IN_REVIEW);
                }
            }
        } else {
            LOG.info("Supplier is empty.");
        }

        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }
}
