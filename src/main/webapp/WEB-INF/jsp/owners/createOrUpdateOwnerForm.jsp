<!DOCTYPE html>

<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<html lang="en">

<jsp:include page="../fragments/htmlHeader.jsp"/>

<body>
<petclinic:bodyHeader menuName="owners"/>
<div class="container">
    <h2>
        <c:if test="${owner['new']}"><fmt:message key="new"/> </c:if> <fmt:message key="owner"/>
    </h2>
    
	<spring:message code="firstName" var="firstName" />
	<spring:message code="lastName" var="lastName" />
	<spring:message code="address" var="Address" />
	<spring:message code="city" var="City" />
	<spring:message code="telephone" var="Telephone" />

    <form:form modelAttribute="owner" class="form-horizontal" id="add-owner-form">
        <div class="form-group has-feedback">
            <c:choose>
		    <c:when test='${fn:startsWith(pageContext.response.locale, "zh")}'>
		        <petclinic:inputField label="${lastName}" name="lastName"/>
		        <petclinic:inputField label="${firstName}" name="firstName"/>
		    </c:when>
		    <c:otherwise>
		        <petclinic:inputField label="${firstName}" name="firstName"/>
		        <petclinic:inputField label="${lastName}" name="lastName"/>
		    </c:otherwise>
		    </c:choose>

            <petclinic:inputField label="${Address}" name="address"/>
            <petclinic:inputField label="${City}" name="city"/>
            <petclinic:inputField label="${Telephone}" name="telephone"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${owner['new']}">
                        <button class="btn btn-primary" type="submit"><fmt:message key="addOwner"/></button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-primary" type="submit"><fmt:message key="updateOwner"/></button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</div>
<jsp:include page="../fragments/footer.jsp"/>
</body>

</html>
