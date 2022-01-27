package org.training.commercewebservices.v2.controller;

import org.hsqldb.rights.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.training.facades.user.TrainingUserFacade;
import org.training.facades.user.data.UserData;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/{baseSiteId}")
public class TrainingController extends BaseController {
    @Resource(name = "trainingUserFacade")
    private TrainingUserFacade trainingUserFacade;

    @RequestMapping(value = "/hello-world", method = RequestMethod.GET)
    @ResponseBody
    public String getHelloWorld() {
        return "hello world";
    }

    @RequestMapping(value = "/get-user/{email}")
    @ResponseBody
    public ResponseEntity<?> getUserByEmail(@PathVariable final String email) {
        UserData userData = trainingUserFacade.getUserByEmail(email);
        if (userData != null)
            return new ResponseEntity<UserData>(userData, HttpStatus.OK);
        return new ResponseEntity<String>("Email Not Found", HttpStatus.NOT_FOUND);
    }
}
