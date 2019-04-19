package com.podjetje.democrm;

import com.podjetje.democrm.entity.Conclusion;
import com.podjetje.democrm.entity.ConclusionType;
import com.podjetje.democrm.entity.Customer;
import com.podjetje.democrm.entity.Meeting;
import com.podjetje.democrm.repository.ConclusionRepository;
import com.podjetje.democrm.repository.ConclusionTypeRepository;
import com.podjetje.democrm.repository.CustomerRepository;
import com.podjetje.democrm.repository.MeetingRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@SpringBootApplication
public class DemocrmApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemocrmApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(ConclusionTypeRepository conclusionTypeRepository,
                                      ConclusionRepository conclusionRepository,
                                      CustomerRepository customerRepository, MeetingRepository meetingRepository) {
        return (args) -> {
            /* Only run the first time to fill the database with data */
          //  fillTheDatabase(conclusionTypeRepository, customerRepository, meetingRepository, conclusionRepository);

        };
    }

    private void fillTheDatabase(ConclusionTypeRepository conclusionTypeRepository,
                                 CustomerRepository customerRepository, MeetingRepository meetingRepository,
                                 ConclusionRepository conclusionRepository){

        System.out.println("Creating data in database");

        // Create 3 possible types of Conclusions */
        conclusionTypeRepository.save(new ConclusionType("Sestanek"));
        conclusionTypeRepository.save(new ConclusionType("Pogodba"));
        conclusionTypeRepository.save(new ConclusionType("Račun"));

        System.out.println("Creating Customers");
        // Create customers
        customerRepository.save(new Customer("Janez", "Novak","jannov@gm.com",
                "031000000"));
        customerRepository.save(new Customer("Miran", "Novak","mirnov@gm.com",
                "031000001"));
        customerRepository.save(new Customer("Maja", "Kuhar","maku@gm.com",
                "031000002"));
        customerRepository.save(new Customer("Mirjam", "Sapnik","mirsa@gm.com",
                "031000003"));
        customerRepository.save(new Customer("Janez", "Sapnik","jansa@gm.com",
                "031000004"));
        customerRepository.save(new Customer("Janez", "Grohar","jangr@gm.com",
                "031000005"));

        System.out.println("Creating Meetings");
        // Create meetings
        List<Customer> allCustomers = customerRepository.findAll();
        meetingRepository.save(new Meeting("Trnova pot 12", LocalDate.now(), LocalTime.now(),
                LocalTime.now(), allCustomers.get(0)));
        meetingRepository.save(new Meeting("Lepa Gora 1", LocalDate.now(), LocalTime.now(),
                LocalTime.now(), allCustomers.get(0)));
        meetingRepository.save(new Meeting("Loška Stena 5", LocalDate.now(), LocalTime.now(),
                LocalTime.now(), allCustomers.get(2)));
        meetingRepository.save(new Meeting("Trnova pot 12", LocalDate.now(), LocalTime.now(),
                LocalTime.now(), allCustomers.get(3)));

        System.out.println("Creating Conclusions");
        // Create conclusions
        List<Meeting> allMeetings = meetingRepository.findAll();
        List<ConclusionType> allTypes = conclusionTypeRepository.findAll();
        conclusionRepository.save(new Conclusion(allMeetings.get(0),allTypes.get(0),
                "Sestanek: zdaj na Lepi Gori 1."));
        conclusionRepository.save(new Conclusion(allMeetings.get(1),allTypes.get(1),
                "Pogodba za nedoločen čas. Podpis"));
        conclusionRepository.save(new Conclusion(allMeetings.get(0),allTypes.get(2),
                "IBAN: SI56 000000000, ZNESEK: 1000€"));

    }
}
