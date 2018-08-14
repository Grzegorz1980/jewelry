package com.jewelry.core.db.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Jewel {

    @Id
    @GeneratedValue
    private Long id;

    private Integer businessId;
    private String type;
    private String sku;
    private String name;

    @OneToMany(mappedBy = "jewelId")
    private List<JewelImage> images;
}
