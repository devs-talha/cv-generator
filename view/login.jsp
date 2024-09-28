<%@ page errorPage="error.jsp"%>
<%@ page import="com.github.tm_425006.webproject.bean.User"%>
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
  <title>CV Generator - Login</title>
</head>

<body>
<jsp:useBean id="user" scope="session" class="com.github.tm_425006.webproject.bean.User" />

  <%
    if (user != null && user.getId() != null) {
      response.sendRedirect("index.jsp");
      return;
      }
  %>

  <div class="container-md">
 
      <div class="mb-3">
        <div class="row">
          <center>
            <div class="col-md-4">
              <h1>CV Generator</h1>
              <h2>Login</h2>
            </div>
          </center>
        </div>
      </div>


    <% if(request.getParameterMap().containsKey("error")) { %>
      <jsp:include page="message.jsp">
        <jsp:param name="error" value='<%=request.getParameter("error")%>' />
        <jsp:param name="small" value="true" />
      </jsp:include>
    <% } %>

   <form method="POST" action="usercontroller?action=login">
      <div class="mb-3">
        <div class="row">
          <center>
            <div class="col-md-4">
              <div class="form-floating">
                <input type="email" class="form-control" name="email" required/>
                <label for="email">Email</label>
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
                <input type="password" class="form-control" name="password" required/>
                <label for="password">Password</label>
              </div>
            </div>
          </center>
        </div>
      </div>


      <div class="mb-3">
        <div class="row">
          <center>
            <div class="col-md-4">
              <div class="d-flex justify-content-between">
                <a href="signup.jsp" class="link-primary text-decoration-none">Not Registered?</a>
                <button type="submit" class="btn btn-primary">Login</button>
              </div>
            </div>
          </center>
        </div>
      </div>
    </form>
  </div>
</body>

</html>