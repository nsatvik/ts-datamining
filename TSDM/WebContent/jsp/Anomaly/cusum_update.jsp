<%@page import="org.ck.beans.TimeSeriesBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.ArrayList" %>
<%@page import="org.ck.gui.Constants" %>
<%@page import="org.ck.sample.Sample" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cusum Update Results</title>
</head>
<body>

<br /> Task Type Selected = ${tsBean.taskType } <br /> 
		Algorithm used =
		${tsBean.algorithmType } <br /> Dataset used = ${tsBean.dataset } <br />		
		<br/>		

	<div>
		<!-- GRAPH <br /> <img alt="charts4j" src="${graphBean.url }" /> -->
	</div>

	<div id="line_chart_div"
		style="width: 1600px; height: 500px; float: right;">		
	</div>

	<!-- <div id="annotated_timeline_div"
		style="width: 900px; height: 500px; float: right;">HUHU</div> -->
	<p> Satvik's Master Piece Graph Coming Soon! </p>
</body>
</html>