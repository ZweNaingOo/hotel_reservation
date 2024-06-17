<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="head.jsp"/>
<body>
<jsp:include page="nav.jsp"/>
    <div class="container">
      <div class="main_contents">
    <div id="sub_content">
<div>
      <table class="table table-striped" id="stduentTable">
        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">Student ID</th>
            <th scope="col">Name</th>
            <th scope="col">Course Name</th>
            <th scope="col">Details</th>
          </tr>
        </thead>
        <tbody>
        	<c:forEach var="student" items="${students}" varStatus="loop">
        		<tr>
        			<td>${loop.index+1}</td>
        			<td>AI-S${student.id}</td>
        			<td>${student.name}</td>
        			<td>${student.courses}</td>
        			<td><a href="all_details?id=${student.id}">See more</a></td>
        		</tr>
        	</c:forEach>
        </tbody>
      </table>
    </div>
    </div>
</div>
</div>
        <div id="testfooter">
            <span>Copyright &#169; ACE Inspiration 2022</span>
        </div>
</body>
</html>