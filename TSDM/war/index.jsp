<%@page contentType="text/html" import="java.util.*" %>
<%@page contentType="text/html" import="org.ck.sample.*" %>
<%@page contentType="text/html" import="org.ck.gui.*" %>
<%@page contentType="text/html" import="org.ck.forecaster.*" %>
<%@page contentType="text/html" import="org.ck.similarity.*" %>
<%@page contentType="text/html" import="org.ck.anomalifinder.*" %>
<% final String PROJECT_PATH = "../webapps/tsdm/"; %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Time Series Data Mining Tool</title>

    <meta charset="utf-8" />
    <title>TSSV</title>
    <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.10/themes/mint-choc/jquery-ui.css" />
    <script src="http://code.jquery.com/jquery-1.8.2.js"></script>
    <script src="http://code.jquery.com/ui/1.9.1/jquery-ui.js"></script>        

    <script type="text/javascript">  	
        //For the Background Image
        $(document).ready(function () {

            var bgImage = $('#background-image');

            function resizeImg() {
                var imgwidth = bgImage.width(),
                     imgheight = bgImage.height(),
                     winwidth = $(window).width(),
                     winheight = $(window).height(),
                     widthratio = winwidth / imgwidth,
                     heightratio = winheight / imgheight,
                     widthdiff = heightratio * imgwidth,
                     heightdiff = widthratio * imgheight;

                if (heightdiff > winheight) {
                    bgImage.css({
                        width: winwidth + 'px',
                        height: heightdiff + 'px'
                    });
                } else {
                    bgImage.css({
                        width: widthdiff + 'px',
                        height: winheight + 'px'
                    });
                }

                $("#background-image").show();

            }
            resizeImg();
            $(window).resize(function () {
                resizeImg();
            });

        });
	</script>

    <script type="text/javascript">
        //The Main Function, executed when the document is ready
        $(function ()
        {            
            initMainMenuButtons();           
        });


        //All helper methods are defined below
        function initMainMenuButtons()
        {
            $("#button_home").button().click(function () {
                removeUnnecessaryWidgets();
            });

            $("#button_testYourself").button().click(function () {
                removeUnnecessaryWidgets();
            });

            $("#button_createTest").button().click(function () {
                removeUnnecessaryWidgets();
            });

            $("#button_aboutUs").button().click(function () {
                removeUnnecessaryWidgets();                
                $("#div_ajaxFiller").load("./ServerScripts/AboutUsScripts/About_Us.php");                
            });

            $("#button_support").button().click(function () {
                removeUnnecessaryWidgets();
            });

            $("#button_login").button().click(function () {
                removeUnnecessaryWidgets();
                $("#div_ajaxFiller").load("./ServerScripts/LoginScripts/Login_Form.php");                
            });

            $("#button_debug").button().click(function () {
                removeUnnecessaryWidgets();                
                $("#div_ajaxFiller").load("./ServerScripts/DebugScripts/Debug.php");                
            });

            $("#button_statistics").button().click(function () {
                removeUnnecessaryWidgets();                
                $("#div_ajaxFiller").load("./ServerScripts/StatisticsScripts/RetrieveCourseScript.php");                
            });
        }


        function removeUnnecessaryWidgets()
        {
            $("#div_ajaxFiller").empty();
        }

    </script>

    <style type="text/css">
        #Button1 {
            height: 33px;
            width: 116px;
        }
        #background-image{
            position:absolute;
            top:0;
            left:0;
            z-index:-10;
            overflow: hidden;
            width: 100%;
            display:none;
        }
    </style>

</head>

<body>      
    <img id="background-image" src="./images/project_name.png" />  
    
    <div style="text-align: center;">                
        <div id="div_mainMenu">
            <input id="button_home" class="TopMenuButtons" style="width: 10%" type="button" value="Home"/>            
            <input id="button_aboutUs" class="TopMenuButtons" style="width: 10%" type="button" value="About Us"/>   
        </div>
        

        <div id="div_ajaxFiller">
            PlaceHolder for code that will be retrieved from JSP scripts through AJAX
			<br>
			<div style="float: left;">
				<% 
					Sample seaSample = new Sample(PROJECT_PATH + DataHolder.TRAINING_FILE_NAME,"Sea Level Data");	
					out.println("Name = " + seaSample.getName() + "\tNum of Values = " + seaSample.getNumOfValues());
					String list = MainClass.getSortedSimilarSeries(seaSample).replaceAll("\n", "<br>");				
					list = list.replaceAll("\t", "----------->");
					out.println("<br>" + list);
				%>
			</div>
        </div>

    </div>
    
</body>
</html>