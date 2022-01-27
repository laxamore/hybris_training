package org.training.core.service.user.impl;

import de.hybris.platform.core.model.user.UserModel;
import org.training.core.service.user.TrainingUserService;
import org.training.core.service.user.dao.TrainingUserDAO;
import org.training.facades.user.data.UserData;

import javax.annotation.Resource;

public class TrainingUserServiceImpl implements TrainingUserService {

    @Resource
    private TrainingUserDAO trainingUserDAO;

    @Override
    public UserData getUserByEmail(final String email) {
        UserModel userModel = trainingUserDAO.getUserByEmail(email);

        UserData userData = new UserData();

        userData.setEmail(userModel.getUid());
        userData.setName(userModel.getName());

        return userData;
    }
}
