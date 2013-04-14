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
<p>Satvik: Cusum Update Page </p>
<br /> Task Type Selected = ${tsBean.taskType } <br /> 
		Algorithm used =
		${tsBean.algorithmType } <br /> Dataset used = ${tsBean.dataset } <br />		
		<br/>		
		 Algorithm Output String <br/>
		  ${tsBean.result } }

	<div>
		<!-- GRAPH <br /> <img alt="charts4j" src="${graphBean.url }" /> -->
	</div>

	<div id="line_chart_div"
		style="width: 1600px; height: 500px; float: right;">		
	</div>

	<!-- <div id="annotated_timeline_div"
		style="width: 900px; height: 500px; float: right;">HUHU</div> -->
	<%
		TimeSeriesBean tsBean = (TimeSeriesBean) request.getSession()
				.getAttribute("tsBean");
		out.println("Output String <br/>"+tsBean.getResult());
		
	%>
	<p> Satvik's Master Piece Graph Coming Soon! </p>
	<!-- 
	<script type="text/javascript">
		function getDataArrayForLineChart(){
			var dataArray = [];
			var i = 0;
			
			
			return dataArray;		
		};
		
		/*function getDataArrayForTimeline(){
			var i = 0;
			var data = new google.visualization.DataTable();
			data.addColumn('date', 'Date');
	        data.addColumn('number', 'Sold Pencils');	        
	        data.addRows([
				<c:forEach items="${tsBean.sample.visualPAATimeSeries}" var="timeValuePair">
					[new Date(++i * 10000000), ${timeValuePair}],
				</c:forEach>
	          [new Date(i), ${tsBean.sample.timeSeries[5]}]
	        ]);
	        
			return data;					
		};*/
	</script>

	<script type="text/javascript">    	
	    google.load("visualization", "1", {callback : function(){drawLineChart();}, packages:["corechart"]});
	    function drawLineChart() { 		    	
	      	var data = google.visualization.arrayToDataTable(getDataArrayForLineChart());
	      
		    var options = {
		        title: 'Time Series'
		      };
		  
	     	var chart = new google.visualization.LineChart(document.getElementById('line_chart_div'));
	     	chart.draw(data, options);
	    };
    </script>
    
    <script type="text/javascript">    	
	    /*google.load("visualization", "1", {callback : function(){drawAnnotatedTimeline();}, packages:["annotatedtimeline"]});
	    function drawAnnotatedTimeline() { 		    	
	    	
	    	var data = getDataArrayForTimeline();
	        
	        var chart = new google.visualization.AnnotatedTimeLine(document.getElementById('annotated_timeline_div'));
	        chart.draw(data, {displayAnnotations: true});

	    };*/
    </script>
	
	
 -->
</body>
</html>