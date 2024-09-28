<%@ page errorPage="error.jsp"%>
<% 
if (request.getAttribute("preview") != null) {
    out.print(request.getAttribute("preview")); 
}%>