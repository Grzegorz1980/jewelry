package com.jewelry.core.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor @AllArgsConstructor
public class SourceFileDTO implements Serializable {
    private Long id;

    private String name;

    private Instant importDate;

}
