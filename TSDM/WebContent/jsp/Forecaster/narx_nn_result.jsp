
<%@page import="org.ck.servlets.AlgorithmUtils"%>
<%@page import="org.ck.beans.TimeSeriesBean"%>
<%@page import="org.ck.gui.Constants" %>
<%@page import="java.util.ArrayList" %>
<%@page import="org.ck.sample.Sample" %>

<%@page import="org.ck.smoothers.SimpleMovingAverageSmoother" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>NARX NN Graphs</title>
</head>
<body>
	 
	 
	
	
	<!--  <div id="line_chart_div" style="width: 900px; height: 500px; float: left;"> -->
		
	<label> Estimated Error : </label> <p id="error_estimate">TBD</p>
	<div id="annotated_timeline_div" style="width: 900px; height: 500px;  margin-left: auto; margin-right: auto;">
		
	</div>
	
	
    <script type="text/javascript">    	
	    google.load("visualization", "1", {callback : function(){drawLineChart();}, packages:["corechart"]});
	    function drawLineChart() { 		    	
	      	var data = google.visualization.arrayToDataTable(getDataArrayForLineChart());
	      
		    var options = {
		        title: 'Neural Network Based Forecaster'
		      };
		  
	     	var chart = new google.visualization.LineChart(document.getElementById('line_chart_div'));
	     	chart.draw(data, options);
	    }
    </script>
    <%
		TimeSeriesBean tsBean = (TimeSeriesBean) request.getSession()
				.getAttribute("tsBean");
		//out.println("Output String <br/>"+tsBean.getResult());
	%>
    <script type="text/javascript">    	
	    google.load("visualization", "1", {callback : function(){drawAnnotatedTimeline();}, packages:["annotatedtimeline"]});
	    function drawAnnotatedTimeline() { 		    	
	    	
	    	//var data = getDataArrayForTimeline();\
	    	var data = new google.visualization.DataTable();
			data.addColumn ('date', 'Time Line');
			data.addColumn('number', 'Actual Value');
			data.addColumn('number', 'Predicted Value');
			data.addRows(<%out.print(tsBean.getResult());%>);
	        
	        var chart = new google.visualization.AnnotatedTimeLine(document.getElementById('annotated_timeline_div'));
	        chart.draw(data, {displayAnnotations: true});
	        $("#error_estimate").html(<%out.println(tsBean.getErrorEstimate());%>);

	    }
    </script>
	
</body>
</html>