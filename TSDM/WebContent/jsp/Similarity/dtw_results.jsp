<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="org.ck.gui.Constants" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
</head>
<body>

	
	<div style="float: left;">
		<p>
			<label for="sample_range">Range:</label> <input type="text"
				id="sample_range" style="width:55%; color: #f6931f; font-weight: bold;" readonly />
		</p>
		<div id="slider-range"></div>
		<br/>
		<input id="button_updateGraph" type="button" value="Update Graph"/>
		
		<br/>
		<br /> Task Type Selected = ${tsBean.taskType } <br /> 
		Sub Task Type = ${tsBean.subTaskType } <br/> Algorithm used =
		${tsBean.algorithmType } <br /> Dataset used = ${tsBean.dataset } <br />
		Sample = ${tsBean.sample.normalizedTimeSeries[0] } <br />
		<!-- Result = ${tsBean.result } -->
		<br/>
		SAX String : ${tsBean.sample.saxString }
		
		
		
	</div>

	<div id="div_dtwUpdate">
		<div>
			<!-- GRAPH <br /> <img alt="charts4j" src="${graphBean.url }" /> -->
		</div>

		<div id="line_chart_div"
			style="width: 900px; height: 500px; float: left;">HAHA</div>

		<div id="annotated_timeline_div"
			style="width: 900px; height: 500px; float: right;">HUHU</div>
	</div>
	
	<script type="text/javascript">
		$(function() {
			$( "#slider-range" ).slider({
								range : true,
								min : 0,
								max : ${tsBean.sample.numOfValues},
								values : [ 0, 1400],
								slide : function(event, ui) {
									$("#sample_range").val(
											"" + ui.values[0] + " to "
													+ ui.values[1]);
								},
								change : function(event, ui) {
									$("#button_updateGraph").trigger("click");	
								}
							});
			$("#sample_range").val(
					"" + $("#slider-range").slider("values", 0) + " to "
							+ $("#slider-range").slider("values", 1));
		});
	</script>
	
	<script type="text/javascript">
		$("#button_updateGraph").button().click(function(){
			$("#hidden_params").val($("#sample_range").val());
			 $("#div_dtwUpdate").load("MainController", 
		        		{"taskType" : "<%=Constants.TaskType.SIMILARITY %>",
				 		 "subTaskType" : "<%=Constants.SubTaskType.UPDATE_GRAPH %>",
		        		 "algorithmType" : "<%=Constants.AlgorithmType.DTW %>",
		        		 "dataset" : $("#dropdown option:selected").val(),
		        		 "params" : $("#hidden_params").val()
		        		}
		        );
		});
		
		
		$(function(){
			$("#button_updateGraph").trigger("click");
		});	
	</script>
	
</body>
</html>