package com.jewelry.core.rest;

import com.jewelry.core.db.model.Jewel;
import com.jewelry.core.rest.dto.JewelDTO;
import com.jewelry.core.rest.dto.ServerResponse;
import com.jewelry.core.rest.dto.ServerResponseDTO;
import com.jewelry.core.service.jewelry.JewelryService;
import com.jewelry.core.service.security.UserService;
import com.jewelry.core.util.mapper.JewelryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/user/{userId}/password")
    public ServerResponseDTO updatePassword(@PathVariable String userId, @RequestBody String password) {
        userService.updatePassword(userId, password);
        return new ServerResponseDTO(ServerResponse.OK, "OK");
    }
}
