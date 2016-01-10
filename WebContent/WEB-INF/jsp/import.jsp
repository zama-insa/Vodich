<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Import a scenario</title>
</head>
<body>
<t:baseLayout>
<button class="btn btn-primary" type=button onclick="location.href='create'"><fmt:message key="message_creation" /> </button>
<button class="btn btn-primary" type=button onclick="location.href='default'"> <fmt:message key="message_displayt" /> </button>
<button class="btn btn-primary" type=button onclick="location.href='import'"> <fmt:message key="message_importt" /> </button>
<br>
<br>


<head>
<title><fmt:message key="message_importt" /></title>


</head>

<h3><fmt:message key="message_importt" /></h3>
<fmt:message key="message_importselect" /><br />
<form action="import" method="post" enctype="multipart/form-data">
<input type="file" name="file" size="50" />
<br />
<input type="submit" value=<fmt:message key="message_importselect" /> />
</form>



</t:baseLayout>





</body>
</html>
