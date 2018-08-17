package com.jewelry.core.rest;

import com.jewelry.core.rest.dto.ServerResponseDTO;
import com.jewelry.core.rest.dto.ServerResponse;
import com.jewelry.core.service.importFile.FileImportException;
import com.jewelry.core.service.importFile.FileImportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class FileUploadController {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @Autowired
    private FileImportService fileImportService;

    @PostMapping("/uploadFile")
    public ServerResponseDTO uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            logger.info("Uploaded fileName=" + file.getOriginalFilename());
            fileImportService.importFile(file.getInputStream());
            return new ServerResponseDTO(ServerResponse.OK, "Załadowany plik:" + file.getOriginalFilename());
        } catch (FileImportException e) {
            String message = "Brak SKU w pozycjach:" + Arrays.toString(e.getSkippedSku().toArray());
            logger.error(message);
            return new ServerResponseDTO(ServerResponse.ERROR, message);
        } catch (Exception e) {
            logger.error("General error.", e);
            return new ServerResponseDTO(ServerResponse.ERROR, e.getMessage());
        }
    }
}
