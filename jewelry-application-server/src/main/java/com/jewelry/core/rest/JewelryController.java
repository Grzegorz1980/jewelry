package com.jewelry.core.rest;

import com.jewelry.core.db.model.Jewel;
import com.jewelry.core.rest.dto.JewelDTO;
import com.jewelry.core.rest.dto.ServerResponse;
import com.jewelry.core.rest.dto.ServerResponseDTO;
import com.jewelry.core.util.mapper.JewelryMapper;
import com.jewelry.core.service.jewelry.JewelryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class JewelryController {

    private static final Logger logger = LoggerFactory.getLogger(JewelryController.class);

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

    @PostMapping("/jewelry")
    public ServerResponseDTO saveJewel(@RequestBody JewelDTO jewelDTO) {
        logger.info("GOT POST. Jewel SKU=" + jewelDTO.getSku());
        Jewel jewel = mapper.jewelFromDTO(jewelDTO);
        jewelryService.saveJewel(jewel);
        return new ServerResponseDTO(ServerResponse.OK, "OK");
    }

}
