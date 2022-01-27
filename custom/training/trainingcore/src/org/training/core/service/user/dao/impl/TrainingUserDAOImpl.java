package org.training.core.service.user.dao.impl;

import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import org.apache.commons.collections.CollectionUtils;
import org.training.core.service.user.dao.TrainingUserDAO;

import javax.annotation.Resource;
import java.util.Arrays;

public class TrainingUserDAOImpl implements TrainingUserDAO {

    private static final String QUERY_TRAINING_USER = "SELECT {pk} FROM {User} WHERE {uid} = ?email";

    @Resource
    private FlexibleSearchService flexibleSearchService;

    @Override
    public UserModel getUserByEmail(final String email) {
        final FlexibleSearchQuery flexibleSearchForQuery = new FlexibleSearchQuery(QUERY_TRAINING_USER);
        flexibleSearchForQuery.addQueryParameter("email", email);
        final SearchResult<UserModel> users = flexibleSearchService.search(flexibleSearchForQuery);

        if (CollectionUtils.isNotEmpty(users.getResult())) {
            return users.getResult().get(0);
        }
        return null;
    }
}
