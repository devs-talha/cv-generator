<%@ page isErrorPage="true"%>
<html lang="en">
  <link rel="stylesheet" href="css/bootstrap.css" />
  <link rel="stylesheet" href="css/global.css" />

  <script src="js/popper.js" />
  </script>
  <script src="js/bootstrap.js"></script>
<body>
     <div class="container">
          <div class="row d-flex w-100 justify-content-center">
        <div class="col-12">
      <div class="card text-danger mb-3">
      <div class="card-header">Error</div>
        <div class="card-body">
            <% if (exception != null) {
                  out.println(exception);
                  }
                else if (request.getParameterMap().containsKey("error"))
                  out.println(request.getParameter("error"));
                else if (request.getAttribute("exception") != null) {
                  Exception requestException = (Exception) request.getAttribute("exception");
                  out.println(requestException);
                  }
                else 
                  out.println("An unknown error occured");
                   %>
          </div>
          </div>
          </div>
        </div>
      </div>
</body>

</html>