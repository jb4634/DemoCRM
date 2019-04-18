package com.podjetje.democrm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemocrmApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemocrmApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner loadData(ConclusionTypeRepository conclusionTypeRepository) {
//        return (args) -> {
//            // Save 3 possible types of Conclusions
//            conclusionTypeRepository.save(new ConclusionType("Sestanek"));
//            conclusionTypeRepository.save(new ConclusionType("Pogodba"));
//            conclusionTypeRepository.save(new ConclusionType("Račun"));
//        };
//    }
}
