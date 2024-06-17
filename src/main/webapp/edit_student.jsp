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
        <form action="edit_student" method="post" enctype="multipart/form-data">
            <input type="hidden" value="${id}" name="id">
            <h2 class="col-md-6 offset-md-2 mb-5 mt-4">Edit Student Data</h2>
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="name" class="col-md-2 col-form-label">Name</label>
                <div class="col-md-4">
                    <input type="text" class="form-control" id="name" value="${student.name}" name="name" required>
                </div>
            </div>
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="dob" class="col-md-2 col-form-label">DOB</label>
                <div class="col-md-4">
                    <input type="date" class="form-control" id="dob" name="dob" value="${student.dob}" required>
                </div>
            </div>
            <fieldset class="row mb-4">
                <div class="col-md-2"></div>
                <legend class="col-form-label col-md-2 pt-0">Gender</legend>
                    <div class="col-md-4">
                    	<div class="form-check-inline">
                        	<input class="form-check-input" type="radio" name="gender" id="gridRadios1" value="1" <c:if test="${student.gender==1}">checked</c:if>>
                        	<label class="form-check-label" for="gridRadios1">
                            	Male
                        	</label>
                    	</div>
                    	<div class="form-check-inline">
                        	<input class="form-check-input" type="radio" name="gender" id="gridRadios2" value="0" <c:if test="${student.gender==0}">checked</c:if>>
                        	<label class="form-check-label" for="gridRadios2">
                            	Female
                        	</label>
                    	</div>
                	</div>
            </fieldset>
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="phone" class="col-md-2 col-form-label">Phone</label>
                <div class="col-md-4">
                    <input type="text" class="form-control" id="phone" name="phone" value="${student.phone}" oninput="onlyNumber(this)">
                </div>
            </div>
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="education" class="col-md-2 col-form-label">Education</label>
                <div class="col-md-4">
                    <select class="form-select" aria-label="Education" id="education" name="education" required>
                		<option value="1" <c:if test="${student.education==1}">selected</c:if>>Bachelor of Information Technology</option>
                        <option value="2" <c:if test="${student.education==2}">selected</c:if>>Diploma in IT</option>
                      	<option value="3" <c:if test="${student.education==3}">selected</c:if>>Bachelor of Computer Science</option>      
                    </select>
                </div>
            </div>
            <fieldset class="row mb-4">
                        <div class="col-md-2"></div>
                        <legend class="col-form-label col-md-2 pt-0">Attend</legend>
                        <div class="col-md-4">
                            <c:forEach items="${courses}" var="course">
                                <div class="form-check-inline col-md-5">
                                    <input class="form-check-input" type="checkbox" name="courseIds" id="gridCheck${course.id}" value="${course.id}"  <c:if test="${courseIds.contains(course.id)}">checked</c:if>>
                                    <label class="form-check-label" for="gridCheck${course.id}" >${course.name}</label>
                                </div>
                            </c:forEach>
                            <div id="attendError" class="text-danger">${error}</div>
                        </div>
                    </fieldset>
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="name" class="col-md-2 col-form-label">Photo</label>
                <div class="col-md-4">
                	<input type="file" name="image" class="form-control" accept="image/*" id="imageInput">
                	 <img src="data:image/jpeg;base64,${student.photo}" alt="Image Preview" id="imagePreviewImg">
                </div>
            </div>
            <div class="row mb-4">
                <div class="col-md-4">
                    <button type="reset" class="btn btn-danger ">
                        Reset
                    </button>
                    <button type="submit" class="btn btn-secondary col-md-2" data-bs-toggle="modal" data-bs-target="#exampleModal">
                        Comfirm
                    </button>
                </div>
            </div>
            </form>
    </div>
</div>
</div>
        <div id="testfooter">
            <span>Copyright &#169; ACE Inspiration 2022</span>
        </div>
        <script>
        document.getElementById('imageInput').addEventListener('change', function(event) {
            const file = event.target.files[0];
            if (file) {
                const reader = new FileReader();
                reader.onload = function(e) {
                    const image = document.getElementById('imagePreviewImg');
                    image.src = e.target.result;
                    image.style.display = 'block';
                };
                reader.readAsDataURL(file);
            }
        });
        </script>
</body>

</html>

