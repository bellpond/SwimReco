package com.swimreco.api.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.swimreco.api.domain.SwimRecord;
import com.swimreco.api.domain.SwimRecordSelector;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.springframework.stereotype.Repository;

@Repository
public class SwimRecordRepositoryImpl implements SwimRecordRepository {

    private Firestore db;

    public SwimRecordRepositoryImpl() {
        this.db = FirestoreOptions.getDefaultInstance().getService();
    }

    @Override
    public List<SwimRecord> find(SwimRecordSelector selector) {
        // asynchronously retrieve all users
        ApiFuture<QuerySnapshot> query = db.collection("users").get();
        // ...
        // query.get() blocks on response
        QuerySnapshot querySnapshot = null;
        try {
            querySnapshot = query.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            System.out.println("User: " + document.getId());
            System.out.println("First: " + document.getString("first-name"));
            if (document.contains("middle")) {
                System.out.println("Middle: " + document.getString("middle"));
            }
            System.out.println("Last: " + document.getString("last-name"));
        }
        System.out.println("printed all");
        return null;
    }

    @Override
    public SwimRecord get(Long swimRecordId) {
        return null;
    }
}
