package com.github.tm_425006.webproject;

import com.github.tm_425006.webproject.bean.*;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.layout.font.FontProvider;

import java.io.*;
import java.util.List;

public class CVGenerator {
    private final CV cv;

    public CVGenerator(CV cv) {
        this.cv = cv;
    }

    public String generateHTML() {
        StringBuilder html = new StringBuilder();
        html.append("""
                <html lang="en">
                  <head>
                    <meta charset="UTF-8" />
                                
                    <style>
                      html {
                        font-size: 15px;
                        font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
                      }
                                
                      .main-border {
                        border: 1px solid rgba(201, 201, 201, 255);
                      }
                                
                      .center {
                        margin-left: auto;
                        margin-right: auto;
                      }
                                
                      em {
                        font-weight: 600;
                        font-style: normal;
                      }
                                
                      td {
                        padding: 0.5rem 4rem 0.5rem 4rem;
                      }
                      
                    span {
                          background-color: gray;
                          color: white;
                          border-radius: 5px;
                          padding: 2px 5px;
                        }
                                
                       hr {
                         opacity: 0.5;
                         border: 1px solid rgba(201, 201, 201, 255);
                         margin-top: 0.5rem;
                         margin-bottom: 0.5rem;
                       }
                 
                       .left-margin {
                         margin-left: 0.5rem;
                       }
                                
                      .bottom-margin {
                        margin-bottom: 0.5rem;
                      }
                    </style>
                    <title>CV</title>
                  </head>
                            
                """);


        html.append("""
                <body>
                    <div class="main-border">
                      <div>
                        <table class="center">
                          <tr>
                            <td><em>Name</em></td>
                """);
        html.append(String.format("<td>%s</td>", this.getCv().getBasicInformation().getName()));
        html.append("""
                </tr>
                                
                          <tr>
                            <td><em>Date of Birth</em></td>
                """);
        html.append(String.format("<td>%s</td>", this.getCv().getBasicInformation().getBirthDate()));
        html.append("""
                 </tr>
                                
                          <tr>
                            <td><em>Address</em></td>
                """);
        html.append(String.format("<td>%s</td>", this.getCv().getBasicInformation().getAddress()));
        html.append("""
                 </tr>
                        </table>
                      </div>
                """);
        if (this.getCv().getContactInformationList() != null && this.getCv().getContactInformationList().size() != 0) {
            html.append("""
                    <hr />
                         <div>
                           <div>
                             <h3 class="left-margin"><em>Contact Information</em></h3>
                             <hr />
                           </div>
                   
                           <div>
                             <figure>
                               <dl>
                    """);
            for (ContactInformation contactInformation : this.getCv().getContactInformationList()) {
                html.append(String.format("<dt class=\"left-margin\"><em>%s</em></dt>", contactInformation.getName()));
                html.append(String.format("<dd class=\"bottom-margin\">%s</dd>", contactInformation.getUrl()));
            }
            html.append("""
                      </dl>
                            </figure>
                          </div>
                        </div>
                    """);

        }
        if (this.getCv().getCertificates() != null && this.getCv().getCertificates().size() != 0) {
            html.append("""
                     <hr />
                      <div>
                        <div>
                          <h3 class="left-margin"><em>Certificates</em></h3>
                          <hr />
                        </div>
                        <figure>
                          <ul>
                    """);
            for (Certificate certificate : this.getCv().getCertificates()) {
                html.append(String.format("<li><p>%s - %s <cite>(%s)</cite></p></li>", certificate.getName(),
                        certificate.getInstitute(), certificate.getCompletionDate()));
            }
            html.append("""
                    </ul>
                       </figure>
                     </div>
                    """);
        }
        if (this.getCv().getEducations() != null && this.getCv().getEducations().size() != 0) {
            html.append("""
                    <hr />
                      <div>
                        <div>
                          <h3 class="left-margin"><em>Education Details</em></h3>
                        </div>
                        <hr />
                      </div>
                
                      <div>
                """);
            for (Education education : this.getCv().getEducations()) {
                html.append("""
                        <figure>
                        """);
                html.append(education.getName());
                html.append("""
                                            <ul>
                        """);
                html.append(String.format("<li>%s</li>", education.getInstitute()));
                html.append("""
                        <li>
                        """);
                html.append(education.getStartDate());
                html.append(" - ");
                if (education.getEndDate() == null)
                    html.append("<cite>Present</cite>");
                else
                    html.append(education.getEndDate());
                html.append("""
                        </li>
                        """);
                html.append(String.format("<li>%s</li>", education.getGrade()));
                html.append("""
                         </ul>
                                  </figure>
                        """);
            }
            html.append("""
                    </div>
                    """);
        }

        if (this.getCv().getProjects() != null && this.getCv().getProjects().size() != 0) {
            html.append("""
                     <hr />
                          <div>
                            <div>
                              <h3 class="left-margin"><em>Projects</em></h3>
                            </div>
                            <hr />
                          </div>
                    """);
            for (Project project : this.getCv().getProjects()) {
                html.append("""
                                  <figure>
                        """);
                html.append(project.getName());
                html.append(String.format("<ul><li>%s</li></ul>", project.getDetails()));
                html.append("""
                        </figure>
                        """);
            }

        }

        if (this.getCv().getWorkExperiences() != null && this.getCv().getWorkExperiences().size() != 0) {
            html.append("""
                    <hr />
                          <div>
                            <div>
                              <h3 class="left-margin"><em>Work Experience</em></h3>
                            </div>
                            <hr />
                          </div>
                    """);
            for (WorkExperience workExperience : this.getCv().getWorkExperiences()) {
                html.append("""
                                  <figure>
                        """);
                html.append(workExperience.getRole());
                html.append("""
                        <ul>
                        """);
                html.append(String.format("<li>%s</li>", workExperience.getInstitute()));
                html.append(String.format("<li>%s - ", workExperience.getStartDate()));
                if (workExperience.getEndDate() == null)
                    html.append("<cite>Present</cite></li>");
                else
                    html.append(String.format("%s</li>", workExperience.getEndDate()));
                html.append(String.format("<li>%s</li>", workExperience.getDetails()));
                html.append("""
                        </ul>
                        """);

                html.append("""
                        </figure>
                        """);
            }
        }
        if (this.getCv().getLanguages() != null && this.getCv().getLanguages().size() != 0) {
            html.append("""
                    <hr />
                          <div>
                            <div>
                              <h3 class="left-margin"><em>Languages</em></h3>
                            </div>
                            <hr />
                          </div>
                          <figure>
                    """);
            for (Language language : this.getCv().getLanguages()) {
                html.append(String.format("<span class=\"bottom-margin\">%s</span>&nbsp;",
                        language.getName()));
            }
            html.append("""
                     </figure>
                    """);
        }

        if (this.getCv().getSkills() != null && this.getCv().getSkills().size() != 0) {
            html.append("""
                     <hr />
                          <div>
                            <div>
                              <h3 class="left-margin"><em>Skills</em></h3>
                            </div>
                            <hr />
                          </div>
                          <figure>
                    """);
            for (Skill skill : this.getCv().getSkills()) {
                html.append(String.format("<span class=\"bottom-margin\">%s</span>&nbsp;",
                        skill.getName()));
            }
            html.append("""
                    </figure>
                    """);
        }
        if (this.getCv().getInterests() != null && this.getCv().getInterests().size() != 0) {
            html.append("""
                     <hr />
                                        
                          <div>
                            <div>
                              <h3 class="left-margin"><em>Interests</em></h3>
                            </div>
                            <hr />
                          </div>
                                        
                          <figure>
                    """);
            for (Interest interest : this.getCv().getInterests()) {
                html.append(String.format("<span class=\"bottom-margin\">%s</span>&nbsp;",
                        interest.getName()));
            }
            html.append("""
                    </figure>
                    """);
        }
        html.append("""
                </div>
                  </body>
                </html>
                """);
        return html.toString();
    }

    public byte[] generatePDF(String resourcesPath) throws IOException {
        String generatedHTML = this.generateHTML();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ConverterProperties properties = new ConverterProperties();
        FontProvider fontProvider = new DefaultFontProvider();
        String fontsPath = resourcesPath.concat("/fonts/");
        List<String> fonts = List.of("Segoe UI.ttf", "Segoe UI Bold Italic.ttf", "Segoe UI Bold.ttf",
                "Segoe UI Italic.ttf");
        for (String fontFile : fonts) {
            try (InputStream inputStream = new FileInputStream(fontsPath.concat(fontFile))) {
                fontProvider.addFont(inputStream.readAllBytes());
            }
        }
        properties.setFontProvider(fontProvider);
        HtmlConverter.convertToPdf(generatedHTML, outputStream, properties);
        return outputStream.toByteArray();
    }

    private CV getCv() {
        return cv;
    }
}
