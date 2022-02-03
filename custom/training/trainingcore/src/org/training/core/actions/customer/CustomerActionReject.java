package org.training.core.actions.customer;

import de.hybris.platform.commerceservices.model.process.CustomerActionProcessModel;
import de.hybris.platform.processengine.action.AbstractSimpleDecisionAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomerActionReject extends AbstractSimpleDecisionAction<CustomerActionProcessModel> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerAction.class);

    @Override
    public Transition executeAction(CustomerActionProcessModel customerActionProcessModel) {
        LOGGER.info("Test Action 3 OK");
        return Transition.NOK;
    }
}
