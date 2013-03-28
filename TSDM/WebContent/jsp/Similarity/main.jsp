<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Similarity Main Page</title> 
</head>
<body>

<div>
	Given a query time series Q, a similarity finder will find the most similar time series in a given collection of time series. 
	With Clustering, natural groupings of time series can be obtained.

	<br/>
	<br/>
	<br/>
	<br/>
	<div id="navigator">
		<input id="button_view" class="TopMenuButtons" style="" type="button" value="View Algorithms"/>
	</div>

</div>

<script type="text/javascript">
	$("#button_view").button().click(function () {	    
	    $("#navigator").load("./jsp/Similarity/navigator.jsp");
	});
</script>

</body>
</html>