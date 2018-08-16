package com.jewelry.core.rest.mapper;

import com.jewelry.core.db.model.Jewel;
import com.jewelry.core.db.model.JewelImage;
import com.jewelry.core.rest.dto.JewelDTO;
import com.jewelry.core.rest.dto.JewelImageDTO;
import com.sun.scenario.effect.impl.sw.java.JSWInvertMaskPeer;
import org.hibernate.validator.constraints.URL;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface JewelryMapper {
    JewelryMapper INSTANCE = Mappers.getMapper(JewelryMapper.class);

    JewelDTO jewelToDTO(Jewel jewel);

    JewelImageDTO jewelImageToDTO(JewelImage jewelImage);

    @Mapping(target = "images", ignore = true)
    Jewel jewelFromDTO(JewelDTO jewelDTO);

}
