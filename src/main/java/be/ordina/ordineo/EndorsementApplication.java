package be.ordina.ordineo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.hateoas.config.EnableEntityLinks;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableEntityLinks
@EnableFeignClients
@EnableEurekaClient
@EnableRetry
//@EnableHypermediaSupport(type = HAL)
public class EndorsementApplication {

	public static void main(String[] args) {
		SpringApplication.run(EndorsementApplication.class, args);
	}
}
