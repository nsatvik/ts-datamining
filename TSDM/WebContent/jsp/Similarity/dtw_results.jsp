<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<div style="float: left;">
		Task Type Selected = ${tsBean.taskType } <br /> Algorithm used =
		${tsBean.algorithmType } <br /> Dataset used = ${tsBean.dataset } <br />
		Sample = ${tsBean.sample } <br />
		<!-- Result = ${tsBean.result } -->
	</div>

	<div>
		GRAPH <br /> <img alt="charts4j" src="${graphBean.url }" />
	</div>
</body>
</html>