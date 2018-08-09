package com.jewelry.core.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor @AllArgsConstructor
public class SourceFileDetailsDTO implements Serializable {
    private Long id;

    private String itemNumber;
    private BigDecimal price;
    private String imageLink;
}
