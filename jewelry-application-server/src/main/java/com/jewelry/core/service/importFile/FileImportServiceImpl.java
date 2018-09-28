package com.jewelry.core.service.importFile;

import com.jewelry.core.db.api.JewelryRepository;
import com.jewelry.core.db.model.Jewel;
import com.jewelry.core.db.model.JewelImage;
import com.jewelry.core.util.mapper.GeneralMapper;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.apache.commons.io.input.BOMInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class FileImportServiceImpl implements FileImportService {

    public static final Logger logger = LoggerFactory.getLogger(FileImportServiceImpl.class);

    public static final String SKU = "SKU";
    private static final String IMAGE_SEPARATOR = ",";

    @Value("${skipEmptySKU:false}")
    private boolean skipEmptySku;

    private List<Integer> skippedRows = new ArrayList<>();

    @Autowired
    private JewelryRepository jewelryRepository;

    @Autowired
    private GeneralMapper mapper;

    @Override
    @Transactional
    public void importFile(InputStream inputStream) {

        CsvToBean<CSVJewel> csvToBean = new CsvToBeanBuilder(new InputStreamReader(new BOMInputStream(inputStream)))
                .withType(CSVJewel.class)
                .withIgnoreLeadingWhiteSpace(true)
                .withSeparator(';')
                .build();
        Iterator<CSVJewel> iterator = csvToBean.iterator();
        List<Jewel> jewelsToSave = new ArrayList<>();
        while (iterator.hasNext()) {
            CSVJewel csvJewel = iterator.next();
            logger.info("Adding jewel. SKU={}, Name={}", csvJewel.getSku(), csvJewel.getName());

            if (csvJewel.getSku() == null) {
                skippedRows.add(csvJewel.getBusinessId());
            } else {
                Jewel jewel = jewelryRepository.findBySku(csvJewel.getSku());
                if (jewel == null) {
                    jewelsToSave.add(convertNewCsvJewel(csvJewel));
                } else {
                    jewelsToSave.add(updateJewel(jewel, csvJewel));
                }
            }
        }

        if (!skippedRows.isEmpty() && !skipEmptySku) {
            throw new FileImportException("Not all rows contains SKU", skippedRows);
        } else {
            jewelsToSave.forEach(jewel -> logger.info("Saving jewel. SKU={}, Name={}", jewel.getSku(), jewel.getName()));
            jewelryRepository.saveAll(jewelsToSave);
        }
    }

    private Jewel convertNewCsvJewel(CSVJewel csvJewel) {
        Jewel jewel = mapper.fromCSVJewel(csvJewel);
        jewel.getImages().addAll(createImages(csvJewel.getImages()));
        if (jewel.getShortDescription() != null && jewel.getShortDescription().length() > Jewel.GLOBAL_LENGTH) {
            jewel.setShortDescription(jewel.getShortDescription().substring(0, Jewel.GLOBAL_LENGTH - 1));
        }
        if (jewel.getDescription() != null && jewel.getDescription().length() > Jewel.GLOBAL_LENGTH) {
            jewel.setDescription(jewel.getDescription().substring(0, Jewel.GLOBAL_LENGTH - 1));
        }
        return jewel;
    }

    private List<JewelImage> createImages(String images) {
        List<JewelImage> result = new ArrayList<>();
        if (images != null) {
            String[] splitted = images.split(IMAGE_SEPARATOR);
            for (String image : splitted) {
                result.add(new JewelImage(image.trim()));
            }
        }
        return result;
    }

    private Jewel updateJewel(Jewel jewel, CSVJewel csvJewel) {
        jewel.setBusinessId(csvJewel.getBusinessId());
        jewel.setName(csvJewel.getName());
        jewel.setType(csvJewel.getType());
        jewel.setSku(csvJewel.getSku());
        if (csvJewel.getShortDescription() != null) {
            String shortDescription = csvJewel.getShortDescription().trim();
            if (shortDescription.length() > Jewel.GLOBAL_LENGTH) {
                shortDescription = shortDescription.substring(0, Jewel.GLOBAL_LENGTH - 1);
            }
            jewel.setShortDescription(shortDescription);
        }
        if (csvJewel.getDescription() != null) {
            String description = csvJewel.getDescription().trim();
            if (description.length() > Jewel.GLOBAL_LENGTH) {
                description = description.substring(0, Jewel.GLOBAL_LENGTH - 1);
            }
            jewel.setDescription(description);
        }
        jewel.setTaxClass(csvJewel.getTaxClass());
        jewel.setInStorage(csvJewel.getInStorage());
        jewel.setStorage(csvJewel.getStorage());
        jewel.setWeight(csvJewel.getWeight());
        jewel.setLength(csvJewel.getLength());
        jewel.setWidth(csvJewel.getWidth());
        jewel.setHeight(csvJewel.getHeight());
        jewel.setCategory(csvJewel.getCategory());
        jewel.setTags(csvJewel.getTags());
        jewel.setAttribute1Name(csvJewel.getAttribute1Name());
        jewel.setAttribute1Value(csvJewel.getAttribute1Value());

        jewel.getImages().clear();
        jewel.getImages().addAll(createImages(csvJewel.getImages()));
        return jewel;
    }
}
