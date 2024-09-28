package com.github.tm_425006.webproject.bean;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class Certificate implements Serializable {
    private Integer id;
    private Integer cvId;
    private String name;
    private String institute;
    private Date completionDate;

    public Certificate() {
    }

    public Certificate(Integer id, Integer cvId, String name, String institute, Date completionDate) {
        this.id = id;
        this.cvId = cvId;
        this.name = name;
        this.institute = institute;
        this.completionDate = completionDate;
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

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Certificate that = (Certificate) o;
        return Objects.equals(id, that.id) && cvId.equals(that.cvId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cvId);
    }

    @Override
    public String toString() {
        return "Certificate{" +
                "id=" + id +
                ", cvId=" + cvId +
                ", name='" + name + '\'' +
                ", institute='" + institute + '\'' +
                ", completionDate=" + completionDate +
                '}';
    }
}
