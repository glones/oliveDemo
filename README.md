## Overview
This project implements a real-time object detection ingestion pipeline using **gRPC, Kafka, PostgreSQL, and Spring Boot**. The pipeline receives detection events from edge devices, processes them via Kafka, and stores them in a PostgreSQL database for retrieval and analysis.

## Features
- **gRPC API**: Handles incoming object detection data.
- **Kafka Producer & Consumer**: Ensures efficient streaming of data.
- **PostgreSQL Storage**: Stores detection records.
- **Spring Boot**: Provides a robust backend framework.

## System Architecture
```
┌───────────────┐      ┌───────────┐      ┌──────────────┐      ┌───────────┐
│ Edge Device   │ ---> │ gRPC API  │ ---> │ Kafka Topic  │ ---> │ Consumer  │
└───────────────┘      └───────────┘      └──────────────┘      └───────────┘
                                                                    │
                                                                    ▼
                                                         ┌─────────────────┐
                                                         │ PostgreSQL DB   │
                                                         └─────────────────┘
```
## Installation & Setup
### Prerequisites
- Java 17+
- Docker & Docker Compose
- PostgreSQL 15+
- Kafka & Zookeeper

### Running the Application
1. **Clone the Repository**
   ```sh
   git clone <repository-url>
   cd testForConbo
   ```

2. **Start Kafka & PostgreSQL with Docker Compose**
   ```sh
   docker-compose up -d
   ```

3. **Run the Application**
   ```sh
   mvn clean install
   mvn spring-boot:run
   ```

## API Usage
### gRPC Endpoint
#### `sendDetections`
- **Endpoint:** `/detection.DetectionIngestion/SendDetections`
- **Request Body:**
```json
{
  "source": "deviceA",
  "records": [
    {
      "uniqueId": "obj-11",
      "type": "car",
      "confidence": 0.95,
      "timestamp": "2025-02-24T12:34:56Z",
      "lat": 50.45,
      "lon": 30.523
    }
  ]
}
```
