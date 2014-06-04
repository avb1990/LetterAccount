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
<meta charset="utf-8">
</head>
<body>

	<form:form method="post" action="addLetter" commandName="letter"
		enctype="multipart/form-data">
		<table>
			<tr>
				<td><form:label path="number">
						<spring:message code="letter.number" />
					</form:label></td>
				<td><form:input path="number" /></td>
				<spring:bind path="number">
					<c:if test="${status.error}">
						<td><c:choose>
								<c:when test="${status.errorCode =='Size'}">
									<spring:message code="error.number.size" />
								</c:when>
								<c:when test="${status.errorCode =='NotEmpty'}">
									<spring:message code="error.number.notEmpty" />
								</c:when>
							</c:choose></td>
					</c:if>
				</spring:bind>
			</tr>
			<tr>
				<td><form:label path="letterDate">
						<spring:message code="letter.letterDate" />
					</form:label></td>
				<td><form:input path="letterDate" type="date" /></td>
				<spring:bind path="letterDate">
					<c:if test="${status.error}">
						<td><c:choose>
								<c:when test="${status.errorCode =='NotNull'}">
									<spring:message code="error.letterDate.notNull" />
								</c:when>
							</c:choose></td>
					</c:if>
				</spring:bind>
			</tr>
			<tr>
				<td><form:label path="theme">
						<spring:message code="letter.theme" />
					</form:label></td>
				<td><form:textarea cols="50" rows="5" path="theme" /></td>
				<spring:bind path="theme">
					<c:if test="${status.error}">
						<td><c:choose>
								<c:when test="${status.errorCode =='Size'}">
									<spring:message code="error.theme.size" />
								</c:when>
								<c:when test="${status.errorCode =='NotEmpty'}">
									<spring:message code="error.theme.notEmpty" />
								</c:when>
							</c:choose></td>
					</c:if>
				</spring:bind>
			</tr>
			<tr>
				<td><form:label path="published">
						<spring:message code="letter.published" />
					</form:label>
				<td><form:checkbox path="published" /></td>
			</tr>
			<tr>
				<td><form:label path="note">
						<spring:message code="letter.note" />
					</form:label></td>
				<td><form:textarea cols="50" rows="20" path="note" /></td>
			</tr>
			<tr>
				<td><button id="chooseFileButton" type="button">
						<spring:message code="letter.file" />
					</button></td>

				<td><form:input id="file" type="file" path="letterFile.file"
						style="visibility:hidden" /></td>
				<td><form:errors path="file" /></td>
				<td><form:errors path="fileType" /></td>
			</tr>
			<form:hidden id="fileType" path="letterFile.fileType" />
			<tr>
				<td><input type="submit"
					value="<spring:message code="letter.addLetter"/>" /></td>
			</tr>

		</table>
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
		<c:set var="publishUrlPattern" value="publish?letterId=" />
		<c:forEach items="${letters}" var="aLetter">
			<tr>

				<td>${aLetter.number}</td>
				<td>${aLetter.theme}</td>
				<td><c:choose>
						<c:when test="${aLetter.published}">
							<spring:message code="letter.letterPublished" />
						</c:when>
						<c:otherwise>
							<a href="${publishUrlPattern}${aLetter.id}"><spring:message
									code="letter.letterPublish" /></a>
						</c:otherwise>
					</c:choose></td>
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
