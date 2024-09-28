package com.github.tm_425006.webproject.bean;

import java.io.Serializable;
import java.util.Objects;

public class Skill implements Serializable {
    private Integer id;
    private Integer cvId;
    private String name;

    public Skill() {
    }

    public Skill(Integer id, Integer cvId, String name) {
        this.id = id;
        this.cvId = cvId;
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getCvId() {
        return cvId;
    }

    public void setCvId(Integer cvId) {
        this.cvId = cvId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill skill = (Skill) o;
        return Objects.equals(id, skill.id) && cvId.equals(skill.cvId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cvId);
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", cvId=" + cvId +
                ", name='" + name + '\'' +
                '}';
    }
}
