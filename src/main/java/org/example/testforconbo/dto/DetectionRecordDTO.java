package org.example.testforconbo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetectionRecordDTO {
    private String uniqueId;
    private String type;
    private double confidence;
    private String timestamp;
    private double lat;
    private double lon;
    private String source;
}
