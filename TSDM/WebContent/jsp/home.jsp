<html lang="en">
<head>
<meta charset="utf-8" />
<title>Similarity Main Page</title>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css" />
<script>
$(function() {
	$( "#tabs_all_nav" ).tabs({
		beforeLoad: function( event, ui ) {
			ui.jqXHR.error(function() {
				ui.panel.html(
				"Couldn't load this tab. We'll try to fix this as soon as possible. " +
				"If this wouldn't be a demo." );
			});
		}
	});
});
</script>
</head>
<body>
	<div style="margin-top: 1%;" id="tabs_all_nav">
	<ul>
		<li><a href="./jsp/Similarity/main.jsp">Similarity Detective</a></li>		
		<li><a href="./jsp/Forecaster/main.jsp">Fortune-Teller</a></li>
		<li><a href="./jsp/Anomaly/main.jsp">Anomaly Detective</a></li>
		<li><a href="./jsp/PatternFinder/main.jsp">Temporal Pattern Detective</a></li>
	</ul>
	
	</div>
</body>
</html>