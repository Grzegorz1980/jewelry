package com.jewelry.core.service.xls;

import com.jewelry.core.db.api.JewelryRepository;
import com.jewelry.core.db.model.Jewel;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class XLSServiceImpl implements XLSService {

    private static final String TAB1_NAME = "Kalkulacja";

    @Autowired
    private JewelryRepository jewelryRepository;

    @Override
    public byte[] generateXLS() {
        List<Jewel> jewels = jewelryRepository.findAll();

        XSSFWorkbook result = new XSSFWorkbook();
        CellStyle style = result.createCellStyle();
        style.setDataFormat(result.createDataFormat().getFormat("0.00%"));
        XSSFSheet resultSheet = result.createSheet(TAB1_NAME);
        int i = 2;
        for (Jewel jewel : jewels) {
            XSSFRow resultRow = resultSheet.createRow(i++);
            int cellIndex = 1;
            XSSFCell skuCell = resultRow.createCell(cellIndex++, CellType.STRING);
            skuCell.setCellValue(jewel.getSku());
            XSSFCell nameCell = resultRow.createCell(cellIndex++, CellType.STRING);
            nameCell.setCellValue(jewel.getName());
        };

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            result.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return outputStream.toByteArray();
    }
}
