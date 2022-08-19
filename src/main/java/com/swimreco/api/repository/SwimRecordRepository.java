package com.swimreco.api.repository;

import com.swimreco.api.domain.SwimRecord;
import com.swimreco.api.domain.SwimRecordSelector;
import java.util.List;

public interface SwimRecordRepository {
    List<SwimRecord> find(SwimRecordSelector selector);
    SwimRecord get(Long swimRecordId);
}
