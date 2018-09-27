package com.jewelry.core.service.importFile;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

import java.math.BigDecimal;

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

    @CsvBindByName(column = "Krótki opis")
    private String shortDescription;

    @CsvBindByName(column = "Opis")
    private String description;

    @CsvBindByName(column = "Klasa podatkowa")
    private String taxClass;

    @CsvBindByName(column = "W magazynie?")
    private String inStorage;

    @CsvBindByName(column = "Magazyn")
    private String storage;

    @CsvBindByName(column = "Waga (kg)")
    private BigDecimal weight;

    @CsvBindByName(column = "Długość (cm)")
    private BigDecimal length;

    @CsvBindByName(column = "Szerokość (cm)")
    private BigDecimal width;

    @CsvBindByName(column = "Wysokość (cm)")
    private BigDecimal height;

    @CsvBindByName(column = "Kategorie")
    private String category;

    @CsvBindByName(column = "Tagi")
    private String tags;

    @CsvBindByName(column = "Obrazki")
    private String images;

    @CsvBindByName(column = "Nazwa atrybutu 1")
    private String attribute1Name;

    @CsvBindByName(column = "Wartości atrybutu 1")
    private String attribute1Value;

}

