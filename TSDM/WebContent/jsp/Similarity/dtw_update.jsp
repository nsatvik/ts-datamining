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
<title>DTW Update Results</title>
</head>
<body>

	<!-- <br /> Task Type Selected = ${tsBean.taskType }
	<br /> Algorithm used = ${tsBean.algorithmType }
	<br /> Dataset used = ${tsBean.dataset }
	<br />
	<br /> Results sorted by Similarity
	<br /> ${tsBean.result }
	<br /> SAX String : ${tsBean.sample.saxString }
	-->

	<div>
		<!-- GRAPH <br /> <img alt="charts4j" src="${graphBean.url }" /> -->
	</div>
	
	<div id="dtw_table_div"
		style="width: 1600px; float: right; color: blue;">		
	</div>
	<div id="sax_table_div"
		style="width: 1600px; float: right; color: blue;">		
	</div>
	
	<div id="line_chart_div"
		style="width: 1600px; height: 500px; float: right;"></div>
	
	
	

	

	<!-- <div id="annotated_timeline_div"
		style="width: 900px; height: 500px; float: right;">HUHU</div> -->



	<script type="text/javascript">
		function getDataArrayForDTWDataTable(){
			var dataArray = [];
			dataArray.push(['Sample Name', 'DTW Distance']);
			
			
			<c:forEach items="${tsBean.resultObjects[0]}" var="sample">
				var dataTuple = ["${sample.value.name}", "${sample.key}"];
				dataArray.push(dataTuple);
			</c:forEach>	
			
			return dataArray;
		}
		
		function getDataArrayForSAXDataTable(){
			var dataArray = [];
			dataArray.push(['Sample Name', 'SAX String', 'SAX Distance']);
			
			
			<c:forEach items="${tsBean.resultObjects[1]}" var="sample">
				var dataTuple = ["${sample.value.name}", "${sample.value.saxString}", "${sample.key}"];
				dataArray.push(dataTuple);
			</c:forEach>	
			
			return dataArray;
		}
	
	
		function getDataArrayForLineChart(){
			var dataArray = [];
			var i = 0;
			<%
				TimeSeriesBean tsBean = (TimeSeriesBean)request.getSession().getAttribute("tsBean");
				ArrayList<Sample> subSamples = tsBean.getSubSamples();
			%>
			dataArray.push(['Month' 
			                <%			                	
			                	for(int i=0; i<subSamples.size(); i++)
			                		out.print(", " + "'Sample " + (i+1) + "'");
			                %>
			                ]);
						
			<%
			double indices[] = new double[subSamples.size()];
			Sample baseSample = subSamples.get(0);			//The sample that all other samples will be compared to
			for(int i=0; i<baseSample.getNumOfValues(); i++)
			{
				%>var dataTuple = ['' + <%out.print(i);%> , 
						<%
						out.print(baseSample.getSmoothNormalizedTimeSeries().get(i));
						for(int j=1; j<subSamples.size(); j++)
						{
							out.print(", " + subSamples.get(j).getSmoothNormalizedTimeSeries().get((int)indices[j]));
							indices[j] += (double)subSamples.get(j).getNumOfValues() / baseSample.getNumOfValues();
						}
						%>
						
						
						];
				dataArray.push(dataTuple);
		<%	}
			%>
			
			return dataArray;		
		};	
	</script>
	
	<script type="text/javascript">    	
	    google.load("visualization", "1", {callback : function(){drawDTWDataTable();}, packages:['table']});	    
	    function drawDTWDataTable() { 		    	
	      	var data = google.visualization.arrayToDataTable(getDataArrayForDTWDataTable());
	      
		    var options = {
		    	title: "DTW Data"	     
		      };
		  
	     	var table = new google.visualization.Table(document.getElementById('dtw_table_div'));
	     	table.draw(data, options);	     	
	    };
	    
	    google.load("visualization", "1", {callback : function(){drawSAXDataTable();}, packages:['table']});	    
	    function drawSAXDataTable() { 		    	
	      	var data = google.visualization.arrayToDataTable(getDataArrayForSAXDataTable());
	      
		    var options = {
		    	title: "SAX Data"	     
		      };
		  
	     	var table = new google.visualization.Table(document.getElementById('sax_table_div'));
	     	table.draw(data, options);	     	
	    };
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

</body>
</html>