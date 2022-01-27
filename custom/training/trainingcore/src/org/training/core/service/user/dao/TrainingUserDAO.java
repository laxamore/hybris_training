package org.training.core.service.user.dao;

import de.hybris.platform.core.model.user.UserModel;

public interface TrainingUserDAO {
     UserModel getUserByEmail(final String email);
}
