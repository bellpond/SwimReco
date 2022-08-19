package com.swimreco.api.service;

import com.swimreco.api.domain.SwimRecord;
import com.swimreco.api.domain.SwimRecordList;
import com.swimreco.api.domain.SwimRecordSelector;

public interface SwimRecordService {
    SwimRecordList find(SwimRecordSelector selector);
    SwimRecord get(Long swimRecordId);
}
