package com.jewelry.core.db.api;

import com.jewelry.core.db.model.Settings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


@Component
public interface SettingsRepository extends JpaRepository<Settings, Long> {

    public Settings findByName(String name);
}
