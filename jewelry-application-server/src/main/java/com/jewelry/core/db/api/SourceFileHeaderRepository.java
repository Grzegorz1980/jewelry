package com.jewelry.core.db.api;

import com.jewelry.core.db.model.SourceFileHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SourceFileHeaderRepository extends JpaRepository<SourceFileHeader, Long> {
}
