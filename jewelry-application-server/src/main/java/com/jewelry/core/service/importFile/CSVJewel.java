package com.jewelry.core.service.importFile;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class CSVJewel {

    @CsvBindByName(column = "ID")
    private Integer businessId;

    @CsvBindByName(column = "Typ")
    private String type;

    @CsvBindByName(column = "SKU")
    private String sku;

    @CsvBindByName(column = "Nazwa")
    private String name;

    @CsvBindByName(column = "Obrazki")
    private String images;

}
