<!DOCTYPE html>

<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<html lang="en">
<jsp:include page="fragments/htmlHeader.jsp"/>

<body>
<<petclinic:bodyHeader menuName="error"/>
<div class="container">

    <spring:url value="/resources/images/pets.png" var="petsImage"/>
    <img src="${petsImage}"/>

    <h2><fmt:message key="somethingHappened"/></h2>

    <p>${exception.message}</p>

    <!-- Exception: ${exception.message}.
		  	<c:forEach items="${exception.stackTrace}" var="stackTrace"> 
				${stackTrace} 
			</c:forEach>
	  	-->
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>

</html>
