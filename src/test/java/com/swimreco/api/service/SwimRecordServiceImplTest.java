package com.swimreco.api.service;

import static org.junit.jupiter.api.Assertions.*;
import com.swimreco.api.domain.SwimRecord;
import com.swimreco.api.domain.SwimRecordList;
import com.swimreco.api.domain.SwimRecordSelector;
import com.swimreco.api.repository.SwimRecordRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class SwimRecordServiceImplTest {

    @Mock
    private SwimRecordRepository swimRecordRepository;

    @InjectMocks
    private SwimRecordServiceImpl target;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFind() {
        SwimRecordSelector selector = new SwimRecordSelector();
        List<SwimRecord> findResults = new ArrayList<>();
        Mockito.doReturn(findResults).when(this.swimRecordRepository).find(selector);

        SwimRecordList swimRecordList = target.find(selector);

        assertEquals(findResults, swimRecordList.getSwimRecords());
        Mockito
                .verify(this.swimRecordRepository, Mockito.times(1))
                .find(selector);
    }

}