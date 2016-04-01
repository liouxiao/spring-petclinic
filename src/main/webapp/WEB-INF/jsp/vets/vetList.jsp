<!DOCTYPE html>

<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>

<html lang="en">


<jsp:include page="../fragments/staticFiles.jsp"/>

<body>
<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp"/>

    <h2><fmt:message key="veterinarians"/></h2>

	<spring:message code="name" var="Name" />
	<spring:message code="specialties" var="Specialties" />

    <datatables:table id="vets" data="${vets.vetList}" row="vet" theme="bootstrap2" cssClass="table table-striped"
                      pageable="false" info="false">
        <datatables:column title="${Name}">
            <c:choose>
            <c:when test='${fn:startsWith(pageContext.response.locale, "zh")}'>
            	<c:out value="${vet.lastName}, ${vet.firstName}"/>
            </c:when>
            <c:otherwise>
            	<c:out value="${vet.firstName} ${vet.lastName}"></c:out>
            </c:otherwise>
            </c:choose>
        </datatables:column>
        <datatables:column title="${Specialties}">
            <c:forEach var="specialty" items="${vet.specialties}">
                <c:out value="${specialty.name}"/>
            </c:forEach>
            <c:if test="${vet.nrOfSpecialties == 0}">none</c:if>
        </datatables:column>
    </datatables:table>

    <table class="table-buttons">
        <tr>
            <td>
                <a href="<spring:url value="/vets.xml" htmlEscape="true" />"><fmt:message key="viewAsXml"/></a>
            </td>
            <td>
                <a href="<spring:url value="/vets.json" htmlEscape="true" />"><fmt:message key="viewAsJson"/></a>
            </td>
        </tr>
    </table>

    <jsp:include page="../fragments/footer.jsp"/>
</div>
</body>

</html>
