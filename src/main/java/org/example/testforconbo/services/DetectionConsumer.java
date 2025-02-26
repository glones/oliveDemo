package org.example.testforconbo.services;

import lombok.extern.slf4j.Slf4j;
import org.example.testforconbo.dto.DetectionRecordDTO;
import org.example.testforconbo.entities.DetectionEntity;
import org.example.testforconbo.repositories.DetectionRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Slf4j
@Service
public class DetectionConsumer {

    private final DetectionRepository detectionRepository;

    public DetectionConsumer(DetectionRepository detectionRepository) {
        this.detectionRepository = detectionRepository;
    }

    @KafkaListener(topics = "object-detections", groupId = "detections-group")
    public void consume(DetectionRecordDTO dto) {
        log.info("Consumed from Kafka: {}", dto);

        DetectionEntity entity = new DetectionEntity();
        entity.setUniqueId(dto.getUniqueId());
        entity.setType(dto.getType());
        entity.setConfidence(dto.getConfidence());
        entity.setDetectionTimestamp(Instant.parse(dto.getTimestamp()));
        entity.setLat(dto.getLat());
        entity.setLon(dto.getLon());
        entity.setSource(dto.getSource());

        detectionRepository.save(entity);
    }
}
