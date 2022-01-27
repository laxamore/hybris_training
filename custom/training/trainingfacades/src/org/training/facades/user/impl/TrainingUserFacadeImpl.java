package org.training.facades.user.impl;

import org.training.core.service.user.TrainingUserService;
import org.training.facades.user.TrainingUserFacade;
import org.training.facades.user.data.UserData;

import javax.annotation.Resource;

public class TrainingUserFacadeImpl implements TrainingUserFacade {

    @Resource(name = "trainingUserService")
    private TrainingUserService trainingUserService;

    @Override
    public UserData getUserByEmail(final String email) {
        UserData userData = trainingUserService.getUserByEmail(email);
        if (userData != null)
           return userData;
        return null;
    }
}
