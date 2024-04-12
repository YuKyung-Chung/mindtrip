package com.a303.consultms.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

public abstract class MongoBaseEntity {
    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime createTime;

    @LastModifiedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    public LocalDateTime getCreateTime() {
        return createTime;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }
}
