package com.krukovska.springdbcourse.persistence.repository;

import com.krukovska.springdbcourse.persistence.model.Storage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StorageRepository extends JpaRepository<Storage, Long> {
}
