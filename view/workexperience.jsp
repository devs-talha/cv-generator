<%@ page import="com.github.tm_425006.webproject.bean.CV"%>
<%@ page import="com.github.tm_425006.webproject.bean.WorkExperience"%>
<%@ page errorPage="error.jsp"%>

<html lang="en">

<head>
  <meta charset="UTF-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <link rel="stylesheet" href="css/bootstrap.css" />
  <link rel="stylesheet" href="css/global.css" />
  <script src="js/popper.js" />
  </script>
  <script src="js/bootstrap.js"></script>
  <script src="js/workexperience.js"></script>
</head>

<body>
<jsp:useBean id="user" scope="session" class="com.github.tm_425006.webproject.bean.User" />

<%
    if (user == null || user.getId() == null)  {
      response.sendRedirect("login.jsp?error=Not logged in");
      return;
      } 
  %>

   <div class="card mb-3">
        <div class="card-body">
          <div class="container">
            <h5 class="card-title mb-3">Work Experience</h5>
            <div class="h-100 w-100" id="workExperiences">
              <!-- Work Experiences will be inserted here -->
              <%
                CV cv = (CV)request.getAttribute("cv");
                if (cv != null && cv.getWorkExperiences() != null && cv.getWorkExperiences().size() != 0) {
                  for (WorkExperience workExperience : cv.getWorkExperiences()) {
              %>
              <div class="card mb-3" id='<%="workExperienceComponentId" + workExperience.getId()%>'>
                <div class="card-body">
                  <div class="container">
                    <div class="row">
                      <input type="hidden" name="workExperienceId" value='<%=workExperience.getId()%>' />
                      <input type="hidden" name="workExperienceCvId" value='<%=workExperience.getCvId()%>' />
                      <div class="col-md-6 mb-3">
                        <div class="form-floating">
                          <input type="text" class="form-control" name="workExperienceRole" required
                          value='<%=workExperience.getRole()%>'
                          />
                          <label for="workExperienceRole">Role</label>
                        </div>
                      </div>
                      <div class="col-md-6 mb-3">
                        <div class="form-floating">
                          <input type="text" class="form-control" name="workExperienceInstitute" required
                          value='<%=workExperience.getInstitute()%>'
                          />
                          <label for="workExperienceInstitute">Institute Name</label>
                        </div>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-md-6 mb-3">
                        <div class="form-floating">
                          <input type="date" class="form-control" name="workExperienceStartDate" required
                          value='<%=workExperience.getStartDate()%>'
                          />
                          <label for="workExperienceStartDate">Start Date</label>
                        </div>
                      </div>
                      <div class="col-md-6 mb-3">
                        <div class="form-floating">
                          <input type="date" class="form-control" name="workExperienceEndDate" 
                          value='<% if (workExperience.getEndDate() != null) out.print(workExperience.getEndDate());%>'
                          />
                          <label for="workExperienceEndDate">End Date</label>
                        </div>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-12">
                        <div class="form-floating">
                          <textarea class="form-control" name="workExperienceDetails" required
                          ><% out.print(workExperience.getDetails()); %></textarea>
                          <label for="workExperienceDetails">Details</label>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <button type="button"
                  class="opacity-0 hover-opacity-1 position-absolute top-0 start-100 translate-middle badge rounded-pill btn btn-danger"
                  onclick=<%out.print(String.format("removeElement('workExperienceComponentId%s')", workExperience.getId()));%>
                  >
                  x
                </button>
              </div>
              <% }} %>
            </div>
          </div>
          <div class="mb-3">
            <div class="d-flex justify-content-end">
              <button type="button" class="btn btn-primary" 
              onclick=<%
              if (cv == null) {
                        out.print("addWorkExperience()");
              } else {
                        out.print(String.format("addWorkExperience(%s)", cv.getId()));
              }
              %>
              >Add</button>
            </div>
          </div>
        </div>
      </div>
</body>

</html>