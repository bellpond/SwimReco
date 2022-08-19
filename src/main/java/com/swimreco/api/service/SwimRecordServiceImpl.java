package com.swimreco.api.service;

import com.swimreco.api.domain.SwimRecord;
import com.swimreco.api.domain.SwimRecordList;
import com.swimreco.api.domain.SwimRecordSelector;
import com.swimreco.api.repository.SwimRecordRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SwimRecordServiceImpl implements SwimRecordService {

    private final SwimRecordRepository repository;

    public SwimRecordServiceImpl(SwimRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public SwimRecordList find(SwimRecordSelector selector) {
        List<SwimRecord> swimRecords = this.repository.find(selector);
        SwimRecordList swimRecordList = new SwimRecordList();
        swimRecordList.setSwimRecords(swimRecords);
        return swimRecordList;
    }

    @Override
    public SwimRecord get(Long swimRecordId) {
        return this.repository.get(swimRecordId);
    }
}
