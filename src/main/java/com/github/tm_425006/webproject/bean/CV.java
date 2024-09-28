package com.github.tm_425006.webproject.bean;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Objects;

public class CV implements Serializable {
    private Integer id;
    private Integer userId;
    private Date createdDate;
    private Date modifiedDate;
    private BasicInformation basicInformation;
    private ArrayList<Language> languages;
    private ArrayList<ContactInformation> contactInformationList;
    private ArrayList<Certificate> certificates;
    private ArrayList<Skill> skills;
    private ArrayList<Interest> interests;
    private ArrayList<Project> projects;
    private ArrayList<WorkExperience> workExperiences;
    private ArrayList<Education> educations;

    public CV() {
        this.certificates = new ArrayList<>();
        this.educations = new ArrayList<>();
        this.contactInformationList = new ArrayList<>();
        this.languages = new ArrayList<>();
        this.interests = new ArrayList<>();
        this.projects = new ArrayList<>();
        this.workExperiences = new ArrayList<>();
        this.skills = new ArrayList<>();
    }

    public CV(Integer id, Integer userId, BasicInformation basicInformation, Date createdDate,
              Date modifiedDate, ArrayList<Language> languages, ArrayList<ContactInformation> contactInformationArrayList,
              ArrayList<Certificate> certificates, ArrayList<Skill> skills, ArrayList<Interest> interests, ArrayList<Project> projects,
              ArrayList<WorkExperience> workExperiences, ArrayList<Education> educations) {
        this.id = id;
        this.userId = userId;
        this.basicInformation = basicInformation;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.languages = languages;
        this.contactInformationList = contactInformationList;
        this.certificates = certificates;
        this.skills = skills;
        this.interests = interests;
        this.projects = projects;
        this.workExperiences = workExperiences;
        this.educations = educations;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public ArrayList<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(ArrayList<Language> languages) {
        this.languages = languages;
    }

    public ArrayList<Certificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(ArrayList<Certificate> certificates) {
        this.certificates = certificates;
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
    }

    public ArrayList<Interest> getInterests() {
        return interests;
    }

    public void setInterests(ArrayList<Interest> interests) {
        this.interests = interests;
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }

    public ArrayList<WorkExperience> getWorkExperiences() {
        return workExperiences;
    }

    public void setWorkExperiences(ArrayList<WorkExperience> workExperiences) {
        this.workExperiences = workExperiences;
    }

    public ArrayList<Education> getEducations() {
        return educations;
    }

    public void setEducations(ArrayList<Education> educations) {
        this.educations = educations;
    }

    public ArrayList<ContactInformation> getContactInformationList() {
        return contactInformationList;
    }

    public void setContactInformationList(ArrayList<ContactInformation> contactInformationList) {
        this.contactInformationList = contactInformationList;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BasicInformation getBasicInformation() {
        return basicInformation;
    }

    public void setBasicInformation(BasicInformation basicInformation) {
        this.basicInformation = basicInformation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CV cv = (CV) o;
        return Objects.equals(id, cv.id) && userId.equals(cv.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId);
    }

    @Override
    public String toString() {
        return "CV{" +
                "id=" + id +
                ", userId=" + userId +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                ", basicInformation=" + basicInformation +
                ", languages=" + languages +
                ", contactInformationList=" + contactInformationList +
                ", certificates=" + certificates +
                ", skills=" + skills +
                ", interests=" + interests +
                ", projects=" + projects +
                ", workExperiences=" + workExperiences +
                ", educations=" + educations +
                '}';
    }
}
