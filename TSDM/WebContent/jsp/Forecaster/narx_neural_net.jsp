<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="org.ck.gui.Constants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>NARX - Neural Network Forecaster</title>
</head>
<body>
<div>
		<label>Select Data Set</label> <select id="dropdown_nn"
			class="TopMenuButtons">
			<%
				Constants.DatasetOptions[] datasets = Constants.DatasetOptions.values();
				for(int i=0; i<datasets.length; i++)
					out.println("<option value=\"" + datasets[i] + "\">" + datasets[i] + "</option>");
			%>
		</select> <br /> <br /> <input id="button_nnCalc" class="TopMenuButtons"
			style="" type="button" value="Calculate" />
	</div>
	<p id="ajax_nn_result"></p>
	
	<script type="text/javascript">
		$("#button_nnCalc").button().click(function () {	
			$("#ajax_nn_result").html(loadingImgHTML);
	        $("#ajax_nn_result").load("MainController", 
	        		{"taskType" : "<%=Constants.TaskType.FORTUNE_TELLER %>",
	        		 "algorithmType" : "<%=Constants.AlgorithmType.NARX_NN %>",
	        		 "dataset" : $("#dropdown_nn option:selected").val()
	        		}
	        );
	    });
	</script>
</body>
</html>