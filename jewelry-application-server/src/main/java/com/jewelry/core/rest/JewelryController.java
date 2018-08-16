package com.jewelry.core.rest;

import com.jewelry.core.db.model.Jewel;
import com.jewelry.core.rest.dto.JewelDTO;
import com.jewelry.core.rest.mapper.JewelryMapper;
import com.jewelry.core.service.JewelryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class JewelryController {

    @Autowired
    private JewelryService jewelryService;

    @Autowired
    private JewelryMapper mapper;

    @GetMapping("/jewelry/list")
    public Collection<JewelDTO> getJewelry() {
        return jewelryService.getJewelry().stream()
                .map(jewel -> {return mapper.jewelToDTO(jewel);})
                .collect(Collectors.toList());
    }

    @GetMapping("/jewelry/{id}")
    public JewelDTO getJewel(@PathVariable Long id) {
        Jewel jewel = jewelryService.getJewel(id);
        return mapper.jewelToDTO(jewel);
    }
}