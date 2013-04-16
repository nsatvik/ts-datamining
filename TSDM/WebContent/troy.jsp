<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Troy Page</title>
</head>
<body>
	<h1>Sample Page for Testing!</h1>
	<% out.println("TEst"); %>
	<script type="text/javascript" src="https://www.google.com/jsapi"></script>
	<script type="text/javascript">
		//Load the Visualization API and the piechart package.
		google.load('visualization', '1.0', {
			'packages' : [ 'corechart' ]
		});

		// Set a callback to run when the Google Visualization API is loaded.
		google.setOnLoadCallback(drawChart);

		// Callback that creates and populates a data table, 
		// instantiates the pie chart, passes in the data and
		// draws it.
		function drawChart() {

			// Create the data table.
			var data = new google.visualization.DataTable();
			data.addColumn('number', 'Time');
			data.addColumn('number', 'Value');
			data.addColumn('number', 'Value2');
			data.addRows(<%out.println("[[ 0,-1.3382422126395963, 0],[ 1,0,-1.376596681557371],[ 2,0,-0.9163430545440788],[ 3,-0.5327983653663355, 0],[ 4,0,-0.18760814510636642],[ 5,0.9246714535090893, 0]]");%>);

			// Set chart options
			var options = {
				'title' : 'Time Series Graph',
				'width' : 500,
				'height' : 500
			};

			// Instantiate and draw our chart, passing in some options.
			var chart = new google.visualization.LineChart(document
					.getElementById('chart_div'));
			chart.draw(data, options);
		}
	</script>
<div id="chart_div" style="width:400; height:300"></div>
</body>
</html>