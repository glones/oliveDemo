package org.example.testforconbo.services;

import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.example.testforconbo.dto.DetectionRecordDTO;
import org.example.testforconbo.grpc.DetectionIngestionGrpc;
import org.example.testforconbo.grpc.DetectionRecord;
import org.example.testforconbo.grpc.DetectionsBatch;
import org.example.testforconbo.grpc.IngestionResponse;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DetectionIngestionServiceImpl extends DetectionIngestionGrpc.DetectionIngestionImplBase {

    private final KafkaTemplate<String, DetectionRecordDTO> kafkaTemplate;

    private static final String TOPIC_NAME = "object-detections";

    public DetectionIngestionServiceImpl(KafkaTemplate<String, DetectionRecordDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendDetections(DetectionsBatch request, StreamObserver<IngestionResponse> responseObserver) {
        if (request.getRecordsCount() == 0) {
            IngestionResponse response = IngestionResponse.newBuilder()
                    .setSuccess(false)
                    .setMessage("No records in batch")
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
            return;
        }

        log.info("Received batch from source {}, size = {}", request.getSource(), request.getRecordsCount());

        for (DetectionRecord r : request.getRecordsList()) {
            DetectionRecordDTO dto = new DetectionRecordDTO();
            dto.setUniqueId(r.getUniqueId());
            dto.setType(r.getType());
            dto.setConfidence(r.getConfidence());
            dto.setTimestamp(r.getTimestamp());
            dto.setLat(r.getLat());
            dto.setLon(r.getLon());
            dto.setSource(request.getSource());

            kafkaTemplate.send(TOPIC_NAME, dto.getUniqueId(), dto);
        }

        IngestionResponse response = IngestionResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Ingested " + request.getRecordsCount() + " records")
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
