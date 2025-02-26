package org.example.testforconbo.repositories;

import org.example.testforconbo.entities.DetectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DetectionRepository extends JpaRepository<DetectionEntity, UUID> {
}
