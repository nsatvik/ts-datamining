<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="org.ck.gui.Constants" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
</head>
<body>

	
	<div style="float: left;">
		<p>
			<label for="sample_range">Range:</label> <input type="text"
				id="sample_range" style="width:55%; color: #f6931f; font-weight: bold;" value="0 to 1400" readonly />
		</p>
		<div id="slider-range"></div>
		<br/>		
		<input id="button_addSlider" type="button" value="Add Slider"/>
		  
		<div id="div_sliders">
		</div>
		
		<input id="button_removeSlider" type="button" value="Remove Slider"/>
		
		
		
		
		
	</div>

	<div id="div_cusumUpdate">
		

		<div id="line_chart_div"
			style="width: 900px; height: 500px; float: left;">Time Series Graph Appears Here!</div>

	</div>
	<script type="text/javascript">
		//These two functions are used to update and redraw the graphs
		var gatherRanges = function(){
			var str = $("#sample_range").val();
			for(var i=1; i<subSliderID; i++){
				str += ";" + $("#subRange_" + i).val();
			};
			
			$("#hidden_params").val(str);
		};
	
		var updateGraph = function(){
			gatherRanges();		
			
			 $("#div_cusumUpdate").load("MainController", 
		        		{"taskType" : "<%=Constants.TaskType.ANOMALY_DETECTIVE %>",
				 		 "subTaskType" : "<%=Constants.SubTaskType.UPDATE_GRAPH %>",
		        		 "algorithmType" : "<%=Constants.AlgorithmType.CUSUM %>",
		        		 "dataset" : $("#dropdown option:selected").val(),
		        		 "params" : $("#hidden_params").val()
		        		}
		        );
		};		
		
		updateGraph();	
		
		//A unique number for slider ids
		var subSliderID = 1;
	
		//These two functions can be used to initialize a slider with id = sliderID, and connect its output to a text box with id = rangeBoxID
			
		var createSlider = function(sliderID, rangeBoxID) {
			$( "#" + sliderID ).slider({
								range : true,
								min : 0,
								max : ${tsBean.sample.numOfValues},
								values : [ 0, ${tsBean.sample.numOfValues}],
								create: function( event, ui ) {
									initRangeBox(sliderID, rangeBoxID);									
								},
								slide : function(event, ui) {
									$("#" + rangeBoxID).val(
											"" + ui.values[0] + " to "
													+ ui.values[1]);
								},
								change : function(event, ui) {
									updateGraph();	
								}
							});			
		};
		
		var initRangeBox = function(sliderID, rangeBoxID){
			$("#" + rangeBoxID).val(
					"" + $("#" + sliderID).slider("values", 0) + " to "
							+ $("#" + sliderID).slider("values", 1));
		}
		
		createSlider("slider-range", "sample_range");
	</script>
	<script type="text/javascript">
		$("#button_addSlider").button().click(function(){
			$("#div_sliders").append(function(index){
				return '<p>'
						+ '<label id="subLabel_' + subSliderID + '" for="subRange_' + subSliderID + '">Range:</label> <input type="text"'
					 	+ 'id="subRange_' + subSliderID + '" style="width:55%; color: #f6931f; font-weight: bold;" readonly />'						
						+ '<div id="subSlider_' + subSliderID + '"></div>'
						+ '</p>';
			});
			createSlider('subSlider_' + subSliderID, 'subRange_' + subSliderID);
			subSliderID++;
			updateGraph();
		});
		
		$("#button_removeSlider").button().click(function(){
			subSliderID--;
			$("#subSlider_" + subSliderID).remove();
			$("#subRange_" + subSliderID).remove();
			$("#subLabel_" + subSliderID).remove();
			updateGraph();
		});
		
		$("#dropdown").change(function(){
			 $("#ajax_cusum_algo_results").load("MainController", 
		        		{"taskType" : "<%=Constants.TaskType.ANOMALY_DETECTIVE %>",
		        		 "algorithmType" : "<%=Constants.AlgorithmType.CUSUM %>",
		        		 "dataset" : $("#dropdown option:selected").val()
		        		}
		        );
		});
	</script>
</body>
</html>