package com.jewelry.core.rest;

import com.jewelry.core.rest.dto.SourceFileDTO;
import com.jewelry.core.rest.dto.SourceFileDetailsDTO;
import com.jewelry.core.service.SourceFileService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SourceFileController {

    @Autowired
    private SourceFileService sourceFileService;

    @GetMapping("/source-files")
    public Collection<SourceFileDTO> getSourceFiles() {
        return sourceFileService.getAllFiles().stream()
                .map(sourceFile -> {
                    SourceFileDTO result = new SourceFileDTO();
                    BeanUtils.copyProperties(sourceFile, result);
                    return result;
                })
                .collect(Collectors.toList());
    }

    @GetMapping("/source-files/{id}")
    public Collection<SourceFileDetailsDTO> getFileDetails(@PathVariable Long id) {
        return sourceFileService.getFileDetails(id).stream()
                .map(sourceFileDetails -> {
                    SourceFileDetailsDTO result = new SourceFileDetailsDTO();
                    BeanUtils.copyProperties(sourceFileDetails, result);
                    return result;
                })
                .collect(Collectors.toList());
    }
}
