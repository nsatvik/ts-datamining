<html lang="en">
<head>
<meta charset="utf-8" />
<title>Similarity Main Page</title>
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
	<div style="margin-top: 1%; margin-left: auto; margin-right: auto;" id="tabs_all_nav">
	<ul>
		<li><a href="./jsp/Similarity/main.jsp">Similarity Detective</a></li>		
		<li><a href="./jsp/Forecaster/main.jsp">Fortune-Teller</a></li>
		<li><a href="./jsp/Anomaly/main.jsp">Anomaly Detective</a></li>
		<li><a href="./jsp/PatternFinder/main.jsp">Temporal Pattern Detective</a></li>
	</ul>
	
	</div>
</body>
</html>