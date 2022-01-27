package org.training.core.service.user;

import org.training.facades.user.data.UserData;

public interface TrainingUserService {
    UserData getUserByEmail(final String email);
}
