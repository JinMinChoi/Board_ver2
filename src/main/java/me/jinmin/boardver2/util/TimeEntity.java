package me.jinmin.boardver2.util;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class TimeEntity {

    @CreatedDate
    private String createdDate;

    @LastModifiedDate
    private String updatedDate;

    @PrePersist
    public void onPrePersist() {
        this.createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.updatedDate = createdDate;
    }

    @PreUpdate
    public void onPreUpdate() {
        this.updatedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
