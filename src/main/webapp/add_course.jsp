<%@page import="studentregistration.utils.Result"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--     <%
    if(session.getAttribute("isLogin")!=null) {
    	if((int)session.getAttribute("userRole") != 3) {
            
        } else {
            session.setAttribute("result", new Result(1, "Invalid route"));
            response.sendRedirect("login");
        }
	}else {
		session.setAttribute("result", new Result(1, "Invalid rout"));
		response.sendRedirect("login");
	}
    %> --%>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="head.jsp"/>
<body>
<jsp:include page="nav.jsp"/>
    <div class="container">
      <div class="main_contents">
    <div id="sub_content">
    <form action="add_course" method="post">
        <h2 class="col-md-6 offset-md-2 mb-5 mt-4">Course Registration</h2>
        <div class="row mb-4">
            <div class="col-md-2"></div>
        </div>
        <p>${error}</p>
        <div class="row mb-4">
            <div class="col-md-2"></div>
            <label for="name" class="col-md-2 col-form-label">Name</label>
            <div class="col-md-4">
                <input type="text" class="form-control" id="name" name="name">
            </div>
        </div>
            <div class="col-md-6">
                <button type="submit" class="btn btn-secondary col-md-2" data-bs-toggle="modal" data-bs-target="#exampleModal">Add</button>
        </div>
        </form>
    </div>
</div>
</div>
        <div id="testfooter">
            <span>Copyright &#169; ACE Inspiration 2022</span>
        </div>
</body>

</html>