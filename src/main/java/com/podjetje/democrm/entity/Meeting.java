package com.podjetje.democrm.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 *  Meeting is represented with the location, date, timeStart, timeEnd and customer.
 */
@Entity
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    // Location of the meeting
    private String location;

    // Date of the meeting
    private LocalDate date;

    //Time timeStart represents the beginning of the meeting
    private LocalTime timeStart;

    //Time timeEnd represents the end of the meeting
    private LocalTime timeEnd;

    // Customer that attended the meeting
    @ManyToOne
    @JoinColumn
    private Customer customer;

    // List of all the conclusions at the meeting
    @OneToMany(mappedBy = "meeting", cascade = CascadeType.ALL)
    private List<Conclusion> conclusions;

    // Constructors

    public Meeting(String location, LocalDate date, LocalTime timeStart, LocalTime timeEnd) {
        this.location = location;
        this.date = date;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    public Meeting(String location, LocalDate date, LocalTime timeStart, LocalTime timeEnd, Customer customer) {
        this.location = location;
        this.date = date;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.customer = customer;
    }

    public Meeting() {
    }

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalTime getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(LocalTime timeStart) {
        this.timeStart = timeStart;
    }

    public LocalTime getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(LocalTime timeEnd) {
        this.timeEnd = timeEnd;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Conclusion> getConclusions() {
        return conclusions;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCustomerName(){
        if(this.customer==null){
            return null;
        }
        else
            return customer.getFullName();
    }
}
