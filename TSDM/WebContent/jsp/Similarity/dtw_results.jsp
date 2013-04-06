<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
</head>
<body>

	<div style="float: left;">
		Task Type Selected = ${tsBean.taskType } <br /> Algorithm used =
		${tsBean.algorithmType } <br /> Dataset used = ${tsBean.dataset } <br />
		Sample = ${tsBean.sample.normalizedTimeSeries[0] } <br />
		<!-- Result = ${tsBean.result } -->
		
		
		
	</div>

	<div>
		<!-- GRAPH <br /> <img alt="charts4j" src="${graphBean.url }" /> -->
	</div>
	
	<div id="line_chart_div" style="width: 900px; height: 500px; float: left;">
		HAHA
	</div>
	
	<div id="annotated_timeline_div" style="width: 900px; height: 500px; float: right;">
		HUHU
	</div>
	
	
    <script type="text/javascript">    	
	    google.load("visualization", "1", {callback : function(){drawLineChart();}, packages:["corechart"]});
	    function drawLineChart() { 		    	
	      	var data = google.visualization.arrayToDataTable(getDataArrayForLineChart());
	      
		    var options = {
		        title: 'Time Series'
		      };
		  
	     	var chart = new google.visualization.LineChart(document.getElementById('line_chart_div'));
	     	chart.draw(data, options);
	    }
    </script>
    
    <script type="text/javascript">    	
	    google.load("visualization", "1", {callback : function(){drawAnnotatedTimeline();}, packages:["annotatedtimeline"]});
	    function drawAnnotatedTimeline() { 		    	
	    	
	    	var data = getDataArrayForTimeline();
	        
	        var chart = new google.visualization.AnnotatedTimeLine(document.getElementById('annotated_timeline_div'));
	        chart.draw(data, {displayAnnotations: true});

	    }
    </script>
	
	<script type="text/javascript">
		function getDataArrayForLineChart(){
			var dataArray = [];
			var i = 0;
			dataArray.push(['Month', 'Level']);
			<c:forEach items="${tsBean.sample.smoothTimeSeries}" var="timeValuePair">
				// IMPORTANT : '' + ++i will be replaced with appropriate names from the Sample Object -- Restructuring required
				var dataTuple = ['' + ++i, ${timeValuePair}];		
				dataArray.push(dataTuple);
			</c:forEach>
			return dataArray;		
		}
		
		function getDataArrayForTimeline(){
			var i = 0;
			var data = new google.visualization.DataTable();
			data.addColumn('date', 'Date');
	        data.addColumn('number', 'Sold Pencils');	        
	        data.addRows([
				<c:forEach items="${tsBean.sample.smoothTimeSeries}" var="timeValuePair">
					[new Date(++i * 10000000), ${timeValuePair}],
				</c:forEach>
	          [new Date(i), ${tsBean.sample.timeSeries[5]}]
	        ]);
	        
			return data;
					
		}
	</script>
	
</body>
</html>