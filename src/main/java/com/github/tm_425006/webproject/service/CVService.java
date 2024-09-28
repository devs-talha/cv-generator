package com.github.tm_425006.webproject.service;

import com.github.tm_425006.webproject.exception.CVGeneratorException;
import com.github.tm_425006.webproject.model.*;
import com.github.tm_425006.webproject.bean.*;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Properties;

public class CVService {
    private final DAO<CV> cvDAO;
    private final DAO<BasicInformation> basicInformationDAO;
    private final DAO<Language> languageDAO;
    private final DAO<Certificate> certificateDAO;
    private final DAO<ContactInformation> contactInformationDAO;
    private final DAO<Education> educationDAO;
    private final DAO<Interest> interestDAO;
    private final DAO<Project> projectDAO;
    private final DAO<WorkExperience> workExperienceDAO;
    private final DAO<Skill> skillDAO;

    public CVService(Properties dbProperties) {
        this.cvDAO = new CVDAO(dbProperties);
        this.basicInformationDAO = new BasicInformationDAO(dbProperties);
        this.languageDAO = new LanguageDAO(dbProperties);
        this.certificateDAO = new CertificateDAO(dbProperties);
        this.contactInformationDAO = new ContactInformationDAO(dbProperties);
        this.educationDAO = new EducationDAO(dbProperties);
        this.interestDAO = new InterestDAO(dbProperties);
        this.projectDAO = new ProjectDAO(dbProperties);
        this.workExperienceDAO = new WorkExperienceDAO(dbProperties);
        this.skillDAO = new SkillDAO(dbProperties);
    }

    public CV get(int id) throws SQLException, CVGeneratorException, ClassNotFoundException {
        CV cv = this.getCvDAO().get(id);
        cv.setBasicInformation(this.getBasicInformationDAO().get(id));
        cv.setLanguages(this.getLanguageDAO().getAll(id));
        cv.setCertificates(this.getCertificateDAO().getAll(id));
        cv.setContactInformationList(this.getContactInformationDAO().getAll(id));
        cv.setEducations(this.getEducationDAO().getAll(id));
        cv.setInterests(this.getInterestDAO().getAll(id));
        cv.setProjects(this.getProjectDAO().getAll(id));
        cv.setWorkExperiences(this.getWorkExperienceDAO().getAll(id));
        cv.setSkills(this.getSkillDAO().getAll(id));

        return cv;
    }

    public ArrayList<CV> getAll(int userId) throws SQLException, CVGeneratorException, ClassNotFoundException {
        ArrayList<CV> list = this.getCvDAO().getAll(userId);

        if (list == null || list.size() == 0)
            return null;

        for (CV cv : list) {
            cv.setBasicInformation(this.getBasicInformationDAO().get(cv.getId()));
            cv.setLanguages(this.getLanguageDAO().getAll(cv.getId()));
            cv.setCertificates(this.getCertificateDAO().getAll(cv.getId()));
            cv.setContactInformationList(this.getContactInformationDAO().getAll(cv.getId()));
            cv.setEducations(this.getEducationDAO().getAll(cv.getId()));
            cv.setInterests(this.getInterestDAO().getAll(cv.getId()));
            cv.setProjects(this.getProjectDAO().getAll(cv.getId()));
            cv.setWorkExperiences(this.getWorkExperienceDAO().getAll(cv.getId()));
            cv.setSkills(this.getSkillDAO().getAll(cv.getId()));
        }
        return list;
    }

    public Integer insert(CV cv) throws SQLException, CVGeneratorException, ClassNotFoundException {
        Integer cvId = this.getCvDAO().insert(cv);

        cv.getBasicInformation().setCvId(cvId);
        this.getBasicInformationDAO().insert(cv.getBasicInformation());

        for (Language language : cv.getLanguages()) {
            language.setCvId(cvId);
            this.getLanguageDAO().insert(language);
        }
        for (Certificate certificate : cv.getCertificates()) {
            certificate.setCvId(cvId);
            this.getCertificateDAO().insert(certificate);
        }
        for (ContactInformation contactInformation : cv.getContactInformationList()) {
            contactInformation.setCvId(cvId);
            this.getContactInformationDAO().insert(contactInformation);
        }
        for (Education education : cv.getEducations()) {
            education.setCvId(cvId);
            this.getEducationDAO().insert(education);
        }
        for (Interest interest : cv.getInterests()) {
            interest.setCvId(cvId);
            this.getInterestDAO().insert(interest);
        }
        for (Project project : cv.getProjects()) {
            project.setCvId(cvId);
            this.getProjectDAO().insert(project);
        }
        for (WorkExperience workExperience : cv.getWorkExperiences()) {
            workExperience.setCvId(cvId);
            this.getWorkExperienceDAO().insert(workExperience);
        }
        for (Skill skill : cv.getSkills()) {
            skill.setCvId(cvId);
            this.getSkillDAO().insert(skill);
        }
        return cvId;
    }

    public void update(CV modifiedCV) throws SQLException, CVGeneratorException, ClassNotFoundException {
        CV originalCV = this.get(modifiedCV.getId());
        this.getCvDAO().update(modifiedCV);
        this.deleteRemovedEntries(modifiedCV, originalCV);
        this.insertOrUpdateEntries(modifiedCV);
    }

    private void insertOrUpdateEntries(CV cv) throws SQLException, CVGeneratorException, ClassNotFoundException {
        if (cv.getBasicInformation().getId() == null || cv.getBasicInformation().getId() == -1) {
            this.getBasicInformationDAO().insert(cv.getBasicInformation());
        } else {
            this.getBasicInformationDAO().update(cv.getBasicInformation());
        }

        for (Language language : cv.getLanguages()) {
            if (language.getId() == null || language.getId() == -1) {
                this.getLanguageDAO().insert(language);
            } else {
                this.getLanguageDAO().update(language);
            }
        }
        for (Certificate certificate : cv.getCertificates()) {
            if (certificate.getId() == null || certificate.getId() == -1) {
                this.getCertificateDAO().insert(certificate);
            } else {
                this.getCertificateDAO().update(certificate);
            }
        }
        for (ContactInformation contactInformation : cv.getContactInformationList()) {
            if (contactInformation.getId() == null || contactInformation.getId() == -1) {
                this.getContactInformationDAO().insert(contactInformation);
            } else {
                this.getContactInformationDAO().update(contactInformation);
            }
        }
        for (Education education : cv.getEducations()) {
            if (education.getId() == null || education.getId() == -1) {
                this.getEducationDAO().insert(education);
            } else {
                this.getEducationDAO().update(education);
            }
        }
        for (Interest interest : cv.getInterests()) {
            if (interest.getId() == null || interest.getId() == -1) {
                this.getInterestDAO().insert(interest);
            } else {
                this.getInterestDAO().update(interest);
            }
        }
        for (Project project : cv.getProjects()) {
            if (project.getId() == null || project.getId() == -1) {
                this.getProjectDAO().insert(project);
            } else {
                this.getProjectDAO().update(project);
            }
        }
        for (WorkExperience workExperience : cv.getWorkExperiences()) {
            if (workExperience.getId() == null || workExperience.getId() == -1) {
                this.getWorkExperienceDAO().insert(workExperience);
            } else {
                this.getWorkExperienceDAO().update(workExperience);
            }
        }
        for (Skill skill : cv.getSkills()) {
            if (skill.getId() == null || skill.getId() == -1) {
                this.getSkillDAO().insert(skill);
            } else {
                this.getSkillDAO().update(skill);
            }
        }
    }

    private void deleteRemovedEntries(CV modifiedCV, CV originalCV) throws SQLException, CVGeneratorException, ClassNotFoundException {
        HashSet<Language> removedLanguages = new HashSet<>(originalCV.getLanguages());
        removedLanguages.removeAll(new HashSet<>(modifiedCV.getLanguages()));

        for (Language language : removedLanguages)
            this.getLanguageDAO().delete(language);

        HashSet<Certificate> removedCertificates = new HashSet<>(originalCV.getCertificates());
        removedCertificates.removeAll(new HashSet<>(modifiedCV.getCertificates()));

        for (Certificate certificate : removedCertificates)
            this.getCertificateDAO().delete(certificate);

        HashSet<ContactInformation> removedContactInformationSet = new HashSet<>(originalCV.getContactInformationList());
        removedContactInformationSet.removeAll(new HashSet<>(modifiedCV.getContactInformationList()));

        for (ContactInformation contactInformation : removedContactInformationSet)
            this.getContactInformationDAO().delete(contactInformation);

        HashSet<Education> removedEducations = new HashSet<>(originalCV.getEducations());
        removedEducations.removeAll(new HashSet<>(modifiedCV.getEducations()));

        for (Education education : removedEducations)
            this.getEducationDAO().delete(education);

        HashSet<Interest> removedInterests = new HashSet<>(originalCV.getInterests());
        removedInterests.removeAll(new HashSet<>(modifiedCV.getInterests()));

        for (Interest interest : removedInterests)
            this.getInterestDAO().delete(interest);

        HashSet<Project> removedProjects = new HashSet<>(originalCV.getProjects());
        removedProjects.removeAll(new HashSet<>(modifiedCV.getProjects()));

        for (Project project : removedProjects)
            this.getProjectDAO().delete(project);

        HashSet<WorkExperience> removedWorkExperiences = new HashSet<>(originalCV.getWorkExperiences());
        removedWorkExperiences.removeAll(new HashSet<>(modifiedCV.getWorkExperiences()));

        for (WorkExperience workExperience : removedWorkExperiences)
            this.getWorkExperienceDAO().delete(workExperience);

        HashSet<Skill> removedSkills = new HashSet<>(originalCV.getSkills());
        removedSkills.removeAll(new HashSet<>(modifiedCV.getSkills()));

        for (Skill skill : removedSkills)
            this.getSkillDAO().delete(skill);
    }

    public void delete(CV cv) throws SQLException, CVGeneratorException, ClassNotFoundException {
        this.getCvDAO().delete(cv);
    }

    private DAO<CV> getCvDAO() {
        return cvDAO;
    }

    private DAO<BasicInformation> getBasicInformationDAO() {
        return basicInformationDAO;
    }

    private DAO<Language> getLanguageDAO() {
        return languageDAO;
    }

    private DAO<Certificate> getCertificateDAO() {
        return certificateDAO;
    }

    private DAO<ContactInformation> getContactInformationDAO() {
        return contactInformationDAO;
    }

    private DAO<Education> getEducationDAO() {
        return educationDAO;
    }

    private DAO<Interest> getInterestDAO() {
        return interestDAO;
    }

    private DAO<Project> getProjectDAO() {
        return projectDAO;
    }

    private DAO<WorkExperience> getWorkExperienceDAO() {
        return workExperienceDAO;
    }

    private DAO<Skill> getSkillDAO() {
        return skillDAO;
    }
}
