package com.podjetje.democrm.entity;

import javax.persistence.*;

@Entity
public class Conclusion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn
    private ConclusionType conclusionType;

    @ManyToOne
    @JoinColumn
    private Meeting meeting;

    private String content;

    public Conclusion(Meeting meeting, ConclusionType conclusionType, String content) {
        this.meeting = meeting;
        this.conclusionType = conclusionType;
        this.content = content;
    }

    public Conclusion() {
    }

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
