package com.github.tm_425006.webproject.bean;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class BasicInformation implements Serializable {
    private Integer id;
    private Integer cvId;
    private String name;
    private String address;
    private Date birthDate;

    public BasicInformation() {
    }

    public BasicInformation(Integer id, Integer cvId, String name, String address, Date birthDate) {
        this.id = id;
        this.cvId = cvId;
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasicInformation that = (BasicInformation) o;
        return Objects.equals(id, that.id) && cvId.equals(that.cvId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cvId);
    }

    @Override
    public String toString() {
        return "BasicInformation{" +
                "id=" + id +
                ", cvId=" + cvId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
