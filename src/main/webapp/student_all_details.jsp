<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Details</title>
    <link rel="stylesheet" href="resources/css/student_detail.css">
    <script src="resources/js/functions.js"></script>
</head>
<body>
    <div class="container">
        <h1>${student.name}'s details</h1>
        <div class="student-details">
            <div class="student-info">
                <h2>Student ID:</h2>
                <p>AI-S${student.id}</p>
                <h2>Name:</h2>
                <p>${student.name}</p>
                <h2>Date of Birth:</h2>
                <p>${student.dob}</p>
                <h2>Gender:</h2>
                <p><c:choose><c:when test="${student.gender==1}">Male</c:when><c:when test="${student.gender==0}">Female</c:when></c:choose></p>
            </div>
             <img src="data:image/jpeg;base64,${student.photo}" alt="Image">
        </div>
        <div class="student-details">
            <div class="student-info">
                <h2>Education:</h2>
                <p>
                	<c:choose>
                		<c:when test="${student.education==1}">
                			Bachelor of Information Technology
                		</c:when>
                		<c:when test="${student.education==2}">
                			Diploma in IT
                		</c:when>
                		<c:when test="${student.education==3}">
                			Bachelor of Computer Science
                		</c:when>
                	</c:choose> 
                </p>
                <h2>Phone</h2>
                <p>${student.phone}</p>
                <h2>Registration Date:</h2>
                <p>${student.date}</p>
                <h2>Courses</h2>
                <p>${student.courses}</p>
            </div>
        </div>
        <div class="student-details">
            <div class="student-info">
            	<c:choose>
            		<c:when test="${userRole==1}">
            			<a href="edit_student?id=${student.id}" class="buttons" id="edit">Edit</a>
                		<a href="delete_student?id=${student.id}" class="buttons" id="delete" onclick="return confimation('Are you sure to delete this student?')">Delete</a>
            		</c:when>
            		<c:when test="${userRole==2}">
            			<c:if test="${userId==student.user_id}">
            				<a href="edit_student?id=${student.id}" class="buttons" id="edit">Edit</a>
                			<a href="delete_student?id=${student.id}" class="buttons" id="delete" onclick="return confimation('Are you sure to delete this student?')">Delete</a>
            			</c:if>
            		</c:when>
            	</c:choose>
            </div>
        </div>
    </div>
</body>
</html>
