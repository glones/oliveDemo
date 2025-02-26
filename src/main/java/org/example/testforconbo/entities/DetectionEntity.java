package org.example.testforconbo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "detections")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class DetectionEntity {
    @Id
    @UuidGenerator
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "`uniqueId`")
    private String uniqueId;

    @Column(name = "`type`")
    private String type;

    @Column(name = "`confidence`")
    private Double confidence;

    @Column(name = "`detectionTimestamp`")
    private Instant detectionTimestamp;

    @Column(name = "`lat`")
    private Double lat;

    @Column(name = "`lon`")
    private Double lon;

    @Column(name = "`source`")
    private String source;

    @Column(name = "`insertedAt`")
    private Instant insertedAt = Instant.now();
}
