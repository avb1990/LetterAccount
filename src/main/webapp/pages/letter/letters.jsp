<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><spring:message code="letter.title" /></title>
<script type="text/javascript" src="./pages/letter/jquery.js"></script>
<script type="text/javascript" src="./pages/letter/letters.js"></script>
</head>
<body>
	<form:form method="post" action="addLetter" commandName="letter"
		enctype="multipart/form-data">
		<form:label path="number">
			<spring:message code="letter.number" />
		</form:label>
		<form:input path="number" />
		<form:label path="letterDate">
			<spring:message code="letter.letterDate" />
		</form:label>
		<form:input path="letterDate" type="date" />
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
		<form:label path="file">
			<spring:message code="letter.file" />
		</form:label>
		<form:input id="file" type="file" path="file" />
		<form:hidden id="fileType" path="fileType" />
		<input type="submit" value="<spring:message code="letter.addLetter"/>" />
	</form:form>

	<table>
		<tr>
			<th><spring:message code="letter.number" /></th>
			<th><spring:message code="letter.theme" /></th>
			<th><spring:message code="letter.published" /></th>
			<th><spring:message code="letter.note" /></th>
			<th><spring:message code="letter.letterDate" /></th>

		</tr>
		<c:set var="dateTimeDisplayFormat">
			<spring:message code="letter.datePattern" />
		</c:set>
		<c:set var="showFileUrlPattern" value="getFile?letterId=" />
		<c:forEach items="${letters}" var="aLetter">
			<tr>
				<td>${aLetter.number}</td>
				<td>${aLetter.theme}</td>
				<td>${aLetter.published}</td>
				<td>${aLetter.note}</td>
				<td><fmt:formatDate pattern="${dateTimeDisplayFormat}"
						value="${aLetter.letterDate}" /></td>
				<c:set var="url" value="${showFileUrlPattern}${aLetter.id}" />
				<td><a href="${url}"><spring:message code="letter.showFile" /></a></td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>
