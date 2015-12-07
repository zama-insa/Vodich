<t:baseLayout>

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

</t:baseLayout>