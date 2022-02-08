package org.training.storefront.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/custom-page")
public class TrainingCustomPageController extends AbstractPageController {
    private static final String TRAINING_CUSTOM_PAGE = "trainingCustomPage";
    @RequestMapping(value=TRAINING_CUSTOM_PAGE, method = RequestMethod.GET)
    public String getTrainingCustomPage(final Model model) throws CMSItemNotFoundException
    {
        final ContentPageModel trainingCustomPageData = getContentPageForLabelOrId(TRAINING_CUSTOM_PAGE);
        storeCmsPageInModel(model, trainingCustomPageData);
        setUpMetaDataForContentPage(model, trainingCustomPageData);
        return getViewForPage(model);
    }
}
