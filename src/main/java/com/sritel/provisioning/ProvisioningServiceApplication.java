package com.sritel.provisioning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ProvisioningServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProvisioningServiceApplication.class, args);
	}

}
