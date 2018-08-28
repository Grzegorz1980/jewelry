package com.jewelry.core.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JewelDTO implements Serializable {

    private Integer businessId;
    private String type;
    private String sku;
    private String name;
    private String nameEdited;

    private String shortDescription;
    private String shortDescriptionEdited;
    private String description;
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

    private List<JewelImageDTO> images;
}
