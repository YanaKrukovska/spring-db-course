package com.krukovska.task1.persistence.repository;

import com.krukovska.task1.persistence.model.Storage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StorageRepository extends JpaRepository<Storage, Long> {
}
