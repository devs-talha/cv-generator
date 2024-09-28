package com.github.tm_425006.webproject.bean;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;

public class BeanUtils {
    public static User createUser(Map<String, String[]> parameterMap) {
        User user = new User();
        if (parameterMap.containsKey("id"))
            user.setId(Integer.valueOf(parameterMap.get("id")[0].trim()));
        if (parameterMap.containsKey("firstName"))
            user.setFirstName(parameterMap.get("firstName")[0].trim());
        if (parameterMap.containsKey("lastName"))
            user.setLastName(parameterMap.get("lastName")[0].trim());
        if (parameterMap.containsKey("email"))
            user.setEmail(parameterMap.get("email")[0].trim());
        if (parameterMap.containsKey("birthDate"))
            user.setBirthDate(Date.valueOf(parameterMap.get("birthDate")[0].trim()));
        if (parameterMap.containsKey("password"))
            user.setPassword(parameterMap.get("password")[0].trim());
        if (parameterMap.containsKey("address"))
            user.setAddress(parameterMap.get("address")[0].trim());
        return user;
    }

    public static BasicInformation createBasicInformation(Map<String, String[]> parameterMap) {
        BasicInformation basicInformation = new BasicInformation();
        if (parameterMap.containsKey("basicInformationId"))
            basicInformation.setId(Integer.valueOf(parameterMap.get("basicInformationId")[0].trim()));
        if (parameterMap.containsKey("basicInformationCvId"))
            basicInformation.setCvId(Integer.valueOf(parameterMap.get("basicInformationCvId")[0].trim()));
        if (parameterMap.containsKey("basicInformationName"))
            basicInformation.setName(parameterMap.get("basicInformationName")[0].trim());
        if (parameterMap.containsKey("basicInformationBirthDate"))
            basicInformation.setBirthDate(Date.valueOf(parameterMap.get("basicInformationBirthDate")[0].trim()));
        if (parameterMap.containsKey("basicInformationAddress"))
            basicInformation.setAddress(parameterMap.get("basicInformationAddress")[0].trim());
        return basicInformation;
    }

    public static Certificate createCertificate(Map<String, String[]> parameterMap, int index) {
        Certificate certificate = new Certificate();
        if (parameterMap.containsKey("certificateId"))
            certificate.setId(Integer.valueOf(parameterMap.get("certificateId")[index].trim()));
        if (parameterMap.containsKey("certificateCvId"))
            certificate.setCvId(Integer.valueOf(parameterMap.get("certificateCvId")[index].trim()));
        if (parameterMap.containsKey("certificateName"))
            certificate.setName(parameterMap.get("certificateName")[index].trim());
        if (parameterMap.containsKey("certificateCompletionDate"))
            certificate.setCompletionDate(Date.valueOf(parameterMap.get("certificateCompletionDate")[index].trim()));
        if (parameterMap.containsKey("certificateInstitute"))
            certificate.setInstitute(parameterMap.get("certificateInstitute")[index].trim());
        return certificate;
    }

    public static ContactInformation createContactInformation(Map<String, String[]> parameterMap, int index) {
        ContactInformation contactInformation = new ContactInformation();
        if (parameterMap.containsKey("contactInformationId"))
            contactInformation.setId(Integer.valueOf(parameterMap.get("contactInformationId")[index].trim()));
        if (parameterMap.containsKey("contactInformationCvId"))
            contactInformation.setCvId(Integer.valueOf(parameterMap.get("contactInformationCvId")[index].trim()));
        if (parameterMap.containsKey("contactInformationName"))
            contactInformation.setName(parameterMap.get("contactInformationName")[index].trim());
        if (parameterMap.containsKey("contactInformationUrl"))
            contactInformation.setUrl(parameterMap.get("contactInformationUrl")[index].trim());
        return contactInformation;
    }

    public static Education createEducation(Map<String, String[]> parameterMap, int index) {
        Education education = new Education();
        if (parameterMap.containsKey("educationId"))
            education.setId(Integer.valueOf(parameterMap.get("educationId")[index].trim()));
        if (parameterMap.containsKey("educationCvId"))
            education.setCvId(Integer.valueOf(parameterMap.get("educationCvId")[index].trim()));
        if (parameterMap.containsKey("educationName"))
            education.setName(parameterMap.get("educationName")[index].trim());
        if (parameterMap.containsKey("educationInstitute"))
            education.setInstitute(parameterMap.get("educationInstitute")[index].trim());
        if (parameterMap.containsKey("educationStartDate"))
            education.setStartDate(Date.valueOf(parameterMap.get("educationStartDate")[index].trim()));
        if (parameterMap.containsKey("educationEndDate") &&
                !parameterMap.get("educationEndDate")[index].trim().isEmpty())
            education.setEndDate(Date.valueOf(parameterMap.get("educationEndDate")[index].trim()));
        if (parameterMap.containsKey("educationGrade"))
            education.setGrade(parameterMap.get("educationGrade")[index].trim());
        return education;
    }

    public static WorkExperience createWorkExperience(Map<String, String[]> parameterMap, int index) {
        WorkExperience workExperience = new WorkExperience();
        if (parameterMap.containsKey("workExperienceId"))
            workExperience.setId(Integer.valueOf(parameterMap.get("workExperienceId")[index].trim()));
        if (parameterMap.containsKey("workExperienceCvId"))
            workExperience.setCvId(Integer.valueOf(parameterMap.get("workExperienceCvId")[index].trim()));
        if (parameterMap.containsKey("workExperienceRole"))
            workExperience.setRole(parameterMap.get("workExperienceRole")[index].trim());
        if (parameterMap.containsKey("workExperienceInstitute"))
            workExperience.setInstitute(parameterMap.get("workExperienceInstitute")[index].trim());
        if (parameterMap.containsKey("workExperienceStartDate"))
            workExperience.setStartDate(Date.valueOf(parameterMap.get("workExperienceStartDate")[index].trim()));
        if (parameterMap.containsKey("workExperienceEndDate") &&
                !parameterMap.get("workExperienceEndDate")[index].trim().isEmpty())
            workExperience.setEndDate(Date.valueOf(parameterMap.get("workExperienceEndDate")[index].trim()));
        if (parameterMap.containsKey("workExperienceDetails"))
            workExperience.setDetails(parameterMap.get("workExperienceDetails")[index].trim());
        return workExperience;
    }

    public static Project createProject(Map<String, String[]> parameterMap, int index) {
        Project project = new Project();
        if (parameterMap.containsKey("projectId"))
            project.setId(Integer.valueOf(parameterMap.get("projectId")[index].trim()));
        if (parameterMap.containsKey("projectCvId"))
            project.setCvId(Integer.valueOf(parameterMap.get("projectCvId")[index].trim()));
        if (parameterMap.containsKey("projectName"))
            project.setName(parameterMap.get("projectName")[index].trim());
        if (parameterMap.containsKey("projectDetails"))
            project.setDetails(parameterMap.get("projectDetails")[index].trim());
        return project;
    }

    public static Skill createSkill(Map<String, String[]> parameterMap, int index) {
        Skill skill = new Skill();
        if (parameterMap.containsKey("skillId"))
            skill.setId(Integer.valueOf(parameterMap.get("skillId")[index].trim()));
        if (parameterMap.containsKey("skillCvId"))
            skill.setCvId(Integer.valueOf(parameterMap.get("skillCvId")[index].trim()));
        if (parameterMap.containsKey("skillName"))
            skill.setName(parameterMap.get("skillName")[index].trim());
        return skill;
    }

    public static Interest createInterest(Map<String, String[]> parameterMap, int index) {
        Interest interest = new Interest();
        if (parameterMap.containsKey("interestId"))
            interest.setId(Integer.valueOf(parameterMap.get("interestId")[index].trim()));
        if (parameterMap.containsKey("interestCvId"))
            interest.setCvId(Integer.valueOf(parameterMap.get("interestCvId")[index].trim()));
        if (parameterMap.containsKey("interestName"))
            interest.setName(parameterMap.get("interestName")[index].trim());
        return interest;
    }

    public static Language createLanguage(Map<String, String[]> parameterMap, int index) {
        Language language = new Language();
        if (parameterMap.containsKey("languageId"))
            language.setId(Integer.valueOf(parameterMap.get("languageId")[index].trim()));
        if (parameterMap.containsKey("languageCvId"))
            language.setCvId(Integer.valueOf(parameterMap.get("languageCvId")[index].trim()));
        if (parameterMap.containsKey("languageName"))
            language.setName(parameterMap.get("languageName")[index].trim());
        return language;
    }

    public static CV createCV(Map<String, String[]> parameterMap) {
        CV cv = new CV();

        if (parameterMap.containsKey("id"))
            cv.setId(Integer.valueOf(parameterMap.get("id")[0].trim()));
        if (parameterMap.containsKey("userId"))
            cv.setUserId(Integer.valueOf(parameterMap.get("userId")[0].trim()));

        if (parameterMap.containsKey("basicInformationId"))
            cv.setBasicInformation(createBasicInformation(parameterMap));

        if (parameterMap.containsKey("certificateId")) {
            for (int index = 0; index < parameterMap.get("certificateId").length; index++)
                cv.getCertificates().add(createCertificate(parameterMap, index));
        }
        if (parameterMap.containsKey("educationId")) {
            for (int index = 0; index < parameterMap.get("educationId").length; index++)
                cv.getEducations().add(createEducation(parameterMap, index));
        }
        if (parameterMap.containsKey("contactInformationId")) {
            for (int index = 0; index < parameterMap.get("contactInformationId").length; index++)
                cv.getContactInformationList().add(createContactInformation(parameterMap, index));
        }
        if (parameterMap.containsKey("languageId")) {
            for (int index = 0; index < parameterMap.get("languageId").length; index++)
                cv.getLanguages().add(createLanguage(parameterMap, index));
        }
        if (parameterMap.containsKey("interestId")) {
            for (int index = 0; index < parameterMap.get("interestId").length; index++)
                cv.getInterests().add(createInterest(parameterMap, index));
        }
        if (parameterMap.containsKey("projectId")) {
            for (int index = 0; index < parameterMap.get("projectId").length; index++)
                cv.getProjects().add(createProject(parameterMap, index));
        }
        if (parameterMap.containsKey("workExperienceId")) {
            for (int index = 0; index < parameterMap.get("workExperienceId").length; index++)
                cv.getWorkExperiences().add(createWorkExperience(parameterMap, index));
        }
        if (parameterMap.containsKey("skillId")) {
            for (int index = 0; index < parameterMap.get("skillId").length; index++)
                cv.getSkills().add(createSkill(parameterMap, index));
        }

        return cv;
    }
}
