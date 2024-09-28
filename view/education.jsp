<%@ page import="com.github.tm_425006.webproject.bean.CV"%>
<%@ page import="com.github.tm_425006.webproject.bean.Education"%>
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
  <script src="js/education.js"></script>
</head>

<body>
      <div class="card mb-3">
        <div class="card-body">
          <div class="container">
            <h5 class="card-title mb-3">Education</h5>
            <div class="h-100 w-100" id="educations">
              <!-- Education will be inserted here -->
              <%
                CV cv = (CV)request.getAttribute("cv");
                if (cv != null && cv.getEducations() != null && cv.getEducations().size() != 0) {
                  for (Education education : cv.getEducations()) {
              %>
               <div class="card mb-3" id='<%="educationComponentId" + education.getId()%>'>
              <div class="card-body">
                <div class="container">
                  <div class="row">
                  <input type="hidden" name="educationId" value='<%=education.getId()%>'>
                  <input type="hidden" name="educationCvId" value='<%=education.getCvId()%>'>
                    <div class="col-md-6 mb-3">
                      <div class="form-floating">
                        <input type="text" class="form-control" name="educationName" required
                        value='<%=education.getName()%>'
                        />
                        <label for="educationName">Degree Name</label>
                      </div>
                    </div>
                    <div class="col-md-6 mb-3">
                      <div class="form-floating">
                        <input type="text" class="form-control" name="educationInstitute" required
                        value='<%=education.getInstitute()%>'
                        />
                        <label for="educationInstitute">Institute Name</label>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-6 mb-3">
                      <div class="form-floating">
                        <input type="date" class="form-control" name="educationStartDate" required
                        value='<%=education.getStartDate()%>'
                        />
                        <label for="educationStartDate">Start Date</label>
                      </div>
                    </div>
                    <div class="col-md-6 mb-3">
                      <div class="form-floating">
                        <input type="date" class="form-control" name="educationEndDate"
                          value='<% if (education.getEndDate() != null) out.print(education.getEndDate());%>'
                        />
                        <label for="educationEndDate">End Date</label>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-6">
                      <div class="form-floating">
                        <input type="text" class="form-control" name="educationGrade" required
                        value='<%=education.getGrade()%>'
                        />
                        <label for="educationGrade">Grade</label>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <button type="button"
                class="opacity-0 hover-opacity-1 position-absolute top-0 start-100 translate-middle badge rounded-pill btn btn-danger"
                onclick=<%out.print(String.format("removeElement('educationComponentId%s')", education.getId()));%>
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
                        out.print("addEducation()");
              } else {
                        out.print(String.format("addEducation(%s)", cv.getId()));
              }
              %>
              >Add</button>
            </div>
          </div>
        </div>
      </div>
</body>

</html>