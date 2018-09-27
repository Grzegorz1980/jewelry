package com.jewelry.core.util.mapper;

import com.jewelry.core.db.model.Jewel;
import com.jewelry.core.db.model.JewelImage;
import com.jewelry.core.db.model.Settings;
import com.jewelry.core.rest.dto.JewelDTO;
import com.jewelry.core.rest.dto.JewelImageDTO;
import com.jewelry.core.rest.dto.SettingsDTO;
import com.jewelry.core.service.importFile.CSVJewel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface GeneralMapper {
    GeneralMapper INSTANCE = Mappers.getMapper(GeneralMapper.class);

    JewelDTO jewelToDTO(Jewel jewel);

    JewelImageDTO jewelImageToDTO(JewelImage jewelImage);

    @Mapping(target = "images", ignore = true)
    Jewel jewelFromDTO(JewelDTO jewelDTO);

    @Mapping(target = "images", ignore = true)
    Jewel fromCSVJewel(CSVJewel csvJewel);

    SettingsDTO settingsToDTO(Settings settings);

    Settings settingsFromDTO(SettingsDTO settingsDTO);
}