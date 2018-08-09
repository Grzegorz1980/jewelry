package com.jewelry.core.service;

import com.jewelry.core.db.api.SourceFileRepository;
import com.jewelry.core.db.model.SourceFile;
import com.jewelry.core.db.model.SourceFileDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SourceFileService {

    @Autowired
    private SourceFileRepository sourceFileRepository;

    @Transactional
    public List<SourceFile> getAllFiles() {
        return sourceFileRepository.findAll();
    }

    @Transactional
    public List<SourceFileDetails> getFileDetails(Long id) {
        return sourceFileRepository.getOne(id).getDetailsList();
    }
}
