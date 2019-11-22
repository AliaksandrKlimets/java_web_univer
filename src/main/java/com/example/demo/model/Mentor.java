package com.example.demo.model;

public class Mentor {

    private Long id;
    private String name;
    private String nickname;
    private Long age;
    private boolean isPencioner;

    public boolean isPencioner() {
        return isPencioner;
    }

    public void setPencioner(boolean pencioner) {
        isPencioner = pencioner;
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

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
        this.isPencioner = age > 63;
    }
}
