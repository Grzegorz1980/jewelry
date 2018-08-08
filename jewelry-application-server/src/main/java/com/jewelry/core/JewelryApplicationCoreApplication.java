package com.jewelry.core;

import com.jewelry.core.db.api.SourceFileHeaderRepository;
import com.jewelry.core.db.model.SourceFileHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

import java.time.Instant;

@SpringBootApplication
public class JewelryApplicationCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(JewelryApplicationCoreApplication.class, args);
	}
}
