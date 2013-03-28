<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title>Similarity Detection Algorithms</title>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
<script>
$(function() {
	$( "#tabs_sim_nav" ).tabs().addClass( "ui-tabs-vertical ui-helper-clearfix" );
	$( "#tabs_sim_nav li" ).removeClass( "ui-corner-top" ).addClass( "ui-corner-left" );
});
</script>
<style>
	.ui-tabs-vertical { width: 55em; }
	.ui-tabs-vertical .ui-tabs-nav { padding: .2em .1em .2em .2em; float: left; width: 12em; }
	.ui-tabs-vertical .ui-tabs-nav li { clear: left; width: 100%; border-bottom-width: 1px !important; border-right-width: 0 !important; margin: 0 -1px .2em 0; }
	.ui-tabs-vertical .ui-tabs-nav li a { display:block; }
	.ui-tabs-vertical .ui-tabs-nav li.ui-tabs-active { padding-bottom: 0; padding-right: .1em; border-right-width: 1px; border-right-width: 1px; }
	.ui-tabs-vertical .ui-tabs-panel { padding: 1em; float: right; width: 40em;}
</style>
</head>
<body>
<div id="tabs_sim_nav">
	<ul>
		<li><a href="./jsp/Similarity/dtw.jsp">DTW Method</a></li>
		<li><a href="./jsp/Similarity/sax.jsp">SAX Method</a></li>
	</ul>	
</div>
</body>
</html>