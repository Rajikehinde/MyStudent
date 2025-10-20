package com.My_Student.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@MappedSuperclass
@Entity
@ToString
@Setter
@Getter
public class BaseEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @CreatedBy()
    @Column(updatable = false)
    private String createdBy;

    @Column(insertable = false)
    @LastModifiedBy
    private String updatedBy;

    @Column(insertable = false)
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
