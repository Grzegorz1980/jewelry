package com.jewelry.core.db.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
@Data
public class SourceFileDetails {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private SourceFile parent;
    private String itemNumber;
    private BigDecimal price;
    private String imageLink;
}
