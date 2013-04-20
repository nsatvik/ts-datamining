<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="org.ck.gui.Constants" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>TSDM Results</title>
</head>
<body>

	It's me...yeah ME!
	
	<br/>
	
	
	<div id="scatter_chart_div"
		style="width: 100%; height: 500px; ">
	</div>
	
	<script type="text/javascript">    
	
		function getDataArrayForScatterChart() {
			var dataArray = [['Q-1', 'Q']];						
			dataArray = dataArray.concat(${tsBean.resultObjects[0].phaseSpace});
			return dataArray;
		}

		google.load("visualization", "1", {
			callback : function() {
				drawScatterChart();
			},
			packages : [ "corechart" ]
		});
		function drawScatterChart() {
			var data = google.visualization
					.arrayToDataTable(getDataArrayForScatterChart());

			var options = {
				title : 'Phase Space Graph'
			};

			var chart = new google.visualization.ScatterChart(document
					.getElementById('scatter_chart_div'));
			chart.draw(data, options);
		};
	</script>
	
</body>
</html>