<%@ page import="com.github.tm_425006.webproject.bean.CV"%>
<%@ page import="com.github.tm_425006.webproject.bean.Skill"%>
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
  <script src="js/skill.js"></script>
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
            <h5 class="card-title mb-3">Skills</h5>
            <div class="row" id="skills">
              <!-- Skills will be inserted here -->
               <%
                CV cv = (CV)request.getAttribute("cv");
                if (cv != null && cv.getSkills() != null && cv.getSkills().size() != 0) {
                  for (Skill skill : cv.getSkills()) {
              %>
                <div class="col-4 col-md-2 mb-3" id='<%="skillComponentId" + skill.getId()%>'>
              <input type="hidden" name="skillId" value='<%=skill.getId()%>' />
              <input type="hidden" name="skillCvId" value='<%=skill.getCvId()%>' />
                <div class="form-floating">
                  <input type="text" class="form-control position-relative" name="skillName" required
                  value='<%=skill.getName()%>'
                  />
                  <button type="button"
                    class="opacity-0 hover-opacity-1 position-absolute top-0 start-100 translate-middle badge rounded-pill btn btn-danger"
                    onclick=<%out.print(String.format("removeElement('skillComponentId%s')", skill.getId()));%>
                    >
                    x
                  </button>
                  <label for="skillName">Name</label>
                </div>
              </div>
              <% }} %>
            </div>
          </div>
          <div class="mb-3">
            <div class="d-flex justify-content-end">
              <button type="button" class="btn btn-primary" 
              onclick=<%
              if (cv == null) {
                        out.print("addSkill()");
              } else {
                        out.print(String.format("addSkill(%s)", cv.getId()));
              }
              %>
              >Add</button>
            </div>
            <div tabindex="-1" class="text-danger mb-3" id="skillsHelper"></div>
          </div>
        </div>
      </div>

</body>

</html>