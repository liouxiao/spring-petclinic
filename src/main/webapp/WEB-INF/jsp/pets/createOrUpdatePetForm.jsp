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
        <c:if test="${pet['new']}"><fmt:message key="new"/> </c:if>
        <fmt:message key="pet"/>
    </h2>
    
    <spring:message code="petName" var="Name" />
    <spring:message code="birthDate" var="BirthDate" />
    <spring:message code="type" var="Type" />

    <form:form modelAttribute="pet"
               class="form-horizontal">
        <input type="hidden" name="id" value="${pet.id}"/>
        <div class="form-group has-feedback">
            <div class="form-group">
                <label class="col-sm-2 control-label"><fmt:message key="owner"/></label>
                <div class="col-sm-10">
		            <c:choose>
		            <c:when test='${fn:startsWith(pageContext.response.locale, "zh")}'>
		            	<c:out value="${pet.owner.lastName}, ${pet.owner.firstName}"/>
		            </c:when>
		            <c:otherwise>
		            	<c:out value="${pet.owner.firstName} ${pet.owner.lastName}"/>
		            </c:otherwise>
		            </c:choose>
                </div>
            </div>
            <petclinic:inputField label="${Name}" name="name"/>
            <petclinic:inputField label="${BirthDate}" name="birthDate"/>
            <div class="control-group">
                <petclinic:selectField name="type" label="${Type} " names="${types}" size="5"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${pet['new']}">
                        <button class="btn btn-primary" type="submit"><fmt:message key="addPet"/></button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-primary" type="submit"><fmt:message key="updatePet"/></button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
    <c:if test="${!pet['new']}">
    </c:if>
</div>

<jsp:include page="../fragments/footer.jsp"/>

<script>
    $(function () {
        $("#birthDate").datepicker({dateFormat: 'yy/mm/dd'});
    });
</script>
</body>

</html>
