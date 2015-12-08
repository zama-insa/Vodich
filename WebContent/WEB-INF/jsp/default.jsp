<t:baseLayout>
<br>
<br>

<div class="container">
<div class="row">
<button class="btn btn-primary" type=button onclick="location.href='create'">Create New Scenario</button>
</div>
<br>
  <div class="row">
  <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
  <div class="panel panel-default">
  <c:forEach items="${scenarii }" var="scenario">
    <div class="panel-heading" role="tab" id="heading${scenario.id }">
      <div class="row">
      <div class="col-xs-6 col-sm-3">
      <h4 class="panel-title">
        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#${scenario.id }" aria-expanded="true" aria-controls="${scenario.id }">
		${scenario.id }        </a>
      </h4>
	</div>
	<div class="col-xs-6 col-sm-3">
	<button class="btn btn-primary" type=button onclick="location.href='launch?id=${scenario.id}'">Launch</button>
	</div>
	<div class="col-xs-6 col-sm-3">
	<button class="btn btn-primary" type=button onclick="location.href='default'">Delete</button>
	</div>
    </div>
    </div>
    <div id="${scenario.id }" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading${scenario.id }">
      <div class="panel-body">
      <p>Scenario ID : ${scenario.id }</p>
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
