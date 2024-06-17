<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="head.jsp"/>
<body>
<jsp:include page="nav.jsp"/>
    <div class="container">
      <div class="main_contents">
    <div id="sub_content">
        <table class="table table-striped" id="stduentTable">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Name</th>
                    <th scope="col">Status</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="course" items="${courses}" varStatus="loop">
                <tr>
                    <td>${loop.index+1}.</td>
                    <td>${course.name}</td>
                    <td>
                    	<c:if test="${course.status==1}">
                    		Available
                    	</c:if>
                    	<c:if test="${course.status==0}">
                    		Unavailable
                    	</c:if>
                    </td>
                    <td>
                    	<c:choose>
                    		<c:when test="${userRole==1}">
                    			<c:if test="${course.status==1}">
                    				<a href="disable_course?id=${course.id}">Disable</a>
                    			</c:if>
                    			<c:if test="${course.status==0}">
                    				<a href="enable_course?id=${course.id}">Enable</a>
                    			</c:if>
                    		</c:when>
                    		<c:when test="${userRole==2}">
                    			<c:if test="${userId==course.user_id}">
                    				<c:if test="${course.status==1}">
                    					<a href="disable_course?id=${course.id}">Disable</a>
                    				</c:if>
                    				<c:if test="${course.status==0}">
                    					<a href="enable_course?id=${course.id}">Enable</a>
                    				</c:if>
                    			</c:if>
                    		</c:when>
                    	</c:choose>
                    </td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</div>
        <div id="testfooter">
            <span>Copyright &#169; ACE Inspiration 2022</span>
        </div>
</body>
</html>

