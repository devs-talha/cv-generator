<%@ page import="com.github.tm_425006.webproject.bean.User"%>
<%@ page import="com.github.tm_425006.webproject.bean.CV"%>
<%@ page import="java.util.ArrayList"%>
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
  <title>CV Generator - Home</title>
</head>

<body>

  <jsp:useBean id="user" scope="session" class="com.github.tm_425006.webproject.bean.User" />

  <%
    if (user == null || user.getId() == null) {
      response.sendRedirect("login.jsp");
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

  <div class="container-md">
      <div class="mb-3 mt-3">
        <div class="d-flex justify-content-end">
          <form class="mb-0 d-inline" method="POST" action="cvcontroller?action=getAll">
            <button type="submit" class="btn btn-secondary me-3">Reload</button>
          </form>
          <a href="cv.jsp" class="btn btn-primary">Create CV</a>
        </div>
      </div>

    <%  ArrayList<CV> cvList =  (ArrayList<CV>) request.getAttribute("cvList");
      if (cvList != null && cvList.size() != 0) {
        int i = 0;
        for (CV cv : cvList) {
      %>
        <div class="mb-3">
          <div class="card">
            <div class="card-body">
              <div class="container">
                <div class="d-flex v-100 justify-content-between">
                  <div>
                    <h6 class="card-title d-inline me-3"><%= ++i %>.</h6>
                    <h6 class="card-title d-inline me-3">Creation Date: <span><%= cv.getCreatedDate() %></span></h6>
                    <% if (cv.getModifiedDate() != null) { %>
                    <h6 class="card-title d-inline">Modified Date: <span><%= cv.getModifiedDate() %> </span></h6>
                    <% } %>
                  </div>  
                  <div>
                  <form class="mb-0 d-inline" method="POST" action="cvcontroller?action=preview">
                    <input type="hidden" name="id" value="<%= cv.getId() %>" />
                    <button type="submit" class="btn btn-primary me-3 mb-3">View</button>
                  </form>
                  <form class="mb-0 d-inline" method="POST" action="cvcontroller?action=download">
                    <input type="hidden" name="id" value="<%= cv.getId() %>" />
                    <button type="submit" class="btn btn-primary me-3 mb-3">Download</button>
                  </form>
                  <form class="mb-0 d-inline" method="POST" action="cvcontroller?action=edit">
                    <input type="hidden" name="id" value="<%= cv.getId() %>" />
                    <button type="submit" class="btn btn-primary me-3 mb-3">Edit</button>
                  </form>
                  <form class="mb-0 d-inline" method="POST" action="cvcontroller?action=delete">
                    <input type="hidden" name="id" value="<%= cv.getId() %>" />
                    <button type="submit" class="btn btn-danger mb-3" onclick="return confirm('Confirm delete the CV?');">Delete</button>
                  </form>
                </div>
                </div>
              </div>
            </div>
          </div>
        </div>
    <% } } %>

  </div>
</body>

</html>