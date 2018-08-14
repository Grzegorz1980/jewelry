package com.jewelry.core.service;

import com.jewelry.core.db.api.JewelryRepository;
import com.jewelry.core.db.model.Jewel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class JewelryService {

    @Autowired
    private JewelryRepository jewelryRepository;

    @Transactional
    public List<Jewel> getJewelry() {
        return jewelryRepository.findAll();
    }

    @Transactional
    public Jewel getJewel(Long id) {
        return jewelryRepository.getOne(id);
    }
}
