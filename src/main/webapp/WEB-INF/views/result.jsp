<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Data Analyser</title>
</head>

<body>
	<c:if test="${ errorMessage != null}">
		<div>
			<br> ${ errorMessage } <br> <br>
		</div>
	</c:if>
	<c:if test="${ errorMessage == null}">
		<c:if test="${ cvsData.size() == 0}">
			<div>
				<br> Processed file has no data! <br> <br>
			</div>
		</c:if>
		<c:if test="${ cvsData.size() > 0}">
			<div>
				<h1>Processed File</h1>
			</div>
			<div>
				<table>
					<tr>
						<td>id</td>
						<c:forEach items="${variables}" var="variable">
							<td>${variable}</td>
						</c:forEach>
						<td>Decision</td>
					</tr>

					<c:forEach items="${cvsData}" var="data">
						<tr>
							<td>${data.id}</td>
							<c:forEach items="${data.vars}" var="var">
								<td>${var.value}</td>
							</c:forEach>
							<td>${data.decision}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</c:if>
	</c:if>

</body>

</html>