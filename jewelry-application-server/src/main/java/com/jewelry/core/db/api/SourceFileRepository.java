package com.jewelry.core.db.api;

import com.jewelry.core.db.model.SourceFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


public interface SourceFileRepository extends JpaRepository<SourceFile, Long> {
}
