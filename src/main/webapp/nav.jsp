    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <jsp:include page="message.jsp"/>
    <div id="testheader">
        <div class="container">
            <div class=row>        
                <div class="col-md-5 ">
            <a href="welcome" style="text-size: 15px;">ACE Inspiration</a>
        </div>  
        <div class="col-md-6">
            <p>ID: AI - <c:choose><c:when test="${userRole==1}">Super Admin</c:when><c:when test="${userRole==2}">UA${userId}</c:when><c:when test="${userRole==3}">UU${userId}</c:when></c:choose></p>
            <p>Name: ${user_name}</p>
        </div>  
        <div class="col-md-1" >
        	<a href="logout" id="lgnout-button" onclick="return confimation('Are you sure you want to log out?')">Log Out</a>
        </div>        
    </div>
</div>
</div>
    <div class="sidenav">
    	<c:if test="${userRole==3}">
        	<a href="add_student">Student Registration</a>
        </c:if>
        <c:if test="${userRole!=3}">
        <button class="dropdown-btn" >Class Management<i class="fa fa-caret-down"></i></button>
        	<div class="dropdown-container">
          		<a href="add_student">Student Registration</a>
          		<a href="add_course">Course Registration</a>
        		<a href="courses_list">Course list</a>
          		<a href="students_list">Student Search</a>
        	</div>
        </c:if>
        <c:if test="${userRole==1}">
        	<a href="users_list">Users Management</a>
        </c:if>
      </div>
          <script src="resources/js/functions.js"></script>
                  <script>
            /* Loop through all dropdown buttons to toggle between hiding and showing its dropdown content - This allows the user to have multiple dropdowns without any conflict */
            var dropdown = document.getElementsByClassName("dropdown-btn");
            var i;
            
            for (i = 0; i < dropdown.length; i++) {
              dropdown[i].addEventListener("click", function() {
              this.classList.toggle("active");
              var dropdownContent = this.nextElementSibling;
              if (dropdownContent.style.display === "block") {
              dropdownContent.style.display = "none";
              } else {
              dropdownContent.style.display = "block";
              }
              });
            }
            </script>