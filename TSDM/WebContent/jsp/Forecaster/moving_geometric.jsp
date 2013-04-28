<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="org.ck.gui.Constants" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Moving Geometric Average</title>
</head>
</head>
<body>
	<div>
		<label>Select Data Set</label> <select id="dropdown_moving_geo"
			class="TopMenuButtons">
			<%
				Constants.DatasetOptions[] datasets = Constants.DatasetOptions.values();
				for(int i=0; i<datasets.length; i++)
					out.println("<option value=\"" + datasets[i] + "\">" + datasets[i] + "</option>");
			%>
		</select> <br /> <br /> <input id="button_movinggeoCalc" class="TopMenuButtons"
			style="" type="button" value="Calculate" />
	</div>
	<p id="ajax_moving_geometric_result"></p>
	
	<script type="text/javascript">
		$("#button_movinggeoCalc").button().click(function () {			
			$("#ajax_moving_geometric_result").html(loadingImgHTML);
	        $("#ajax_moving_geometric_result").load("MainController", 
	        		{"taskType" : "<%=Constants.TaskType.FORTUNE_TELLER %>",
	        		 "algorithmType" : "<%=Constants.AlgorithmType.MOVING_GEOMETRIC%>",
	        		 "dataset" : $("#dropdown_moving_geo option:selected").val()
	        		}
	        );
	    });
	</script>
</body>
</html>