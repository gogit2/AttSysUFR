package com.amoh;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableAutoConfiguration;
//@ComponentScan({"com.amoh.dao","com.amoh.controller"})
//@SpringBootApplication(scanBasePackages={"com.amoh.dao"})
//@SpringBootApplication
//@ComponentScan({"com.amoh.controller"})
//@EntityScan("com.amoh.entity")
//@EnableJpaRepositories("com.amoh.dao")
//@SpringBootApplication(scanBasePackages = {"com.amoh.controller"})
//@EnableJpaRepositories(basePackages = {"com.amoh.dao"})
//@EntityScan(basePackages =  {"com.amoh.entity"})

@SpringBootApplication(scanBasePackages = {"com.amoh"})
public class AttendanceProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(AttendanceProjectApplication.class, args);
	}

}
