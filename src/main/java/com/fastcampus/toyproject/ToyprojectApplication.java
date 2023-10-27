package com.fastcampus.toyproject;

import com.fastcampus.toyproject.common.util.LocationUtil;
import com.fastcampus.toyproject.common.util.api.GoogleApiProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@ConfigurationPropertiesScan(basePackages = {"com.fastcampus.toyproject.common.util.api"})
public class ToyprojectApplication {
	public static void main(String[] args) {
		SpringApplication.run(ToyprojectApplication.class, args);

	}

}
