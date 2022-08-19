package com.swimreco.api.repository;

import static java.lang.String.format;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.WriteResult;
import com.swimreco.api.domain.User;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserRepositoryImpl implements UserRepository {

    private static final Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);

    private Firestore db;

    public UserRepositoryImpl() {
        this.db = FirestoreOptions.getDefaultInstance().getService();
    }

    @Override
    public void set(User user, String collectionPath) throws Exception {
        // Create a Map to store the data
        Map<String, Object> userDoc = new ObjectMapper().convertValue(user, Map.class);

        // Add a new document in collection
        ApiFuture<WriteResult> future = db.collection(collectionPath).document(user.getUserId()).set(userDoc);
        future.get();
        logger.info(format("Added a new user into the database. [user id = %s]", user.getUserId()));
    }
}
