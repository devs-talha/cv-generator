<%@ page import="com.github.tm_425006.webproject.bean.CV"%>
<%@ page import="com.github.tm_425006.webproject.bean.ContactInformation"%>
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
  <script src="js/contactinformation.js"></script>
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
            <h5 class="card-title mb-3">Contact Information</h5>
            <div class="h-100 w-100" id="contactInformations">
              <!-- Contact Information will be inserted here -->
               <%
                CV cv = (CV)request.getAttribute("cv");
                if (cv != null && cv.getContactInformationList() != null && cv.getContactInformationList().size() != 0) {
                  for (ContactInformation contactInformation : cv.getContactInformationList()) {
              %>
              <div class="card mb-3" id='<%="contactInformationComponentId" + contactInformation.getId()%>'>
              <div class="card-body">
                <div class="container">
                  <div class="row">
                  <input type="hidden" name="contactInformationId" value='<%=contactInformation.getId()%>'>
                  <input type="hidden" name="contactInformationCvId" value='<%=contactInformation.getCvId()%>'>
                    <div class="col-6">
                      <div class="form-floating">
                        <input type="text" class="form-control" name="contactInformationName" required
                        value='<%=contactInformation.getName()%>'
                        />
                        <label for="contactInformationName">Name</label>
                      </div>
                    </div>
                    <div class="col-6">
                      <div class="form-floating">
                        <input type="text" class="form-control" name="contactInformationUrl" required
                        value='<%=contactInformation.getUrl()%>'
                        />
                        <label for="contactInformationUrl">URL</label>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <button type="button"
                class="opacity-0 hover-opacity-1 position-absolute top-0 start-100 translate-middle badge rounded-pill btn btn-danger"
                onclick=<%out.print(String.format("removeElement('contactInformationComponentId%s')", contactInformation.getId()));%>
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
                        out.print("addContactInformation()");
              } else {
                        out.print(String.format("addContactInformation(%s)", cv.getId()));
              }
              %>
              >Add</button>
            </div>
          </div>
        </div>
      </div>
</body>

</html>