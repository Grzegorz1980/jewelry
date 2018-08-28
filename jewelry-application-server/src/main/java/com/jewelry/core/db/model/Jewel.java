package com.jewelry.core.db.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.ArrayList;
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
    @Column(length = 32, unique = true)
    private String sku;
    private String name;
    private String nameEdited;

    @Column(length = 3072)
    private String shortDescription;
    @Column(length = 3072)
    private String shortDescriptionEdited;
    @Column(length = 3072)
    private String description;
    @Column(length = 3072)
    private String descriptionEdited;
    private String taxClass;
    private String inStorage;
    private String storage;
    private BigDecimal weight;
    private BigDecimal weightEdited;
    private BigDecimal length;
    private BigDecimal lengthEdited;
    private BigDecimal width;
    private BigDecimal widthEdited;
    private BigDecimal height;
    private BigDecimal heightEdited;
    private BigDecimal promoPrice;
    private BigDecimal promoPriceEdited;
    private BigDecimal price;
    private BigDecimal priceEdited;
    private String category;
    private String categoryEdited;
    private String tags;
    private String tagsEdited;
    private String attribute1Name;
    private String attribute1Value;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "jewelId")
    private List<JewelImage> images = new ArrayList<>();
}
