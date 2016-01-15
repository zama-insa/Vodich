<t:baseLayout>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
			<h2><a href="import"><fmt:message key="message_importt" /></a></h2>
			</div>
		</div>
		
<form action="import" method="post" enctype="multipart/form-data">
<input type="file" name="file" size="50" />
<br />
<input type="submit" value=<fmt:message key="message_importselect"/> />
</form>

</t:baseLayout>


