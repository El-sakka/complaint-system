package com.example.complaint.model.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Getter
@MappedSuperclass
public abstract class BaseEntity {

    @Column(name = "created_at", columnDefinition = "TIMESTAMP")
    @NotNull(message = "Created At must not be null")
    protected Instant createdAt;

    @Column(name = "updated_at",columnDefinition = "TIMESTAMP")
    @NotNull(message = "Updated At must not be null")
    protected Instant updatedAt;

    @Column(name = "deleted_at",columnDefinition = "TIMESTAMP")
    protected Instant deletedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = Instant.now();
        updatedAt = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now();
    }
}
