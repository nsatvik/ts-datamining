<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Temporal Pattern Miner Main Page</title> 
</head>
<body>

<div>
		The Time Series Data Mining framework introduced by Richard. J.
		Povinelli and Xin Feng is a novel time series analysis method for
		discovering patterns characteristic of events. "This framework adapts
		and innovates data mining concepts to analyzing time series data. In
		particular, it creates a set of methods that reveal hidden temporal
		patterns that are characteristic and predictive of time series events"

		<br/>
	<br/>
	<br/>
	<br/>
	<div id="tsdm_navigator">
		<input id="button_tsdmView" class="TopMenuButtons" style="" type="button" value="View Algorithms"/>
	</div>

</div>

<script type="text/javascript">
	$("#button_tsdmView").button().click(function () {	    
	    $("#tsdm_navigator").load("./jsp/PatternFinder/tsdm.jsp");
	});
</script>

</body>
</html>