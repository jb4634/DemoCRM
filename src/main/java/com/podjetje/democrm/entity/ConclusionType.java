package com.podjetje.democrm.entity;

import javax.persistence.*;
import java.util.List;

/**
 *  ConclusionType determines possible types of conclusions: Sestanek, Račun, Pogodba
 */
@Entity
public class ConclusionType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String type;

    @OneToMany(mappedBy = "conclusionType", cascade = CascadeType.ALL)
    private List<Conclusion> conclusions;

    // Constructors

    public ConclusionType() {
    }

    public ConclusionType(String type) {
        this.type = type;
    }

    // Getters and setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
