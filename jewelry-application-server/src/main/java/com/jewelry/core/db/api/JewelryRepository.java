package com.jewelry.core.db.api;

import com.jewelry.core.db.model.Jewel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


@Component
public interface JewelryRepository extends JpaRepository<Jewel, Long> {

    public Jewel findBySku(String sku);
}
