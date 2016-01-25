
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
      <p><b>Scenario name : ${scenario.name }</b></p>
      <div class="row">
  			<div class="col-md-4">
      <c:forEach items="${scenario.flows }" var="flow">
      	
      	<div class="panel panel-default">
  			<div class="panel-body">
      	<p>Consumer : ${flow.consumer }</p>
      	<p>Producer : ${flow.producer }</p>
      	<p>Frequency : ${flow.frequency }</p>
      	<p>ProcessTime : ${flow.processTime }</p>
      	<p>Start : ${flow.start }</p>
      	<p>Stop : ${flow.stop }</p>
      	<p>MessageLoad : ${flow.messageLoad }</p>
      	</div>
      	</div>
      
      </c:forEach>
      	</div >
      	<div class="col-md-8"><div id="timeline<c:out value="${scenario.id}"/>"></div></div>
    
      	</div>
      <script type="text/javascript">
	    	var items = new vis.DataSet({
		        type: { start: 'ISODate', end: 'ISODate' }
		    });
	    	   var groups = new vis.DataSet()
	    	   var flowid =1
	    	   <c:forEach items="${scenario.flows }" var="flow">
	    	   
	    	   groups.add([{
	    	        id: 'flow'+flowid, content:'flow'+flowid, subgroupOrder: 'subgroupOrder' // this group has no subgroups but this would be the other method to do the sorting.
	    	    }]);
	    	   flowid = flowid +1;
				</c:forEach>
		    // add items to the DataSet
		    var id = 1
		    <c:forEach items="${scenario.flows }" var="flow">
		   	items.add([{id:"flow"+id, start: '0000-01-01 00:00:<c:out value="${flow.start}"/>', end: '0000-01-01 00:00:<c:out value="${flow.stop}"/>',group:'flow'+id}]);
		   	id = id +1;
		   	</c:forEach>
		   
		    var container = document.getElementById('timeline<c:out value="${scenario.id}"/>');
		    var options = {
		        // orientation:'top'
		        start: '0000-01-01 00:00:00',
		        end: '0000-01-01 00:01:00',
		        editable: true,
		        stack: false
		    };

		    var timeline = new vis.Timeline(container, items, groups, options);

	   
      </script>
          <div id="scenario" style="height: 180px;"></div>
      </div>
    </div>

    </c:forEach>
    
  </div>
  </div>
</div>

</div> 
		

</t:baseLayout>
