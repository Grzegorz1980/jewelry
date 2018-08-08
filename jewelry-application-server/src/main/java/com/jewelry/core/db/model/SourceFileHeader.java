package com.jewelry.core.db.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
public class SourceFileHeader implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Instant importDate;
}
