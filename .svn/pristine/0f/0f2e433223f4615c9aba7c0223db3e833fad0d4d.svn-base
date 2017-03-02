package com.hhh.fund.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.hhh.fund.util.LogUploadClient;


@Configuration
public class LogUploadConfig {

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("com.hhh.opsservice.wsdl");
		return marshaller;
	}

	@Bean
	public LogUploadClient logClient(Jaxb2Marshaller marshaller) {
		LogUploadClient client = new LogUploadClient();
		client.setDefaultUri("http://webservice.ops.platform.hhh.com/");
		//opsService URL
		client.setOpsService("http://192.168.2.42:10003/opsService/opsService?wsdl");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
}
