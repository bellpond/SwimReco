package com.swimreco.api.repository;

import static java.lang.String.format;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.WriteResult;
import com.swimreco.api.domain.User;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private static final Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);

    private final Firestore db;

    private final ObjectMapper mapper;

    public UserRepositoryImpl() {
        this.db = FirestoreOptions.getDefaultInstance().getService();
        this.mapper = new ObjectMapper();
    }

    @Override
    public void set(User user, String collectionPath) throws Exception {
        // Create a Map to store the data
        Map<String, Object> userDoc = mapper.convertValue(user, Map.class);

        // Add a new document in collection
        ApiFuture<WriteResult> future = db.collection(collectionPath).document(user.getUserId()).set(userDoc);
        Timestamp updateTime = future.get().getUpdateTime();
        logger.info(format("Added a new user [user id = %s] into the database at %s. ", user.getUserId(), updateTime));
    }

    @Override
    public User get(String userId, String collectionPath) throws ExecutionException, InterruptedException {
        ApiFuture<DocumentSnapshot> future = db.collection(collectionPath).document(userId).get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            logger.info("Document data: " + document.getData());
            return mapper.convertValue(document.getData(), User.class);
        } else {
            logger.debug(format("No such user with userId <%s>", userId));
            return null;
        }
    }
}
