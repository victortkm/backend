package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@SpringBootApplication
@EnableAutoConfiguration
public class DemoApplication {
    
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Data
	static class FilePathData {
		private String inputPath;
		private String logPath;
	}

	@Component
	@ConfigurationProperties(prefix = "filepath.config")
	public static class FilePathConfig extends FilePathData {
	}
	
	@Data
	static class BirtDatasourceData {
		private String url;
		private String username;
		private String password;
	}
	
	@Component
	@ConfigurationProperties(prefix = "spring.datasource")
	public static class BirtDatasourceConfig extends BirtDatasourceData {
		
	}
	
}
