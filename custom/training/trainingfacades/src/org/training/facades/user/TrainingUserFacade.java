package org.training.facades.user;

import org.training.facades.user.data.UserData;

public  interface TrainingUserFacade {
    UserData getUserByEmail(final String email);
}
