<%@page import="org.ck.beans.TimeSeriesBean"%>
<%@page import="org.ck.gui.Constants" %>
<%@page import="java.util.ArrayList" %>
<%@page import="org.ck.sample.Sample" %>
<%@page import="org.ck.smoothers.SmoothingFilter"%>
<%@page import="org.ck.servlets.AlgorithmUtils"%>
<%@page import="org.ck.smoothers.SimpleMovingAverageSmoother" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Moving Average Graph</title>
</head>
<body>
	 
	 
	
	<div style="float: left;">
		Task Type Selected = ${tsBean.taskType } <br /> Algorithm used =
		${tsBean.algorithmType } <br /> Dataset used = ${tsBean.dataset } <br />
		Sample = ${tsBean.sample.normalizedTimeSeries[0] }
		<%--Predicted Value is =  ${tsBean.pred}  --%> 
		<br />
	</div>
	<label> Estimated Error : </label> <p id="error_avg_estimate">TBD</p>
	<div id="line_chart_div" style="width: 1024px; height: 768px; float: left;">
		
		
	</div>
	<!-- 
	<div id="annotated_timeline_div" style="width: 900px; height: 500px; float: right;">
		
	</div> -->
	<%
		TimeSeriesBean tsBean = (TimeSeriesBean) request.getSession()
				.getAttribute("tsBean");
		//out.println("Output String <br/>"+tsBean.getResult());
	%>
	
    <script type="text/javascript">    	
	    google.load("visualization", "1", {callback : function(){drawLineChart();}, packages:["corechart"]});
	    function drawLineChart() { 		    	
	      	//var data = google.visualization.arrayToDataTable(getDataArrayForLineChart());
	      	var data = new google.visualization.DataTable();
			data.addColumn('number', 'Time');
			data.addColumn('number', 'Actual Value');
			data.addColumn('number', 'Predicted Value');
			data.addRows(<%out.print(tsBean.getResult());%>);
	      
		    var options = {
		        title: 'Fortune Teller'
		      };
		  
	     	var chart = new google.visualization.LineChart(document.getElementById('line_chart_div'));
	     	chart.draw(data, options);
	     	$("#error_avg_estimate").html(<%out.println(tsBean.getErrorEstimate());%>);
	    }
    </script>
</body>
</html>