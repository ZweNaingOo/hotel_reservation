<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="resources/css/test.css">

<title> Student Registration LGN001 </title>
</head>
<body class="login-page-body"> 
  <jsp:include page="message.jsp"/>
    <div class="login-page">
      <div class="form">
        <div class="login">
          <div class="login-header">
            <h1>Welcome!</h1>
            <p id="error">${error}</p>
          </div>
        </div>
        <form class="login-form" action="register" method="post" id="form">
          <input type="text" placeholder="Name" name="name" required value="${user.name}"/>
          <input type="email" placeholder="Email" name="email" required value="${user.email}"/>
          <input type="password" placeholder="Password" name="password" id="password" required value="${user.password}"/>
          <input type="password" placeholder="Confirm Password" id="confirmPassword" name="confirmPassword" required value="${confirmPassword}"/>
          <button type="submit" id="submit">Sign up</button>
          	<input type="hidden" value="<c:if test="${admin}">2</c:if><c:if test="${admin==null}">3</c:if>" name="role">
          <c:if test="${!admin}">
          	<p class="message">Already have an account? <a href="login">Login</a></p>
          </c:if>
        </form>
      </div>
    </div>
    <script src="resources/js/functions.js"></script>
</body>
</html>