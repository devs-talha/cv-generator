<%@ page errorPage="error.jsp"%>
<html lang="en">
  <link rel="stylesheet" href="css/bootstrap.css" />
  <link rel="stylesheet" href="css/global.css" />

  <script src="js/popper.js" />
  </script>
  <script src="js/bootstrap.js"></script>
<body>
      <%
      if (request.getParameterMap().containsKey("error")) {
      %>
      <div class="container">
          <div class="row d-flex w-100 justify-content-center">
          <%
      if (request.getParameterMap().containsKey("small") && 
        Boolean.valueOf(request.getParameterMap().get("small")[0]) == true) {
      %>
            <div class="col-md-4">
      <%} else {%>
        <div class="col-12">
      <% } %>
      <div class="card text-danger mb-3">
      <div class="card-header">Error</div>
        <div class="card-body">
          
        <%
          out.println(request.getParameter("error"));
        %>
          </div>
          
          </div>
          </div>
        </div>
      </div>
      <%
        } else if (request.getParameterMap().containsKey("success")) {
      %>
      <div class="container">
          <div class="row d-flex w-100 justify-content-center">
           <% if (request.getParameterMap().containsKey("small") && 
        Boolean.valueOf(request.getParameterMap().get("small")[0]) == true) {
      %>
            <div class="col-md-4">
      <%} else {%>
        <div class="col-12">
      <% } %>
      <div class="card text-success mb-3">
      <div class="card-header">Success</div>
        <div class="card-body">
          
        <%
          out.println(request.getParameter("success"));
        %>
          </div>
          
          </div>
          </div>
        </div>
      </div>
      <%
        }
      %>
</body>

</html>