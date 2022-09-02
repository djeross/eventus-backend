package com.eventusapp.eventus;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication //(exclude = {DataSourceAutoConfiguration.class })
public class EventusApplication {
	public static void main(String[] args) throws UnknownHostException {
		SpringApplication.run(EventusApplication.class, args);

		//RestTemplate template =new RestTemplate();
		//HttpHeaders headers = new HttpHeaders();


	}
	//spring.datasource.url=jdbc:h2:file:/data/eventus-store
}
