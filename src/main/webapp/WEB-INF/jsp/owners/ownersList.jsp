<!DOCTYPE html>

<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<html lang="en">

<jsp:include page="../fragments/htmlHeader.jsp"/>

<body>
<petclinic:bodyHeader menuName="owners"/>
<div class="container">
    <h2><fmt:message key="owners"/></h2>

    <datatables:table id="owners" data="${selections}" row="owner"
                      cssClass="table table-striped" pageable="true" info="false" export="pdf">
        <datatables:column titleKey="name" cssStyle="width: 150px;" display="html">
            <spring:url value="/owners/{ownerId}.html" var="ownerUrl">
                <spring:param name="ownerId" value="${owner.id}"/>
            </spring:url>
            <a href="${fn:escapeXml(ownerUrl)}">
            	<c:choose>
            	<c:when test='${fn:startsWith(pageContext.response.locale, "zh")}'>
            		<c:out value="${owner.lastName}, ${owner.firstName}"/>
            	</c:when>
            	<c:otherwise>
            		<c:out value="${owner.firstName} ${owner.lastName}"/>
            	</c:otherwise>
            	</c:choose>
            </a>
        </datatables:column>
        <datatables:column titleKey="name" display="pdf">
            <c:choose>
            	<c:when test='${fn:startsWith(pageContext.response.locale, "zh")}'>
            		<c:out value="${owner.lastName}, ${owner.firstName}"/>
            	</c:when>
            	<c:otherwise>
            		<c:out value="${owner.firstName} ${owner.lastName}"/>
            	</c:otherwise>
            </c:choose>
        </datatables:column>
        <datatables:column titleKey="address" property="address" cssStyle="width: 200px;"/>
        <datatables:column titleKey="city" property="city"/>
        <datatables:column titleKey="telephone" property="telephone"/>
        <datatables:column titleKey="pets" cssStyle="width: 100px;">
            <c:forEach var="pet" items="${owner.pets}">
                <c:out value="${pet.name}"/>
            </c:forEach>
        </datatables:column>
        <datatables:export type="pdf" cssClass="btn" cssStyle="height: 25px;"/>
    </datatables:table>
</div>

<jsp:include page="../fragments/footer.jsp"/>
</body>

</html>
