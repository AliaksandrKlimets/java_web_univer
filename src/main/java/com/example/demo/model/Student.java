package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long univerId;
    private String name;
    private String nickname;
    private Long stipuha;
    private Double avgMark;

    public Double getAvgMark() {
        return avgMark;
    }

    public void setAvgMark(Double avgMark) {
        this.avgMark = avgMark;
        this.stipuha = avgMark > 5 ? (long)(Math.random() * 1000 + 500) : (long)(Math.random() * 1000 + 300);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Long getStipuha() {
        return stipuha;
    }

    public void setStipuha(Long stipuha) {
        this.stipuha = stipuha;
    }

    public Long getUniverId() {
        return univerId;
    }

    public void setUniverId(Long univerId) {
        this.univerId = univerId;
    }
}
