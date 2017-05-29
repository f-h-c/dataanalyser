<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Data Analyser</title>
</head>

<body>
	<form:form action="/dataanalyser/home" method="POST" enctype="multipart/form-data">
		<div>
			<label>CVS File</label> 
			<input name="cvsFile" type="file" />
		</div>
		<button type="submit">Send</button>
	</form:form>
</body>

</html>