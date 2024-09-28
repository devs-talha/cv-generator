<%@ page import="com.github.tm_425006.webproject.bean.CV"%>
<%@ page import="com.github.tm_425006.webproject.bean.User"%>
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
  <script src="js/cv.js"></script>
  <script src="js/global.js"></script>
  <title>CV Generator</title>
</head>

<body>

  <jsp:useBean id="user" scope="session" class="com.github.tm_425006.webproject.bean.User" />

<%
    if (user == null || user.getId() == null)  {
      response.sendRedirect("login.jsp?error=Not logged in");
      return;
      } 
  %>

  <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <div class="container-md">
      <div class="navbar-brand">
        CV Generator
      </div>
      
      <div class="d-flex">
        <ul class="navbar-nav">
          <li class="nav-item">
          <form class="mb-0 d-inline" method="POST" action="cvcontroller?action=getAll">
            <button type="submit" class="btn btn-primary">Home</button>
          </form>
          </li>
        </ul>
        <ul class="navbar-nav">
          <li class="nav-item">
            <div class="dropdown">
              <a class="btn btn-primary dropdown-toggle" role="button" id="dropdownMenuButton"
                data-bs-toggle="dropdown">
                <jsp:getProperty name="user" property="firstName" />   
              </a>
              <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                 <li><a class="dropdown-item" href="updateaccount.jsp">Update Account</a></li>
                <li><a class="dropdown-item" href="changepassword.jsp">Change Password</a></li>
                <li>
                  <hr class="dropdown-divider">
                </li>
                
                <li><form method="POST" action="usercontroller?action=logout">
                <button type="submit" class="dropdown-item" >Logout</button></form></li>
              </ul>
            </div>
          </li>
        </ul>
      </div>
    </div>
  </nav>


  <div class="container-md mt-3">

    <% if(request.getParameterMap().containsKey("error")) { %>
      <jsp:include page="message.jsp">
        <jsp:param name="error" value='<%=request.getParameter("error")%>' />
      </jsp:include>
      <% } else if(request.getParameterMap().containsKey("success")) { %>
      <jsp:include page="message.jsp">
        <jsp:param name="success" value='<%=request.getParameter("success")%>' />
      </jsp:include>
      <% } %>

      <% CV cv = (CV)request.getAttribute("cv"); %>

      <div class="d-flex justify-content-between mb-3">
      <form class="mb-0 d-inline" name="getAllForm" method="POST" action="cvcontroller?action=getAll">
        <button type="submit" id="backButton" class="btn btn-secondary">Back</button>
    </form>
        <div>
          <form class="mb-0 d-inline me-3" method="POST" action="cvcontroller?action=delete">
            <input type="hidden" name="id" value=<% if (cv != null && cv.getId() != null) {
                                                      out.println(cv.getId());
                                                    } else {
                                                      out.println("-1");
                                                    } %> />
            <button type="submit" class="btn btn-danger" onclick="return handleDelete();">Delete</button>
          </form>
          <form class="mb-0 d-inline" method="POST" action="cvcontroller?action=preview">
            <input type="hidden" name="id" value=<% if (cv != null && cv.getId() != null) {
                                              out.println(cv.getId());
                                            } else {
                                              out.println("-1");
                                            } %> /> 
          <button type="submit" class="btn btn-secondary" onclick="return handlePreview();">Preview</button>
          </form>
        </div>
      </div>

    <form name="saveForm" class="mb-0 d-inline" method="POST" action="cvcontroller?action=save">
      <div class="d-flex justify-content-end mb-3">
          <button type="submit" class="btn btn-primary" onclick="return handleSave();">Save</button>
      </div>

      <input type="hidden" name="id" value=<%
                if (cv != null && cv.getId() != null) {
                  out.println(cv.getId());
                } else {
                  out.println("-1");
                }
              %> />
      <input type="hidden" name="userId" value='<%=user.getId()%>'/>
     

      <jsp:include page="basicinformation.jsp" />

      <jsp:include page="contactinformation.jsp" />
    
      <jsp:include page="education.jsp" />

      <jsp:include page="certificate.jsp" />

      <jsp:include page="workexperience.jsp" />

      <jsp:include page="project.jsp" />
     
      <jsp:include page="skill.jsp" />
      
      <jsp:include page="interest.jsp" />

      <jsp:include page="language.jsp" />

    </form>
  </div>
</body>

</html>