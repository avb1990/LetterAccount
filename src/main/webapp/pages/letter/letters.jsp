<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><spring:message code="letter.title" /></title>
</head>
<body>
	<form:form method="post" action="addLetter" commandName="letter">
		<form:label path="number">
			<spring:message code="letter.number" />
		</form:label>
		<form:input path="number" />
		<form:label path="theme">
			<spring:message code="letter.theme" />
		</form:label>
		<form:input path="theme" />
		<form:label path="published">
			<spring:message code="letter.published" />
		</form:label>
		<form:checkbox path="published" />
		<form:label path="note">
			<spring:message code="letter.note" />
		</form:label>
		<form:textarea cols="50" rows="20" path="note" />
		<input type="submit" value="<spring:message code="letter.addLetter"/>" />
	</form:form>

	<table>
		<tr>
			<th><spring:message code="letter.number" /></th>
			<th><spring:message code="letter.theme" /></th>
			<th><spring:message code="letter.published" /></th>
			<th><spring:message code="letter.note" /></th>

		</tr>
		<c:forEach items="${letters}" var="letter">
			<tr>
				<td>${letter.number}</td>
				<td>${letter.theme}</td>
				<td>${letter.published}</td>
				<td>${letter.note}</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>