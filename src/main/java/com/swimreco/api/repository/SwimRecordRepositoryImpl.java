package com.swimreco.api.repository;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.swimreco.api.domain.SwimRecord;
import com.swimreco.api.domain.SwimRecordSelector;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class SwimRecordRepositoryImpl implements SwimRecordRepository {

    FirestoreOptions firestoreOptions =
            FirestoreOptions.getDefaultInstance().toBuilder()
                    .setProjectId(projectId)
                    .setCredentials(GoogleCredentials.getApplicationDefault())
                    .build();
    Firestore db = firestoreOptions.getService();

    @Override
    public List<SwimRecord> find(SwimRecordSelector selector) {
        return null;
    }

    @Override
    public SwimRecord get(Long swimRecordId) {
        return null;
    }
}
