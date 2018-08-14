package com.jewelry.core.rest;

import com.jewelry.core.rest.dto.FileUploadResponseDTO;
import com.jewelry.core.rest.dto.ServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class FileUploadController {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @PostMapping("/uploadFile")
    public FileUploadResponseDTO uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            logger.info("Uploaded fileName=" + file.getName());
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            logger.info("First line = " + reader.readLine());
            return new FileUploadResponseDTO(ServerResponse.OK, file.getName());
        } catch (IOException e) {
            return new FileUploadResponseDTO(ServerResponse.ERROR, e.getMessage());
        }
    }
}
