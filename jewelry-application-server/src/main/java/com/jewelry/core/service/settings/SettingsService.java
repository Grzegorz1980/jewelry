package com.jewelry.core.service.settings;

import com.jewelry.core.db.api.SettingsRepository;
import com.jewelry.core.db.model.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingsService {

    public static final String NAME = "Globals";

    @Autowired
    private SettingsRepository settingsRepository;

    public Settings getSettings() {
        return settingsRepository.findByName(NAME);
    }

    public void updateSettings(Settings newSettings) {
        Settings settings = settingsRepository.findByName(NAME);
        settings.setPromoRate(newSettings.getPromoRate());
        settings.setRegularRate(newSettings.getRegularRate());
        settings.setUsdRate(newSettings.getUsdRate());
        settingsRepository.save(settings);
    }
}
