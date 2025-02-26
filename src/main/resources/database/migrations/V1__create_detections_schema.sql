CREATE TABLE IF NOT EXISTS detections
(
    id                  UUID         NOT NULL DEFAULT gen_random_uuid(),
    unique_id           TEXT,
    type                TEXT,
    confidence          DOUBLE PRECISION,
    detection_timestamp TIMESTAMP(3) NOT NULL,
    lat                 DOUBLE PRECISION,
    lon                 DOUBLE PRECISION,
    source              TEXT,
    inserted_at         TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX IF NOT EXISTS idx_detections_timestamp
    ON detections (detection_timestamp);

CREATE INDEX IF NOT EXISTS idx_detections_uniqueid
    ON detections (unique_id);
