package com.swimreco.api.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class SwimRecordList {
    @JsonProperty("swim_records")
    private List<SwimRecord> swimRecords;

    public List<SwimRecord> getSwimRecords() {
        return swimRecords;
    }

    public void setSwimRecords(List<SwimRecord> swimRecords) {
        this.swimRecords = swimRecords;
    }
}
