<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="org.ck.gui.Constants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>CUSUM Anomaly Detection Algorithm</title>
</head>
<body>
	<div>
		<label>Select Data Set</label> <select id="dropdown_cusum_algo"
			class="TopMenuButtons">
			<%
				Constants.DatasetOptions[] datasets = Constants.DatasetOptions.values();
				for(int i=0; i<datasets.length; i++)
					out.println("<option value=\"" + datasets[i] + "\">" + datasets[i] + "</option>");
			%>
		</select> <br /> <br /> <input id="button_cusumAlgoCalc" class="TopMenuButtons"
			style="" type="button" value="Calculate" />
	</div>
	<p id="ajax_cusum_algo_result">
	Algorithm Results will appear here!</p>
	
	<script type="text/javascript">
		$("#button_cusumAlgoCalc").button().click(function () {			
	        $("#ajax_cusum_algo_result").load("MainController", 
	        		{"taskType" : "<%=Constants.TaskType.ANOMALY_DETECTIVE %>",
	        		 "algorithmType" : "<%=Constants.AlgorithmType.CUSUM %>",
	        		 "dataset" : $("#dropdown_cusum_algo option:selected").val()
	        		}
	        );
	    });
	</script>
</body>
</html>