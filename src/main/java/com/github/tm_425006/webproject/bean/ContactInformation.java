package com.github.tm_425006.webproject.bean;

import java.io.Serializable;
import java.util.Objects;

public class ContactInformation implements Serializable {
    private Integer id;
    private Integer cvId;
    private String name;
    private String url;

    public ContactInformation() {
    }

    public ContactInformation(Integer id, Integer cvId, String name, String url) {
        this.id = id;
        this.cvId = cvId;
        this.name = name;
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCvId() {
        return cvId;
    }

    public void setCvId(Integer cvId) {
        this.cvId = cvId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactInformation that = (ContactInformation) o;
        return Objects.equals(id, that.id) && cvId.equals(that.cvId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cvId);
    }

    @Override
    public String toString() {
        return "ContactInformation{" +
                "id=" + id +
                ", cvId=" + cvId +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
