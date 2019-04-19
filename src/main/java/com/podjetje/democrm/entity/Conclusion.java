package com.podjetje.democrm.entity;

import javax.persistence.*;

/***
 *  Entity Conclusion represents the conclusion that was made on the Meeting 'meeting'.
 *  It can be one of the 'conclusionTypes': sestanek, pogodba, račun
 *  with details in 'content'.
 */
@Entity
public class Conclusion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    // Type of the conclusion: sestanek, pogodba, račun
    @ManyToOne
    @JoinColumn
    private ConclusionType conclusionType;


    @ManyToOne
    @JoinColumn
    private Meeting meeting;

    // The content of the conclusion
    private String content;

    // Constructors

    public Conclusion(Meeting meeting, ConclusionType conclusionType, String content) {
        this.meeting = meeting;
        this.conclusionType = conclusionType;
        this.content = content;
    }

    public Conclusion() {
    }

    // Getters and setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ConclusionType getConclusionType() {
        return conclusionType;
    }

    public void setConclusionType(ConclusionType conclusionType) {
        this.conclusionType = conclusionType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }
}
