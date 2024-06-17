<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
	#message-box {
			z-index: 21;
            position: fixed;
            top: -5%;
            left: 50%;
            transform: translate(-50%);
            background-color: blue;
            color: #ffffff;
            padding: 10px 20px;
            border-radius: 5px;
            font-size: 16px;
            opacity: 0;
            animation: fadeInOut 5s forwards;
        }
 	@keyframes fadeInOut {
            0% { opacity: 0;top: 5%; }
            10% { opacity: 1;top:5% }
            90% { opacity: 1;top:5% }
            100% { opacity: 0;top: -5%; }
        }
</style>
<c:if test="${result!=null}">
<c:if test="${result.result==1}">
	<div id="message-box">${result.message}</div><div hidden="true">${result.result=0}</div>
</c:if>
</c:if>


