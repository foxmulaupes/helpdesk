package com.javahelps.mysqlrestservice;
import com.javahelps.mysqlrestservice.Repository.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@ComponentScan({"com.javahelps.mysqlrestservice"})
@EntityScan("com.javahelps.mysqlrestservice")
@EnableJpaRepositories(basePackageClasses=Repositorydata.class)
@SpringBootApplication
public class MysqlRestServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MysqlRestServiceApplication.class, args);
		    }
	}


