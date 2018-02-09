package demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@link DashboardApplication} is a cloud-native Spring Boot application that manages
 * a bounded context for @{link Customer}, @{link Account}, @{link CreditCard}, and @{link Address}
 *
 * @author Kenny Bastani
 * @author Josh Long
 */
@SpringBootApplication
@EnableEurekaClient
@RestController
public class DashboardApplication {
    @Value("${echo}")
    String echo;

    @RequestMapping(value = "/echo")
    public String echo(){
        return echo;
    }

    public static void main(String[] args) {
        SpringApplication.run(DashboardApplication.class, args);
    }

}
