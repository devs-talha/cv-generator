<%@ page import="com.github.tm_425006.webproject.bean.CV"%>
<%@ page import="com.github.tm_425006.webproject.bean.BasicInformation"%>
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
            <h5 class="card-title mb-3">Basic Information</h5>
            <div class="row">
              <div class="col-md-6 mb-3">
              <%
                CV cv = (CV)request.getAttribute("cv");
                if (cv != null && cv.getBasicInformation() != null) {
                  BasicInformation basicinformation = cv.getBasicInformation();
              %>
                <input type="hidden" name="basicInformationId" value='<%=basicinformation.getId()%>' />
                <input type="hidden" name="basicInformationCvId" value='<%=basicinformation.getCvId()%>' />
                <div class="form-floating">
                  <input type="text" class="form-control" name="basicInformationName" required
                  value='<%=basicinformation.getName()%>'/>
                  <label for="basicInformationName">Name</label>
                </div>
              </div>
              <div class="col-md-6 mb-3">
                <div class="form-floating">
                  <input type="date" class="form-control" name="basicInformationBirthDate" required/
                  value='<%=basicinformation.getBirthDate()%>'>
                  <label for="basicInformationBirthDate">Date of Birth</label>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-12">
                <div class="form-floating">
                  <textarea class="form-control" name="basicInformationAddress" required
                  ><%=basicinformation.getAddress()%></textarea>
                  <label for="basicInformationAddress">Address</label>
                </div>
              </div>
            </div>
                <% } else { %>
                <input type="hidden" name="basicInformationId" value="-1" />
                <input type="hidden" name="basicInformationCvId" value="-1" />
                <div class="form-floating">
                  <input type="text" class="form-control" name="basicInformationName" required
                  value='<%=user.getFirstName() + " " + user.getLastName()%>'/>
                  <label for="basicInformationName">Name</label>
                </div>
              </div>
              <div class="col-md-6 mb-3">
                <div class="form-floating">
                  <input type="date" class="form-control" name="basicInformationBirthDate" required/
                  value='<%=user.getBirthDate()%>'>
                  <label for="basicInformationBirthDate">Date of Birth</label>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-12">
                <div class="form-floating">
                  <textarea class="form-control" name="basicInformationAddress" required
                  ><%=user.getAddress()%></textarea>
                  <label for="basicInformationAddress">Address</label>
                </div>
              </div>
            </div>
                <% } %>
          </div>
        </div>
      </div>
</body>

</html>