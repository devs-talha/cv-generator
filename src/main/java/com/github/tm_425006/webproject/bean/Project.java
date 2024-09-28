package com.github.tm_425006.webproject.bean;

import java.io.Serializable;
import java.util.Objects;

public class Project implements Serializable {
    private Integer id;
    private Integer cvId;
    private String name;
    private String details;

    public Project() {
    }

    public Project(Integer id, Integer cvId, String name, String details) {
        this.id = id;
        this.cvId = cvId;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        Project project = (Project) o;
        return Objects.equals(id, project.id) && cvId.equals(project.cvId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cvId);
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", cvId=" + cvId +
                ", name='" + name + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
