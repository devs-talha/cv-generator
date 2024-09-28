<%@ page import="com.github.tm_425006.webproject.bean.CV"%>
<%@ page import="com.github.tm_425006.webproject.bean.Language"%>
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
  <script src="js/language.js"></script>
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
            <h5 class="card-title mb-3">Languages</h5>
            <div class="row" id="languages">
              <!-- Languages will be inserted here -->
              <%
                CV cv = (CV)request.getAttribute("cv");
                if (cv != null && cv.getLanguages() != null && cv.getLanguages().size() != 0) {
                  for (Language language : cv.getLanguages()) {
              %>
              <div class="col-4 col-md-2 mb-3" id='<%="languageComponentId" + language.getId()%>'>
              <input type="hidden" name="languageId" value='<%=language.getId()%>' />
              <input type="hidden" name="languageCvId" value='<%=language.getCvId()%>' />
                <div class="form-floating">
                  <input type="text" class="form-control position-relative" name="languageName" required
                  value='<%=language.getName()%>'
                  />
                  <button type="button"
                    class="opacity-0 hover-opacity-1 position-absolute top-0 start-100 translate-middle badge rounded-pill btn btn-danger"
                    onclick=<%out.print(String.format("removeElement('languageComponentId%s')", language.getId()));%>
                    >
                    x
                  </button>
                  <label for="languageName">Name</label>
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
                        out.print("addLanguage()");
              } else {
                        out.print(String.format("addLanguage(%s)", cv.getId()));
              }
              %>
              >Add</button>
            </div>
            <div tabindex="-1" class="text-danger mb-3" id="languagesHelper"></div>
          </div>
        </div>
      </div>

</body>

</html>