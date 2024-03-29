package employee_details;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableCircuitBreaker
public class EmployeeDetailsApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmployeeDetailsApplication.class, args);
    }}
