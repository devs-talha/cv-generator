package com.github.tm_425006.webproject.bean;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class WorkExperience implements Serializable {
    private Integer id;
    private Integer cvId;
    private String role;
    private String institute;
    private Date startDate;
    private Date endDate;
    private String details;

    public WorkExperience() {
    }

    public WorkExperience(Integer id, Integer cvId, String role, String institute,
                          Date startDate, Date endDate, String details) {
        this.id = id;
        this.cvId = cvId;
        this.role = role;
        this.institute = institute;
        this.startDate = startDate;
        this.endDate = endDate;
        this.details = details;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkExperience that = (WorkExperience) o;
        return Objects.equals(id, that.id) && cvId.equals(that.cvId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cvId);
    }

    @Override
    public String toString() {
        return "WorkExperience{" +
                "id=" + id +
                ", cvId=" + cvId +
                ", role='" + role + '\'' +
                ", institute='" + institute + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", details='" + details + '\'' +
                '}';
    }
}
