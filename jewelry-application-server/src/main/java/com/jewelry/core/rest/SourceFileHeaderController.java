package com.jewelry.core.rest;

import com.jewelry.core.db.api.SourceFileHeaderRepository;
import com.jewelry.core.rest.dto.SourceFileHeaderDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SourceFileHeaderController {

    @Autowired
    private SourceFileHeaderRepository fileHeaderRepository;

    @GetMapping("/source-files")
    public Collection<SourceFileHeaderDTO> sourceFiles() {
        return fileHeaderRepository.findAll().stream()
                .map(fileHeaderRepository -> {
                    SourceFileHeaderDTO result = new SourceFileHeaderDTO();
                    BeanUtils.copyProperties(fileHeaderRepository, result);
                    return result;
                })
                .collect(Collectors.toList());
    }
}
