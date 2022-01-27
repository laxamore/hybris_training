package org.training.facades.user.populator;


import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.user.UserModel;
import org.training.facades.user.data.PhoneData;
import org.training.facades.user.data.UserData;

public class TrainingUserPopulator implements Populator<UserModel, UserData> {

    @Override
    public void populate(final UserModel userModel, final UserData userData) {
        userData.setEmail(userModel.getUid());
        userData.setName(userModel.getName());

        PhoneData phoneData = new PhoneData();
        phoneData.setNumber("8123456789");
        phoneData.setCode("+62");

        userData.setPhone(phoneData);
    }
}
