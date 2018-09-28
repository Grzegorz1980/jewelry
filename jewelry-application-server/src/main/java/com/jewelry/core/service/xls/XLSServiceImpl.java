package com.jewelry.core.service.xls;

import com.jewelry.core.db.api.JewelryRepository;
import com.jewelry.core.db.api.SettingsRepository;
import com.jewelry.core.db.model.Jewel;
import com.jewelry.core.db.model.Settings;
import com.jewelry.core.service.settings.SettingsService;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Service
public class XLSServiceImpl implements XLSService {

    private static final String TAB1_NAME = "Kalkulacja";

    public static final int COLUMN_JEW_NO = 1;
    public static final String COLUMN_JEW_NO_NAME = "Jew No";
    public static final int COLUMN_PRICE_USD = 2;
    public static final String COLUMN_PRICE_USD_NAME = "Koszt USD";
    public static final int COLUMN_RATE_USD = 3;
    public static final String COLUMN_RATE_USD_NAME = "PLN/USD";
    public static final int COLUMN_PRICE_PLN = 4;
    public static final String COLUMN_PRICE_PLN_NAME = "Koszt PLN";

    public static final int COLUMN_REGULAR_INCOME = 6;
    public static final String COLUMN_REGULAR_INCOME_NAME = "         Zysk";
    public static final int COLUMN_REGULAR_INCOME_PERC = 7;
    public static final String COLUMN_REGULAR_INCOME_PERC_NAME = "         Zysk%";
    public static final int COLUMN_REGULAR_PRICE_NO_VAT = 8;
    public static final String COLUMN_REGULAR_PRICE_NO_VAT_NAME = "Cena bez VAT";
    public static final int COLUMN_REGULAR_VAT = 9;
    public static final String COLUMN_REGULAR_VAT_NAME = "VAT 23%";
    public static final int COLUMN_REGULAR_PRICE = 10;
    public static final String COLUMN_REGULAR_PRICE_NAME = "Cena";
    public static final int COLUMN_REGULAR_SUGESTED_PRICE = 11;
    public static final String COLUMN_REGULAR_SUGESTED_PRICE_NAME = "Sugerowana cena";

    public static final int COLUMN_PROMO_INCOME = 13;
    public static final String COLUMN_PROMO_INCOME_NAME = "         Zysk";
    public static final int COLUMN_PROMO_INCOME_PERC = 14;
    public static final String COLUMN_PROMO_INCOME_PERC_NAME = "         Zysk%";
    public static final int COLUMN_PROMO_PRICE_NO_VAT = 15;
    public static final String COLUMN_PROMO_PRICE_NO_VAT_NAME = "Cena bez VAT";
    public static final int COLUMN_PROMO_VAT = 16;
    public static final String COLUMN_PROMO_VAT_NAME = "VAT 23%";
    public static final int COLUMN_PROMO_PRICE = 17;
    public static final String COLUMN_PROMO_PRICE_NAME = "Cena promocyjna";
    public static final int COLUMN_PROMO_SUGESTED_PRICE = 18;
    public static final String COLUMN_PROMO_SUGESTED_PRICE_NAME = "Sugerowana cena promocyjna";


    @Autowired
    private JewelryRepository jewelryRepository;

    @Autowired
    private SettingsRepository settingsRepository;

    @Override
    public byte[] generateXLS() {
        List<Jewel> jewels = jewelryRepository.findAll();
        Settings settings = settingsRepository.findByName(SettingsService.NAME);

        XSSFWorkbook result = new XSSFWorkbook();
        XSSFCellStyle style = result.createCellStyle();
        style.setDataFormat(result.createDataFormat().getFormat("0.00"));

        XSSFCellStyle jewelStyle = result.createCellStyle();
        XSSFColor xssfColor = new XSSFColor(new java.awt.Color(255, 242, 204));
        jewelStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        jewelStyle.setFillForegroundColor(xssfColor);

        XSSFCellStyle incomeStyle = result.createCellStyle();
        xssfColor = new XSSFColor(new java.awt.Color(169, 208, 142));
        incomeStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        incomeStyle.setFillForegroundColor(xssfColor);
        incomeStyle.setDataFormat(result.createDataFormat().getFormat("0.00"));

        XSSFCellStyle incomePercStyle = result.createCellStyle();
        xssfColor = new XSSFColor(new java.awt.Color(169, 208, 142));
        incomePercStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        incomePercStyle.setFillForegroundColor(xssfColor);
        incomePercStyle.setDataFormat(result.createDataFormat().getFormat("0.00%"));

        XSSFCellStyle priceStyle = result.createCellStyle();
        xssfColor = new XSSFColor(new java.awt.Color(255, 172, 157));
        priceStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        priceStyle.setFillForegroundColor(xssfColor);
        priceStyle.setDataFormat(result.createDataFormat().getFormat("0.00"));

        XSSFSheet resultSheet = result.createSheet(TAB1_NAME);
        createHeader(resultSheet.createRow(1), result);
        int i = 2;
        for (Jewel jewel : jewels) {
            XSSFRow resultRow = resultSheet.createRow(i);
            XSSFCell cell = resultRow.createCell(COLUMN_JEW_NO, CellType.STRING);
            cell.setCellValue(jewel.getSku());
            cell.setCellStyle(jewelStyle);
            cell = resultRow.createCell(COLUMN_PRICE_USD, CellType.NUMERIC);
            cell.setCellValue(jewel.getPriceUsd() != null ? jewel.getPriceUsd().doubleValue() : 0);
            cell.setCellStyle(style);
            cell = resultRow.createCell(COLUMN_RATE_USD, CellType.NUMERIC);
            cell.setCellValue(settings.getUsdRate().doubleValue());
            cell.setCellStyle(style);
            cell = resultRow.createCell(COLUMN_PRICE_PLN, CellType.FORMULA);
            cell.setCellFormula("C" + (i + 1) + "*D" + (i + 1));
            cell.setCellStyle(style);

            cell = resultRow.createCell(COLUMN_REGULAR_INCOME, CellType.FORMULA);
            cell.setCellFormula("I" + (i + 1) + "-E" + (i + 1));
            cell.setCellStyle(incomeStyle);
            cell = resultRow.createCell(COLUMN_REGULAR_INCOME_PERC, CellType.FORMULA);
            cell.setCellFormula("G" + (i + 1) + "/E" + (i + 1));
            cell.setCellStyle(incomePercStyle);
            cell = resultRow.createCell(COLUMN_REGULAR_PRICE_NO_VAT, CellType.FORMULA);
            cell.setCellFormula("K" + (i + 1) + "-J" + (i + 1));
            cell.setCellStyle(style);
            cell = resultRow.createCell(COLUMN_REGULAR_VAT, CellType.FORMULA);
            cell.setCellFormula("K" + (i + 1) + "/123*23");
            cell.setCellStyle(style);
            cell = resultRow.createCell(COLUMN_REGULAR_PRICE, CellType.NUMERIC);
            cell.setCellValue(jewel.getPriceEdited() != null ? jewel.getPriceEdited().doubleValue() : 0);
            cell.setCellStyle(priceStyle);
            cell = resultRow.createCell(COLUMN_REGULAR_SUGESTED_PRICE, CellType.FORMULA);
            cell.setCellFormula("E" + (i + 1) + "*" + (settings.getRegularRate().add(new BigDecimal(100)).divide(new BigDecimal(100))) + "*1.23");
            cell.setCellStyle(style);

            cell = resultRow.createCell(COLUMN_PROMO_INCOME, CellType.FORMULA);
            cell.setCellFormula("P" + (i + 1) + "-E" + (i + 1));
            cell.setCellStyle(incomeStyle);
            cell = resultRow.createCell(COLUMN_PROMO_INCOME_PERC, CellType.FORMULA);
            cell.setCellFormula("N" + (i + 1) + "/E" + (i + 1));
            cell.setCellStyle(incomePercStyle);
            cell = resultRow.createCell(COLUMN_PROMO_PRICE_NO_VAT, CellType.FORMULA);
            cell.setCellFormula("R" + (i + 1) + "-Q" + (i + 1));
            cell.setCellStyle(style);
            cell = resultRow.createCell(COLUMN_PROMO_VAT, CellType.FORMULA);
            cell.setCellFormula("R" + (i + 1) + "/123*23");
            cell.setCellStyle(style);
            cell = resultRow.createCell(COLUMN_PROMO_PRICE, CellType.NUMERIC);
            cell.setCellValue(jewel.getPromoPriceEdited() != null ? jewel.getPromoPriceEdited().doubleValue() : 0);
            cell.setCellStyle(priceStyle);
            cell = resultRow.createCell(COLUMN_PROMO_SUGESTED_PRICE, CellType.FORMULA);
            cell.setCellFormula("E" + (i + 1) + "*" + (settings.getPromoRate().add(new BigDecimal(100)).divide(new BigDecimal(100))) + "*1.23");
            cell.setCellStyle(style);
            i++;
        }
        ;

        for (int columnIndex = 1; columnIndex < 20; columnIndex++) {
            resultSheet.autoSizeColumn(columnIndex);
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            result.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return outputStream.toByteArray();
    }

    private void createHeader(XSSFRow row, XSSFWorkbook result) {
        XSSFFont defaultFont = result.createFont();
        defaultFont.setBold(true);

        XSSFCellStyle style = result.createCellStyle();
        style.setFont(defaultFont);

        XSSFCellStyle jewelStyle = result.createCellStyle();
        XSSFColor xssfColor = new XSSFColor(new java.awt.Color(255, 242, 204));
        jewelStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        jewelStyle.setFillForegroundColor(xssfColor);
        jewelStyle.setFont(defaultFont);

        XSSFCellStyle incomeStyle = result.createCellStyle();
        xssfColor = new XSSFColor(new java.awt.Color(169, 208, 142));
        incomeStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        incomeStyle.setFillForegroundColor(xssfColor);
        incomeStyle.setFont(defaultFont);

        XSSFCellStyle priceStyle = result.createCellStyle();
        xssfColor = new XSSFColor(new java.awt.Color(255, 172, 157));
        priceStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        priceStyle.setFillForegroundColor(xssfColor);
        priceStyle.setFont(defaultFont);

        XSSFCell cell = row.createCell(COLUMN_JEW_NO, CellType.STRING);
        cell.setCellValue(COLUMN_JEW_NO_NAME);
        cell.setCellStyle(jewelStyle);
        cell = row.createCell(COLUMN_PRICE_USD, CellType.STRING);
        cell.setCellValue(COLUMN_PRICE_USD_NAME);
        cell.setCellStyle(style);
        cell = row.createCell(COLUMN_RATE_USD, CellType.STRING);
        cell.setCellValue(COLUMN_RATE_USD_NAME);
        cell.setCellStyle(style);
        cell = row.createCell(COLUMN_PRICE_PLN, CellType.STRING);
        cell.setCellValue(COLUMN_PRICE_PLN_NAME);
        cell.setCellStyle(style);

        cell = row.createCell(COLUMN_REGULAR_INCOME, CellType.STRING);
        cell.setCellValue(COLUMN_REGULAR_INCOME_NAME);
        cell.setCellStyle(incomeStyle);
        cell = row.createCell(COLUMN_REGULAR_INCOME_PERC, CellType.STRING);
        cell.setCellValue(COLUMN_REGULAR_INCOME_PERC_NAME);
        cell.setCellStyle(incomeStyle);
        cell = row.createCell(COLUMN_REGULAR_PRICE_NO_VAT, CellType.STRING);
        cell.setCellValue(COLUMN_REGULAR_PRICE_NO_VAT_NAME);
        cell.setCellStyle(style);
        cell = row.createCell(COLUMN_REGULAR_VAT, CellType.STRING);
        cell.setCellValue(COLUMN_REGULAR_VAT_NAME);
        cell.setCellStyle(style);
        cell = row.createCell(COLUMN_REGULAR_PRICE, CellType.STRING);
        cell.setCellValue(COLUMN_REGULAR_PRICE_NAME);
        cell.setCellStyle(priceStyle);
        cell = row.createCell(COLUMN_REGULAR_SUGESTED_PRICE, CellType.STRING);
        cell.setCellValue(COLUMN_REGULAR_SUGESTED_PRICE_NAME);
        cell.setCellStyle(style);

        cell = row.createCell(COLUMN_PROMO_INCOME, CellType.STRING);
        cell.setCellValue(COLUMN_PROMO_INCOME_NAME);
        cell.setCellStyle(incomeStyle);
        cell = row.createCell(COLUMN_PROMO_INCOME_PERC, CellType.STRING);
        cell.setCellValue(COLUMN_PROMO_INCOME_PERC_NAME);
        cell.setCellStyle(incomeStyle);
        cell = row.createCell(COLUMN_PROMO_PRICE_NO_VAT, CellType.STRING);
        cell.setCellValue(COLUMN_PROMO_PRICE_NO_VAT_NAME);
        cell.setCellStyle(style);
        cell = row.createCell(COLUMN_PROMO_VAT, CellType.STRING);
        cell.setCellValue(COLUMN_PROMO_VAT_NAME);
        cell.setCellStyle(style);
        cell = row.createCell(COLUMN_PROMO_PRICE, CellType.STRING);
        cell.setCellValue(COLUMN_PROMO_PRICE_NAME);
        cell.setCellStyle(priceStyle);
        cell = row.createCell(COLUMN_PROMO_SUGESTED_PRICE, CellType.STRING);
        cell.setCellValue(COLUMN_PROMO_SUGESTED_PRICE_NAME);
        cell.setCellStyle(style);
    }
}
