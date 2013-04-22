<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Forecaster Main Page</title>
</head>
<body>
<div>
	Time series forecasting is the use of a model to predict future values based on previously observed values.
	 While regression analysis is often employed in such a way as to test theories that the current values
	  of one or more independent time series affect the current value of another time series,
	 this type of analysis of time series is not called "time series analysis".
	<br/>
	<br/>
	<br/>
	<br/>
	<div id="forecast_navigator">
		<input id="button_view_forecast" class="TopMenuButtons" style="" type="button" value="View Algorithms"/>
	</div>
</div>

<script type="text/javascript">
	$("#button_view_forecast").button().click(function () {	    
	    $("#forecast_navigator").load("./jsp/Forecaster/navigator.jsp");
	});
</script>

</body>
</html>
