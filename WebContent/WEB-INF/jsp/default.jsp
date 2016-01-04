<t:baseLayout>
<br>
<br>

<div class="container">
<div class="row">
<button class="btn btn-primary" type=button onclick="location.href='create'">Create New Scenario</button>
</div>
<br>
  <div class="row">
	<div class="col-md-10">
		<c:if test="${not empty error}">
			<div class="alert alert-danger alert-dismissible" role="alert">
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			  <strong>Error</strong><br/> ${error}
			</div>
		</c:if>
	</div>
	</div>
  <div class="row">
  <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
  <div class="panel panel-default">
  <c:forEach items="${scenarii }" var="scenario">
    <div class="panel-heading" role="tab" id="heading<c:out value="${scenario.id}"/>">
      <div class="row">
      <div class="col-xs-6 col-sm-3">
      <h4 class="panel-title">
        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#<c:out value="${scenario.id}"/>" aria-expanded="true" aria-controls="<c:out value="${scenario.id}"/>">
		${scenario.name }        </a>
      </h4>
	</div>
	<div class="col-xs-6 col-sm-3">
	<button class="btn btn-primary" type="button" onclick="location.href='launch?id=<c:out value="${scenario.id}'"/>">Launch</button>
	</div>
	<div class="col-xs-6 col-sm-3">
	<button class="btn btn-primary" type="button" onclick="location.href='delete?id=<c:out value="${scenario.id}'"/>">Delete</button>
	</div>
    </div>
    </div>
    <div id="<c:out value="${scenario.id}"/>" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading<c:out value="${scenario.id}"/>">
      <div class="panel-body">
      <p>Scenario ID : ${scenario.id }</p>
      <p>Scenario name : ${scenario.name }</p>
      <c:forEach items="${scenario.flows }" var="flow">
      	<p>Consumer : ${flow.consumer }</p>
      	<p>Producer : ${flow.producer }</p>
      	<p>Frequency : ${flow.frequency }</p>
      	<p>ProcessTime : ${flow.processTime }</p>
      	<p>Start : ${flow.start }</p>
      	<p>Stop : ${flow.stop }</p>
      	<p>MessageLoad : ${flow.messageLoad }</p>
      </c:forEach>
      </div>
    </div>
    </c:forEach>
    
  </div>
  </div>
</div>

</div>

  


</t:baseLayout>
