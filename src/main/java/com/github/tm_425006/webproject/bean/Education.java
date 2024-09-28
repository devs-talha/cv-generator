package com.github.tm_425006.webproject.bean;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class Education implements Serializable {
    private Integer id;
    private Integer cvId;
    private String name;
    private String institute;
    private Date startDate;
    private Date endDate;
    private String grade;

    public Education() {
    }

    public Education(Integer id, Integer cvId, String name, String institute, Date startDate, Date endDate, String grade) {
        this.id = id;
        this.cvId = cvId;
        this.name = name;
        this.institute = institute;
        this.startDate = startDate;
        this.endDate = endDate;
        this.grade = grade;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Education education = (Education) o;
        return Objects.equals(id, education.id) && cvId.equals(education.cvId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cvId);
    }

    @Override
    public String toString() {
        return "Education{" +
                "id=" + id +
                ", cvId=" + cvId +
                ", name='" + name + '\'' +
                ", institute='" + institute + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", grade='" + grade + '\'' +
                '}';
    }
}
