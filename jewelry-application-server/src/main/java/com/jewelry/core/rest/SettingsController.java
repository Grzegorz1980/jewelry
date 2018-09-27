package com.jewelry.core.rest;

import com.jewelry.core.db.model.Settings;
import com.jewelry.core.rest.dto.ServerResponse;
import com.jewelry.core.rest.dto.ServerResponseDTO;
import com.jewelry.core.rest.dto.SettingsDTO;
import com.jewelry.core.service.settings.SettingsService;
import com.jewelry.core.util.mapper.GeneralMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SettingsController {

    private static final Logger logger = LoggerFactory.getLogger(SettingsController.class);

    @Autowired
    private GeneralMapper mapper;

    @Autowired
    private SettingsService settingsService;

    @GetMapping("/settings")
    public SettingsDTO getSettings() {
        Settings settings = settingsService.getSettings();
        return mapper.settingsToDTO(settings);
    }

    @PostMapping("/settings")
    public ServerResponseDTO updateSettings(@RequestBody SettingsDTO settings) {
        settingsService.updateSettings(mapper.settingsFromDTO(settings));
        return new ServerResponseDTO(ServerResponse.OK, "OK");
    }
}
