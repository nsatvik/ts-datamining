<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="org.ck.gui.Constants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>CUSUM Anomaly Detection Algorithm</title>
<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
  <script>
  			$(function() {
   				 $( "#slider" ).slider({
					range: "max",
					min: -10,
					max: 10,
					value: 0.5,
					slide: function ( event, ui ) {
				        $( "#threshold" ).val( ui.value );
				        $("#ajax_stat_algo_results").load("MainController", 
				        		{"taskType" : "<%=Constants.TaskType.ANOMALY_DETECTIVE %>",
				        		 "algorithmType" : "<%=Constants.AlgorithmType.CUSUM %>",
				        		 "dataset" : $("#dropdown option:selected").val(),
				        		 "anomalyThreshold" : $("#stat_threshold").val()
				        		}
				        
				        );
				      }
   				 });
   				$( "#threshold" ).val( $( "#slider" ).slider( "value" ) );
			  });
  </script>
</head>
<body>
	<div>
	
		<label>Select Data Set</label> <select id="dropdown_stat_algo"
			class="TopMenuButtons">
			<%
				Constants.DatasetOptions[] datasets = Constants.DatasetOptions.values();
				for(int i=0; i<datasets.length; i++)
					out.println("<option value=\"" + datasets[i] + "\">" + datasets[i] + "</option>");
			%>
		</select> <br /> <br /> <input id="button_statsAppCalc" class="TopMenuButtons"
			style="" type="button" value="Calculate" />
		
		<br />
		<label for="threshold">Threshold Value : </label>
		<input type="text" id="stat_threshold" style="border: 0; font-weight: bold;" value="1.0" />
		 <div id="stat_slider" style="width: 300px; margin-left: auto; margin-right: auto;"></div>
	</div>
	<p id="ajax_stat_app_result">
	Algorithm Results will appear here!</p>
	
	<script type="text/javascript">
		$("#button_statsAppCalc").button().click(function () {
			$("#ajax_stat_app_result").html(loadingImgHTML);
	        $("#ajax_stat_app_result").load("MainController", 
	        		{"taskType" : "<%=Constants.TaskType.ANOMALY_DETECTIVE %>",
	        		 "algorithmType" : "<%=Constants.AlgorithmType.STATISTICAL_APPROACH%>",
	        		 "dataset" : $("#dropdown_stat_algo option:selected").val(),
	        		 "anomalyThreshold" : $("#threshold").val()
	        		}
	        );
	    });
	</script>
</body>
</html>