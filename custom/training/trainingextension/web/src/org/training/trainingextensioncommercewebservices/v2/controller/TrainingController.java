package org.training.trainingextensioncommercewebservices.v2.controller;

import de.hybris.platform.commercewebservicescommons.dto.basesite.BaseSiteListWsDTO;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.training.facades.user.TrainingUserFacade;
import org.training.facades.user.data.UserData;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/{baseSiteId}", headers="Accept=application/xml,application/json")
@Api(tags = "Training")
public class TrainingController extends BaseController {

    @Resource(name="trainingUserFacade")
    private TrainingUserFacade trainingUserFacade;

    @RequestMapping(value = "/hello-wolrd", method = RequestMethod.GET)
    @ResponseBody
    public UserData getHelloWorld() {
        UserData userData = new UserData();
        userData.setName("hello world!!");
        return userData;
    }
}
