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
  <script src="js/changepassword.js"></script>
  <title>CV Generator - Change Password</title>
</head>

<body>

  <jsp:useBean id="user" scope="session" class="com.github.tm_425006.webproject.bean.User" />

    <%
      if (user == null || user.getId() == null) {
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
          <form class="mb-0 d-inline" method="POST" action="cvcontroller?action=getAll" >
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
        <div class="row">
          <center>
            <div class="col-md-4">
              <h2>Change Password</h2>
            </div>
          </center>
        </div>
      </div>

      <% if(request.getParameterMap().containsKey("error")) { %>
      <jsp:include page="message.jsp">
        <jsp:param name="error" value='<%=request.getParameter("error")%>' />
      </jsp:include>
      <% } else if(request.getParameterMap().containsKey("success")) { %>
      <jsp:include page="message.jsp">
        <jsp:param name="success" value='<%=request.getParameter("success")%>' />
      </jsp:include>
      <% } %>

    <form method="POST" action="usercontroller?action=changePassword" 
          onsubmit="return handleChangePassword()">
      <div class="mb-3">
        <div class="row">
          <center>
            <div class="col-md-4">
              <div class="form-floating">
                <input type="password" class="form-control" name="currentPassword" required/>
                <label for="currentPassword">Current Password</label>
              </div>
            </div>
          </center>
        </div>
      </div>

      <div class="mb-3">
        <div class="row">
          <center>
            <div class="col-md-4">
              <div class="form-floating">
                <input type="password" class="form-control" name="newPassword" required/>
                <label for="newPassword">New Password</label>
              </div>
            </div>
          </center>
        </div>
      </div>

      <div class="mb-3">
        <div class="row">
          <center>
            <div class="col-md-4">
              <div class="form-floating">
                <input type="password" class="form-control" name="confirmPassword" required/>
                <label for="confirmPassword">Confirm Password</label>
              </div>
            </div>
          </center>
        </div>
      </div>

      <div class="mb-3">
        <div class="row">
          <center>
            <div class="col-md-4">
              <button type="submit" class="btn btn-primary">Save</button>
            </div>
          </center>
        </div>
      </div>

    </form>
  </div>
</body>

</html>