package com.example.textAnalyzer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "input")
public class Input {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "input_id")
    private Integer inputId;

    @Column(name = "payload")
    private String payload;

    public Input(String payload) {
        this.payload = payload;
    }

    public Input() {
    }

    @JsonIgnore
    @OneToMany(mappedBy = "input")
    private List<AnalyzeResult> stats = new ArrayList<>();

    public Integer getInputId() {
        return inputId;
    }

    public void setInputId(Integer inputId) {
        this.inputId = inputId;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public List<AnalyzeResult> getStats() {
        return stats;
    }

    public void setStats(List<AnalyzeResult> stats) {
        this.stats = stats;
    }


}
