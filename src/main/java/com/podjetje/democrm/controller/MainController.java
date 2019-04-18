package com.podjetje.democrm.controller;

import com.podjetje.democrm.entity.Customer;
import com.podjetje.democrm.entity.Meeting;
import com.podjetje.democrm.repository.CustomerRepository;
import com.podjetje.democrm.repository.MeetingRepository;
import com.podjetje.democrm.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(path="/demo")
public class MainController {

    @Autowired
    private CustomerRepository customerRepository;

     @Autowired
     private MeetingRepository meetingRepository;

     @Autowired
     private CustomerServiceImpl customerService;

    @GetMapping(path="/add")
    public @ResponseBody String addNewCustomer (@RequestParam String firstName, @RequestParam String email){
        customerService.saveCustomerFields(firstName,null,email,null);
        return "Saved";
    }

    @GetMapping(path="/fillMeetings")
    public @ResponseBody  String fillMeetings (){
        List<Customer> customers = customerRepository.findCustomersByFirstName("Jan");
        Meeting m1 = new Meeting("V kocevju",null, null, null, customers.get(0));
        Meeting m2 = new Meeting("V ljubljani",null, null, null, customers.get(0));
        Meeting m3 = new Meeting("V kocevju",null, null, null, customers.get(1));

        meetingRepository.save(m1);
        meetingRepository.save(m2);
        meetingRepository.save(m3);
        return "Saved meetings";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping(path="/fillTable")
    public @ResponseBody String fillCustomers (){
        Customer tmp1 = new Customer("Jan", "Blatnik", "jb@jv.com", "1231231");
        Customer tmp2 = new Customer("Jan", "Plestenjak", "jp@jv.com", "1231232");
        Customer tmp3 = new Customer("Luka", "Madon", "lm@jv.com", "1231233");
        Customer tmp4 = new Customer("Anze", "Kristan", "ak@jv.com", "1231234");
        Customer tmp5 = new Customer("Mitja", "Blatnik", "mb@jv.com", "1231235");

        customerRepository.save(tmp1);
        customerRepository.save(tmp2);
        customerRepository.save(tmp3);
        customerRepository.save(tmp4);
        customerRepository.save(tmp5);
        return "Table Filled";
    }

    @GetMapping(path="/findFirst")
    public @ResponseBody Iterable<Customer> getAllCustomersByFirst(@RequestParam String firstName){
        return customerService.getCustomersByName(firstName, null);
    }
    @GetMapping(path="/findLast")
    public @ResponseBody Iterable<Customer> getCustomersByLast(@RequestParam String lastName){
        return customerService.getCustomersByName(null, lastName);
    }
    @GetMapping(path="/findFull")
    public @ResponseBody Iterable<Customer> getAllCustomersByFull(@RequestParam String firstName, @RequestParam String lastName){
        return customerService.getCustomersByName(firstName, lastName);
    }

    @GetMapping(path="/meetings")
    public @ResponseBody Iterable<Meeting> getAllMeetings(){
        return meetingRepository.findAll();
    }

    @GetMapping(path="/meetings1")
    public @ResponseBody Iterable<Meeting> getAllMeetingsByFirstName1(@RequestParam String firstName){
        return meetingRepository.findByCustomer_FirstName(firstName);
    }


}
