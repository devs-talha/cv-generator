<jsp:directive.page language="java" contentType="text/html" import="com.github.tm_425006.webproject.bean.User" />
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
  <script src="js/jquery.js"></script>
  <script src="js/bootstrap.js"></script>
  <title>CV Generator</title>
</head>

<body>
  <%
    if (request.getSession(false) == null) {
      response.sendRedirect("/login.jsp?error=sessionInvalid");
      return;
      }
  %>


  <jsp:useBean id="user" scope="session" class="com.github.tm_425006.webproject.bean.User" />

  <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <div class="container-md">
      <a class="navbar-brand" href="index.jsp">
        CV Generator
      </a>
      <div class="d-flex">
        <ul class="navbar-nav">
          <li class="nav-item">
            <a class="nav-link active" href="#">Home</a>
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
                <li><a class="dropdown-item" href="account.jsp">Account</a></li>
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
</body>

</html>