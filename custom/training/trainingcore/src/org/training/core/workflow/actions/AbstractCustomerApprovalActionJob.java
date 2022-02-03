package org.training.core.workflow.actions;

import de.hybris.platform.commerceservices.model.process.CustomerActionProcessModel;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.workflow.jobs.AutomatedWorkflowTemplateJob;
import de.hybris.platform.workflow.model.WorkflowActionModel;
import de.hybris.platform.workflow.model.WorkflowDecisionModel;
import org.apache.commons.collections.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * The Class AbstractCustomerApprovalActionJob
 *
 * @author kris.sunu.purnandaru
 */
public class AbstractCustomerApprovalActionJob implements AutomatedWorkflowTemplateJob {

    @Resource(name = "modelService")
    private ModelService modelService;

    @Resource(name = "businessProcessService")
    private BusinessProcessService businessProcessService;

    protected WorkflowDecisionModel approveCustomerAndFetchDecision(final WorkflowActionModel action) {
        final CustomerModel cus = getCustomerFromAttachment(action);
        if (null != cus) {
            cus.setDescription("approve");
            modelService.save(cus);

            final CustomerActionProcessModel customerActionProcessModel = getBusinessProcessService().createProcess(
                    "defaultCustomerAction-" + System.currentTimeMillis(),
                    "defaultCustomerAction");
            customerActionProcessModel.setIsApprove("approve");
            getModelService().save(customerActionProcessModel);
            getBusinessProcessService().startProcess(customerActionProcessModel);

            return action.getDecisions().iterator().next();
        }


        return null;
    }

    protected WorkflowDecisionModel rejectCustomerAndFetchDecision(final WorkflowActionModel action) {
        final CustomerModel cus = getCustomerFromAttachment(action);
        if (null != cus) {
            cus.setDescription("reject");
            modelService.save(cus);

            final CustomerActionProcessModel customerActionProcessModel = getBusinessProcessService().createProcess(
                    "defaultCustomerAction-" + System.currentTimeMillis(),
                    "defaultCustomerAction");
            customerActionProcessModel.setIsApprove("reject");
            getModelService().save(customerActionProcessModel);
            getBusinessProcessService().startProcess(customerActionProcessModel);

            return action.getDecisions().iterator().next();
        }
        return null;
    }

    protected CustomerModel getCustomerFromAttachment(final WorkflowActionModel action) {
        final List<ItemModel> attachments = action.getAttachmentItems();
        if (CollectionUtils.isNotEmpty(attachments)) {
            for (final ItemModel item : attachments) {
                if (item instanceof CustomerModel) {
                    return (CustomerModel) item;
                }
            }
        }
        return null;
    }

    @Override
    public WorkflowDecisionModel perform(final WorkflowActionModel action) {
        // YTODO Auto-generated method stub
        return null;
    }

    public BusinessProcessService getBusinessProcessService() {
        return businessProcessService;
    }

    public void setBusinessProcessService(BusinessProcessService businessProcessService) {
        this.businessProcessService = businessProcessService;
    }

    public ModelService getModelService() {
        return modelService;
    }

    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }
}
