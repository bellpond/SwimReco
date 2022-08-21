package com.swimreco.api.controller;

import com.swimreco.api.domain.SwimRecord;
import com.swimreco.api.domain.SwimRecordList;
import com.swimreco.api.domain.SwimRecordSelector;
import com.swimreco.api.service.SwimRecordService;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("services/v1/swimRecords")
public class SwimRecordRestController {

    private Logger logger = LoggerFactory.getLogger(SwimRecordRestController.class);

    private final SwimRecordService service;

    public SwimRecordRestController(SwimRecordService service) {
        this.service = service;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public SwimRecordList find(
            @RequestParam(value = "userId", required = false) Long memberId,
            @RequestParam(value = "styleId", required = false) Integer styleId,
            @RequestParam(value = "length", required = false) Integer length,
            @RequestParam(value = "poolSize", required = false) Integer poolSize,
            @RequestParam(value = "recordedAt", required = false) LocalDateTime recordedAt
            ) {
        SwimRecordSelector selector = new SwimRecordSelector();
        selector.setUserId(memberId);
        selector.setStyleId(styleId);
        selector.setLength(length);
        selector.setPoolSize(poolSize);
        selector.setRecordedAt(recordedAt);
        return this.service.find(selector);
    }

    @GetMapping(value = "/{swimRecordId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public SwimRecord get(@PathVariable Long swimRecordId) {
        SwimRecord swimRecord = this.service.get(swimRecordId);
        if (swimRecord == null) {
            logger.info("Failed to get the record[id = " + swimRecordId + "]");
        }
        return swimRecord;
    }
}
