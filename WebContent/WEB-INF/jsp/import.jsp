<t:baseLayout>

<div class="container-fluid">
  <div class="row">
  <div class="col-md-2"><button type="button"  onclick="location.href='default'" class="btn btn-default" aria-label="Left Align">
  <img src="res/img/zama.png" class="images_petit"  >
</button>
</div>
    <div class="col-md-1"><a href="accessibility"> <fmt:message key="message_gettingStarted"/> </a></div>
    <div class="col-md-1" ><a href="default"> <fmt:message key="message_scenarii"/> </a> </div>
    <div class="col-md-1"><a href="contact"> Contact</a></div>
    <div class="col-md-1"> <button class="btn btn-primary" type=button onclick="location.href='create'"><fmt:message key="message_creation" /> </button></div>
    <div class="col-md-1"> <button class="btn btn-primary" type=button onclick="location.href='import'"> <fmt:message key="message_importt" /> </button></div>
   </div>
</div>

	<div class="container">
		<div class="row">
			<div class="col-md-12">
			<h2><a href="import"><fmt:message key="message_importt" /></a></h2>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
			<h2><a href="import"><fmt:message key="message_importt" /></a></h2>
			</div>
		</div>


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
