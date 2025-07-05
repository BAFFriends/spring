package com.inner_medicine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class InnerMedicineApplication {

	public static void main(String[] args) {
		SpringApplication.run(InnerMedicineApplication.class, args);
	}

}
