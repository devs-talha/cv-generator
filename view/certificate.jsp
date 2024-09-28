<%@ page errorPage="error.jsp"%>
<%@ page import="com.github.tm_425006.webproject.bean.CV"%>
<%@ page import="com.github.tm_425006.webproject.bean.Certificate"%>
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
  <script src="js/certificate.js"></script>
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
            <h5 class="card-title mb-3">Certificates</h5>
            <div class="h-100 w-100" id="certificates">
              <!-- Certificates will be inserted here -->
              <%
                CV cv = (CV)request.getAttribute("cv");
                if (cv != null && cv.getCertificates() != null && cv.getCertificates().size() != 0) {
                  for (Certificate certificate : cv.getCertificates()) {
              %>
                <div class="card mb-3" id='<%="certificateComponentId" + certificate.getId()%>'>
                  <div class="card-body">
                    <div class="container">
                      <div class="row">
                        <input type="hidden" name="certificateId" value='<%=certificate.getId()%>' />
                        <input type="hidden" name="certificateCvId" value='<%=certificate.getCvId()%>' />
                        <div class="col-md-6 mb-3">
                          <div class="form-floating">
                            <input type="text" class="form-control" name="certificateName" required
                            value='<%=certificate.getName()%>'
                            />
                            <label for="certificateName">Certificate Name</label>
                          </div>
                        </div>
                        <div class="col-md-6 mb-3">
                          <div class="form-floating">
                            <input type="text" class="form-control" name="certificateInstitute" required
                            value='<%=certificate.getInstitute()%>'
                            />
                            <label for="certificateInstitute">Institute Name</label>
                          </div>
                        </div>
                      </div>
                      <div class="row">
                        <div class="col-md-6">
                          <div class="form-floating">
                            <input type="date" class="form-control" name="certificateCompletionDate" required
                            value='<%=certificate.getCompletionDate()%>'
                            />
                            <label for="certificateCompletionDate">Completion Date</label>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                  <button type="button"
                    class="opacity-0 hover-opacity-1 position-absolute top-0 start-100 translate-middle badge rounded-pill btn btn-danger"
                    onclick=<%out.print(String.format("removeElement('certificateComponentId%s')", certificate.getId()));%>
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
                        out.print("addCertificate()");
              } else {
                        out.print(String.format("addCertificate(%s)", cv.getId()));
              }
              %>
            >Add</button>
            </div>
          </div>
        </div>
      </div>
</body>

</html>