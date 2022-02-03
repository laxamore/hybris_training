package org.training.core.actions.customer;

import de.hybris.platform.commerceservices.model.process.CustomerActionProcessModel;
import de.hybris.platform.commerceservices.model.process.TestActionProcessModel;
import de.hybris.platform.processengine.action.AbstractSimpleDecisionAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomerAction extends AbstractSimpleDecisionAction<CustomerActionProcessModel> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerAction.class);

    @Override
    public Transition executeAction(CustomerActionProcessModel customerActionProcessModel) {
        //Do something here
        String isApprove = customerActionProcessModel.getIsApprove();

        //If everything goes well return OK, else NOK
        LOGGER.info("Test Action 1 OK!: {}", isApprove);
        if (isApprove == "approve") {
            return Transition.OK;
        } else return Transition.NOK;
    }
}
