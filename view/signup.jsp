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
  <script src="js/signup.js"></script>
  <title>CV Generator - Sign Up</title>
</head>

<body>
  <div class="container-md">

      <div class="mb-3">
        <div class="row">
          <center>
            <div class="col-md-4">
              <h1>CV Generator</h1>
              <h2>Sign Up</h2>
            </div>
          </center>
        </div>
      </div>

      <% if(request.getParameterMap().containsKey("error")) { %>
      <jsp:include page="message.jsp">
        <jsp:param name="error" value='<%=request.getParameter("error")%>' />
        <jsp:param name="small" value="true" />
      </jsp:include>
      <% } else if(request.getParameterMap().containsKey("success")) { %>
      <jsp:include page="message.jsp">
        <jsp:param name="success" value='<%=request.getParameter("success")%>' />
        <jsp:param name="small" value="true" />
      </jsp:include>
      <% } %>


    <form method="POST" action="usercontroller?action=signup" onsubmit="return handleSignup()">
      <input type="hidden" name="id" value="-1" />
      <div class="mb-3">
        <div class="row">
          <center>
            <div class="col-md-4">
              <div class="form-floating">
                <input type="text" class="form-control" name="firstName" required/>
                <label for="firstName">First Name</label>
              </div>
              <div id="firstNameHelper" class="form-text text-danger"></div>
            </div>
          </center>
        </div>
      </div>

      <div class="mb-3">
        <div class="row">
          <center>
            <div class="col-md-4">
              <div class="form-floating">
                <input type="text" class="form-control" name="lastName" required/>
                <label for="lastName">Last Name</label>
              </div>
              <div id="lastNameHelper" class="form-text text-danger"></div>
            </div>
          </center>
        </div>
      </div>

      <div class="mb-3">
        <div class="row">
          <center>
            <div class="col-md-4">
              <div class="form-floating">
                <input type="email" class="form-control" name="email" required/>
                <label for="email">Email</label>
              </div>
              <div id="emailHelper" class="form-text text-danger"></div>
            </div>
          </center>
        </div>
      </div>

      <div class="mb-3">
        <div class="row">
          <center>
            <div class="col-md-4">
              <div class="form-floating">
                <input type="password" class="form-control" name="password" required />
                <label for="password">Password</label>
              </div>
              <div id="passwordHelper" class="form-text text-danger"></div>
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
              <div id="confirmPasswordHelper" class="form-text text-danger"></div>
            </div>
          </center>
        </div>
      </div>

      <div class="mb-3">
        <div class="row">
          <center>
            <div class="col-md-4">
              <div class="form-floating">
                <input type="date" class="form-control" name="birthDate" required />
                <label for="birthDate">Date of Birth</label>
              </div>
              <div id="dateOfBirthHelper" class="form-text text-danger"></div>
            </div>
          </center>
        </div>
      </div>

      <div class="mb-3">
        <div class="row">
          <center>
            <div class="col-md-4">
              <div class="form-floating">
                <textarea class="form-control" name="address" required> </textarea>
                <label for="address">Address</label>
              </div>
              <div id="addressHelper" class="form-text text-danger"></div>
            </div>
          </center>
        </div>
      </div>

      <div class="mb-3">
        <div class="row">
          <center>
            <div class="col-md-4">
              <div class="d-flex justify-content-between">
                <a href="login.jsp" class="link-primary text-decoration-none">Already Registered?</a>
                <button type="submit" class="btn btn-primary">Register</button>
              </div>
            </div>
          </center>
        </div>
      </div>

    </form>
  </div>
</body>

</html>