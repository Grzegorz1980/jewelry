package com.jewelry.core.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JewelDTO implements Serializable {

    private Integer businessId;
    private String type;
    private String sku;
    private String name;
    private List<String> images;
}
