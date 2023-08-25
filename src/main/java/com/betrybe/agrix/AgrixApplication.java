package com.betrybe.agrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Application main class.
 */
@SpringBootApplication
@EntityScan("com.betrybe.agrix")
@EnableJpaRepositories("com.betrybe.agrix")
@ComponentScan("com.betrybe.agrix")
public class AgrixApplication {

  public static void main(String[] args) {
    String activeProfile = "dev";
    System.setProperty("spring.profile.active", activeProfile);
    SpringApplication.run(AgrixApplication.class, args);
  }

}
