package com.jewelry.core.db.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
public class SourceFile implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Instant importDate;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SourceFileDetails> detailsList;
}
