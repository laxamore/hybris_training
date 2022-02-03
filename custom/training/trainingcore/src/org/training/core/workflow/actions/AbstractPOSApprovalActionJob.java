package org.training.core.workflow.actions;

import de.hybris.platform.basecommerce.enums.PointOfServiceTypeEnum;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.storelocator.model.PointOfServiceModel;
import de.hybris.platform.workflow.jobs.AutomatedWorkflowTemplateJob;
import de.hybris.platform.workflow.model.WorkflowActionModel;
import de.hybris.platform.workflow.model.WorkflowDecisionModel;
import org.apache.commons.collections.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * The Class AbstractPOSApprovalActionJob
 *
 * @author kris.sunu.purnandaru
 */
public class AbstractPOSApprovalActionJob implements AutomatedWorkflowTemplateJob {

    @Resource(name = "modelService")
    private ModelService modelService;

    protected WorkflowDecisionModel approvePOSAndFetchDecision(final WorkflowActionModel action) {
        final PointOfServiceModel pos = getPOSFromAttachment(action);
        if (null != pos) {
            pos.setType(PointOfServiceTypeEnum.STORE);
            modelService.save(pos);
            return action.getDecisions().iterator().next();
        }
        return null;
    }

    protected WorkflowDecisionModel rejectPOSAndFetchDecision(final WorkflowActionModel action) {
        final PointOfServiceModel pos = getPOSFromAttachment(action);
        if (null != pos) {
            pos.setType(PointOfServiceTypeEnum.WAREHOUSE);
            modelService.save(pos);
            return action.getDecisions().iterator().next();
        }
        return null;
    }

    protected PointOfServiceModel getPOSFromAttachment(final WorkflowActionModel action) {
        final List<ItemModel> attachments = action.getAttachmentItems();
        if (CollectionUtils.isNotEmpty(attachments)) {
            for (final ItemModel item : attachments) {
                if (item instanceof PointOfServiceModel) {
                    return (PointOfServiceModel) item;
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
}
