package com.swimreco.api.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

public class SwimRecord {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("style_id")
    private Integer styleId;

    @JsonProperty("length")
    private Integer length;

    @JsonProperty("pool_size")
    private Integer poolSize;

    @JsonProperty("milli_second_record")
    private Long milliSecondsRecord;

    @JsonProperty("recorded_at")
    private LocalDateTime recordedAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getStyleId() {
        return styleId;
    }

    public void setStyleId(Integer styleId) {
        this.styleId = styleId;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getPoolSize() {
        return poolSize;
    }

    public void setPoolSize(Integer poolSize) {
        this.poolSize = poolSize;
    }

    public Long getMilliSecondsRecord() {
        return milliSecondsRecord;
    }

    public void setMilliSecondsRecord(long milliSecondsRecord) {
        this.milliSecondsRecord = milliSecondsRecord;
    }

    public LocalDateTime getRecordedAt() {
        return recordedAt;
    }

    public void setRecordedAt(LocalDateTime recordedAt) {
        this.recordedAt = recordedAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
