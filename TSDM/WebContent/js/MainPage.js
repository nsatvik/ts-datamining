  	
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
	        $("#div_ajaxFiller").load("./jsp/home.jsp");
	    });
	
	    $("#button_createTest").button().click(function () {
	        removeUnnecessaryWidgets();
	    });
	
	    $("#button_aboutUs").button().click(function () {
	        removeUnnecessaryWidgets();                
	        $("#div_ajaxFiller").load("./jsp/about_us.jsp");                
	    });
		
	    $("#button_login").button().click(function () {
	        removeUnnecessaryWidgets();
	        $("#div_ajaxFiller").load("./ServerScripts/LoginScripts/Login_Form.php");                
	    });
	
	    $("#button_debug").button().click(function () {
	        removeUnnecessaryWidgets();                
	        $("#div_ajaxFiller").load("./ServerScripts/DebugScripts/Debug.php");                
	    });
	    $("#button_Donate").button().click(function () {
	        removeUnnecessaryWidgets();                
	        $("#div_ajaxFiller").load("./jsp/error_page.jsp");
	        
	    });
	}
	
	
	function removeUnnecessaryWidgets()
	{
	    $("#div_ajaxFiller").empty();
	}
	

    