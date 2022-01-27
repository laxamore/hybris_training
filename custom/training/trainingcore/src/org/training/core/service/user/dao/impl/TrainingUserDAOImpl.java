package org.training.core.service.user.dao.impl;

import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import org.training.core.service.user.dao.TrainingUserDAO;

import javax.annotation.Resource;

public class TrainingUserDAOImpl implements TrainingUserDAO {

    private static final String QUERY_TRAINING_USER = "SELECT {name} FROM {User} WHERE {uid} = 'mukhliskurnia60@gmail.com'";

    @Resource
    private FlexibleSearchService flexibleSearchService;

    @Override
    public UserModel getUserByEmail(final String email) {
        final FlexibleSearchQuery flexibleSearchForQuery = new FlexibleSearchQuery(QUERY_TRAINING_USER);
        final SearchResult<UserModel> users = flexibleSearchService.search(flexibleSearchForQuery);
        return users.getResult().get(0);
    }
}
