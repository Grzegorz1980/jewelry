package com.jewelry.core.rest;

import com.jewelry.core.db.model.Jewel;
import com.jewelry.core.rest.dto.JewelDTO;
import com.jewelry.core.rest.dto.ServerResponse;
import com.jewelry.core.rest.dto.ServerResponseDTO;
import com.jewelry.core.rest.dto.UserDTO;
import com.jewelry.core.service.jewelry.JewelryService;
import com.jewelry.core.util.mapper.JewelryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost"})
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private JewelryService jewelryService;

    @Autowired
    private JewelryMapper mapper;

    @PostMapping("/login")
    public ServerResponseDTO getJewelry(@RequestBody UserDTO user) {
        logger.info("Login. Username=" + user.getUsername());
        if (!user.getUsername().equals("test")) {
            return new ServerResponseDTO(ServerResponse.ERROR, "");
        }
        return new ServerResponseDTO(ServerResponse.OK, "");
    }
}
