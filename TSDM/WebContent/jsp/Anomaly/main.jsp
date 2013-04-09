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
	Anomaly detection, also referred to as outlier detection refers to 
	detecting patterns in a given data set that do not conform to an established normal behavior. 
	The patterns thus detected are called anomalies and often translate to critical and actionable
	information in several application domains. Anomalies are also referred to as outliers,
	change, deviation, surprise, aberrant, peculiarity, intrusion, etc.
	
	<br/>
	<br/>
	<br/>
	<br/>
	<div id="anamoly_navigator">
		<input id="button_view_anamoly" class="TopMenuButtons" style="" type="button" value="View Algorithms"/>
	</div>
</div>

<script type="text/javascript">
	$("#button_view_anamoly").button().click(function () {	    
	    $("#anamoly_navigator").load("./jsp/Anomaly/navigator.jsp");
	});
</script>

</body>
</html>
