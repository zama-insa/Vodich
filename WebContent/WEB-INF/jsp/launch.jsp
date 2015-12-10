<t:baseLayout>
<div class="col-md-10">
			<c:if test="${not empty error}">
				<div class="alert alert-danger alert-dismissible" role="alert">
				  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				  <strong>Error</strong><br/> ${error}
				</div>
			</c:if>
			
			</div>
<c:if test ="${empty error}">
<p>Launch Done<p>
</c:if>

</t:baseLayout>