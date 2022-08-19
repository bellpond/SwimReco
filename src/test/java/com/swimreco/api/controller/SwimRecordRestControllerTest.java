package com.swimreco.api.controller;

import static org.junit.jupiter.api.Assertions.*;
import com.swimreco.api.domain.SwimRecord;
import com.swimreco.api.domain.SwimRecordList;
import com.swimreco.api.domain.SwimRecordSelector;
import com.swimreco.api.service.SwimRecordService;
import com.swimreco.api.util.UnitTestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class SwimRecordRestControllerTest {

    private static final String API_CAll_PREFIX = "/services/v1/swimRecords";

    @Mock
    private SwimRecordService service;

    @InjectMocks
    private SwimRecordRestController target;

    private MockMvc mockMvc;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(target).build();
    }

    @Test
    void findによる全件検索() throws Exception {
        SwimRecordList findResult = new SwimRecordList();
        ArgumentMatcher<SwimRecordSelector> matcher = argument -> {
            assertNull(argument.getId());
            assertNull(argument.getMemberId());
            assertNull(argument.getStyleId());
            assertNull(argument.getLength());
            assertNull(argument.getPoolSize());
            assertNull(argument.getRecordedAt());
            return true;
        };
        Mockito.doReturn(findResult).when(this.service).find(Mockito.argThat(matcher));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(API_CAll_PREFIX))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Mockito.verify(
                this.service,
                Mockito.times(1)
        ).find(Mockito.argThat(matcher));

        assertEquals(
                UnitTestUtils.entity2JsonText(findResult),
                result.getResponse().getContentAsString()
        );
    }

    @Test
    void findによる条件検索_memberId() throws Exception {
        SwimRecordSelector selector = new SwimRecordSelector();
        selector.setMemberId(2L);
        SwimRecordList findResult = new SwimRecordList();
        ArgumentMatcher<SwimRecordSelector> matcher = argument -> {
            assertEquals(2L, argument.getMemberId());
            return true;
        };
        Mockito.doReturn(findResult).when(this.service).find(Mockito.argThat(matcher));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get(API_CAll_PREFIX)
                        .param("memberId", selector.getMemberId().toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Mockito.verify(
                this.service,
                Mockito.times(1)
        ).find(Mockito.argThat(matcher));

        assertEquals(
                UnitTestUtils.entity2JsonText(findResult),
                result.getResponse().getContentAsString()
        );
    }

    @Test
    void getによるレコードの取得() throws Exception {
        Long swimRecordId = 2L;
        SwimRecord findResult = new SwimRecord();
        Mockito.doReturn(findResult).when(this.service).get(swimRecordId);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get(API_CAll_PREFIX + "/" + swimRecordId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Mockito.verify(
                this.service,
                Mockito.times(1)
        ).get(swimRecordId);

        assertEquals(
                UnitTestUtils.entity2JsonText(findResult),
                result.getResponse().getContentAsString()
        );
    }
}