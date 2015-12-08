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
<p>ScenarioID  : ${scenario.id }<p>
<c:forEach items="${scenario.flows}" var="flow">
		<p>Consumer : ${flow.consumer }</p>
      	<p>Producer : ${flow.producer }</p>
      	<p>Frequency : ${flow.frequency }</p>
      	<p>ProcessTime : ${flow.processTime }</p>
      	<p>Start : ${flow.start }</p>
      	<p>Stop : ${flow.stop }</p>
      	<p>MessageLoad : ${flow.messageLoad }</p>
</c:forEach>
</c:if>

</t:baseLayout>