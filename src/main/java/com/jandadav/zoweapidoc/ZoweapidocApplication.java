package com.jandadav.zoweapidoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
@EnableOpenApi
public class ZoweapidocApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZoweapidocApplication.class, args);
	}

	@Bean
	public Docket defaultApi() {
		return new Docket(DocumentationType.OAS_30)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}

	@Bean
	public Docket publicApi() {
		return buildDocket("publicapi", "/greeting");
	}
	@Bean
	public Docket userApi() {
		return buildDocket("userapi", "/user");
	}
	@Bean
	public Docket adminApi() {
		return buildDocket("adminapi", "/admin");
	}

	private Docket buildDocket(String group, String pattern) {
		return new Docket(DocumentationType.OAS_30)
				.groupName(group)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.ant(pattern))
				.build();
	}
}
