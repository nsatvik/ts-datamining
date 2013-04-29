<%@page contentType="text/html" import="java.util.*" %>
<%@page contentType="text/html" import="org.ck.sample.*" %>
<%@page contentType="text/html" import="org.ck.gui.*" %>
<%@page contentType="text/html" import="org.ck.forecaster.*" %>
<%@page contentType="text/html" import="org.ck.similarity.*" %>
<%@page contentType="text/html" import="org.ck.anomalifinder.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Time Series Data Mining Tool</title>    
    <title>TSDM Tool</title>
    <link rel="stylesheet" href="./styles/jquery-ui.css" />
    <link rel="stylesheet" href="./styles/MainPage.css"/>
    <script src="./js/jquery-1.9.1.js"></script>
	<script src="./js/jquery-ui.js"></script>
	<script type="text/javascript" src="https://www.google.com/jsapi"></script>
	<script type="text/javascript">
		
	</script>	  
    <script type="text/javascript" src="./js/MainPage.js"></script>
</head>

<body>      
    <img id="background-image" src="./images/bgimage.jpg" />   
    
    <div style="text-align: center;">                
        <div id="div_mainMenu">
            <input id="button_home" class="TopMenuButtons" style="width: 10%" type="button" value="Home"/>            
            <input id="button_aboutUs" class="TopMenuButtons" style="width: 10%" type="button" value="About Us"/>
            <input id="button_Donate" class="TopMenuButtons" style="width: 10%" type="button" value="Donate"/>   
        </div>
        

        <div id="div_ajaxFiller">
            Welcome To the RV All In One Time Series Data Mining Tool!
			<br>
			<div style="float: left; color:white;">					
				
			</div>
        </div>         
        
        <input id="hidden_params" type="text" value="Hidden Values"/>

    </div>
    
    <script type="text/javascript">
    	var loadingImgHTML = '<img src="./images/loading4.gif" />';			//Bad Practice, but really helpful global variable
    </script>    
</body>
</html>