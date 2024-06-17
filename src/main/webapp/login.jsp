<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp"/>
<body class="login-page-body">
<jsp:include page="message.jsp"/> 
    <div class="login-page">
      <div class="form">
        <div class="login">
          <div class="login-header">
            <h1>Welcome!</h1>
            <p>${error}</p>
          </div>
        </div>
        <form class="login-form" action="login" method="post">
          <input type="text" placeholder="Email" name="email" value="${email}"/>
          <input type="password" placeholder="Password" name="password" value="${password}"/>
          <button type="submit">Login</button>
          <p class="message">Not registered? <a href="register">Create an account</a></p>
        </form>
      </div>
    </div>
</body>
</html>