package com.azienda.sweetter.foundation;

import com.azienda.sweetter.model.entity.Post;
import com.azienda.sweetter.model.entity.Role;
import com.azienda.sweetter.model.entity.User;
import com.azienda.sweetter.model.enums.Roles;
import com.azienda.sweetter.service.ServiceManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.sql.Date;

@SpringBootApplication(scanBasePackages = "com.azienda.sweetter")
@EnableJpaRepositories(basePackages = "com.azienda.sweetter.repository")
@EntityScan(basePackages = "com.azienda.sweetter.model.entity")
public class SwetterApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(SwetterApplication.class, args);
        configurableApplicationContext.getBean("ServiceManager", ServiceManager.class);
        //User admin = new User("ghinoads", "pippo", "gmail", "giulio", "dalbono", new Date(2002, 03, 17), "italiana", true, new Role(Roles.ADMIN));
    }

}
