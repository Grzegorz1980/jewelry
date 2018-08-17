package com.jewelry.core.db.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data

public class JewelImage {

    public JewelImage() {
    }

    public JewelImage(String imageLink) {
        this.imageLink = imageLink;
    }

    @Id
    @GeneratedValue
    private Long id;

    private String imageLink;

}
