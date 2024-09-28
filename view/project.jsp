<%@ page import="com.github.tm_425006.webproject.bean.CV"%>
<%@ page import="com.github.tm_425006.webproject.bean.Project"%>
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
  <script src="js/project.js"></script>
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
          <h5 class="card-title mb-3">Projects</h5>
          <div class="h-100 w-100" id="projects">
            <!-- Projects will be inserted here -->
             <%
                CV cv = (CV)request.getAttribute("cv");
                if (cv != null && cv.getProjects() != null && cv.getProjects().size() != 0) {
                  for (Project project : cv.getProjects()) {
              %>
                <div class="card mb-3" id='<%="projectComponentId" + project.getId()%>'>
                <div class="card-body">
                  <div class="container">
                    <div class="row">
                    <input type="hidden" name="projectId" value='<%=project.getId()%>' />
                    <input type="hidden" name="projectCvId" value='<%=project.getCvId()%>' />
                      <div class="col-12 mb-3">
                        <div class="form-floating">
                          <input type="text" class="form-control" name="projectName" required
                          value='<%=project.getName()%>'
                          />
                          <label for="projectName">Name</label>
                        </div>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-12">
                        <div class="form-floating">
                          <textarea class="form-control" name="projectDetails" required
                          ><%out.print(project.getDetails());%></textarea>
                          <label for="projectDetails">Details</label>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <button type="button"
                  class="opacity-0 hover-opacity-1 position-absolute top-0 start-100 translate-middle badge rounded-pill btn btn-danger"
                  onclick=<%out.print(String.format("removeElement('projectComponentId%s')", project.getId()));%>
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
                        out.print("addProject()");
              } else {
                        out.print(String.format("addProject(%s)", cv.getId()));
              }
              %>
            >Add</button>
          </div>
        </div>
      </div>
    </div>
</body>

</html>