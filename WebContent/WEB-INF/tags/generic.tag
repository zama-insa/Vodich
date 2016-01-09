<%@tag description= "Just a default generic tag, useful to create layouts"
	   language="java"
       pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="messagePanel" fragment="true" %>
<%@attribute name="body" fragment="true" %>
<%@attribute name="footer" fragment="true" %>


<html lang="${language}">
      <jsp:invoke fragment="header"/>
      <script src="res/js/script.js"></script>
  <body>
  

        <form id="frmUser">
                <label for="txtName">Change language :</label>
                <input type="button" id="btnSubmitFrench" value="French" />
                <input type="button" id="btnSubmitEnglish" value="English" />
                <br />
                <br />
        </form>

  	<div class="page-wrap">
    <div id="header">
    	<form name="langForm" id="langForm" target="changeLanguage">
    	<div class="btn-group" role="group" aria-label="Button group with nested dropdown">
		<button class="btn btn-primary" type=button onclick="location.href='create'"><fmt:message key="message_creationnew" /></button>
		<button class="btn btn-primary" type=button onclick="location.href='default'"><fmt:message key="message_displayt" /></button>
		<%-- <button type="button" class="btn btn-secondary">1</button> --%>
		  <div class="btn-group" role="group">
		    <button id="btnGroupDrop1" type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		      Change Language
		    </button>
		    <div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
		    <a class="dropdown-item" onclick="" value="fr">Français</a> 
		    <a class="dropdown-item" onclick="" value="fr2">Français2</a> 
		
		   <a class="dropdown-item" onclick="switchLang('en');" value="en">English</a> 
		      
		    </div>
		</div>
		  </div></form>
    </div>

    <div id="body">
      <jsp:invoke fragment="body"/>
    </div>
    </div>
    <div id="footer">
      <jsp:invoke fragment="footer"/>
    </div>
  </body>
</html>