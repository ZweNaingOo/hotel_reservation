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
    <div class="col-auto">
                <button type="button" class="btn btn-secondary " onclick="location.href = 'add_admin';">
                    Add admin
                </button>
            </div>
        <table class="table table-striped" id="stduentTable">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">ID</th>
                    <th scope="col">Name</th>
                    <th scope="col">Email</th>
                    <th scope="col">Registration date</th>
                    <th scope="col">Role</th>
                    <th scope="col">Status</th>
                    <th scope="col">Action</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${users}" varStatus="loop">
                <tr>
                    <td>${loop.index+1}.</td>
                    <td><c:choose><c:when test="${user.role==2}">UA${userId}</c:when><c:when test="${user.role==3}">UU${user.id}</c:when></c:choose></td>
                    <td>${user.name}</td>
                    <td>${user.email}</td>
                    <td>${user.date}</td>
                    <td><c:choose><c:when test="${user.role==2}">Admin</c:when><c:when test="${user.role==3}">User</c:when></c:choose></td>
                    <td>
                    	<c:choose>
                    		<c:when test="${user.status==0}">
                    			Unverified
                    		</c:when>
                    		<c:when test="${user.status==1}">
                    			Active
                    		</c:when>
                    		<c:when test="${user.status==2}">
                    			Banned
                    		</c:when>
                    	</c:choose>
                    </td>
                	<td>
                    	<c:choose>
                    		<c:when test="${user.status==1}">
                    			<button type="button" class="btn btn-success" onclick="location.href = 'disable_user?id=${user.id}';">
                        			Disable
                    			</button>
                    		</c:when>
                    		<c:when test="${user.status==2}">
                    			<button type="button" class="btn btn-success" onclick="location.href = 'enable_user?id=${user.id}';">
                        			Enable
                    			</button>
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

