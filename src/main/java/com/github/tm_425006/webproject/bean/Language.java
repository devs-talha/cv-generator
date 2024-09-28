package com.github.tm_425006.webproject.bean;

import java.io.Serializable;
import java.util.Objects;

public class Language implements Serializable {
    private Integer id;
    private Integer cvId;
    private String name;

    public Language() {
    }

    public Language(Integer id, Integer cvId, String name) {
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
        Language language = (Language) o;
        return Objects.equals(id, language.id) && cvId.equals(language.cvId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cvId);
    }

    @Override
    public String toString() {
        return "Language{" +
                "id=" + id +
                ", cvId=" + cvId +
                ", name='" + name + '\'' +
                '}';
    }
}
