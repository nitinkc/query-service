package com.nitin.microservices.query.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.data.neo4j.Neo4jDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients("com.nitin.microservices.query.currencyconversionservice")
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {
		"com.nitin.microservices.query.currencyconversionservice"
}, exclude = {
		ElasticsearchDataAutoConfiguration.class,
		ElasticsearchRepositoriesAutoConfiguration.class,
		DataSourceAutoConfiguration.class,
		HibernateJpaAutoConfiguration.class,
		SecurityAutoConfiguration.class,
		JmxAutoConfiguration.class,
		Neo4jDataAutoConfiguration.class})
public class CurrencyConversionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyConversionServiceApplication.class, args);
	}

//	@Bean
//	public Sampler defaultSampler(){
//		return Sampler.ALWAYS_SAMPLE;
//	}
}