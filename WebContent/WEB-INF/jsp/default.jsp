<t:baseLayout>

	<div class="container">
		<br>
		<div class="row">
			<div class="col-md-10">
				<c:if test="${not empty error}">
					<div class="alert alert-danger alert-dismissible" role="alert">
						<button type="button" class="close" data-dismiss="alert"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<strong>Error</strong><br /> ${error}
					</div>
				</c:if>
			</div>
		</div>

		<c:choose>
			<c:when test="${empty scenarii}">
				<div class="alert alert-success alert-dismissible" role="alert">
					<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<strong><fmt:message key="message_default_no_scenario_1" /></strong> 
					<fmt:message key="message_default_no_scenario_2" /> 
					<a href="create"><fmt:message key="message_default_no_scenario_3" /></a>
				</div>
			</c:when>
			<c:otherwise>
				<div class="row">
					<div class="col-md-9">
					<div class="panel-group" id="accordion" role="tablist"
						aria-multiselectable="true">
						<div class="panel panel-default">
							<c:forEach items="${scenarii }" var="scenario">
								<div class="panel-heading" role="tab"
									id="heading<c:out value="${scenario.id}"/>">
									<div class="row">
										<div class="col-md-6">
											<h4 class="panel-title">
												<a role="button" data-toggle="collapse"
													data-parent="#accordion"
													href="#<c:out value="${scenario.id}"/>"
													aria-expanded="true"
													aria-controls="<c:out value="${scenario.id}"/>">
													${scenario.name } </a>
												<a href="result">
													<span class="badge" title="${scenario.name} launched ${scenario.totalLaunches} times">${scenario.totalLaunches}</span>
												</a>
											</h4>
										</div>
										<div class="col-md-4 col-md-offset-2">
											<button class="btn btn-success" type="button"
												onclick="location.href='launch?id=<c:out value="${scenario.id}'"/>">
												<fmt:message key="message_launch" />
											</button>
											<button class="btn btn-danger" type="button"
												onclick="location.href='delete?id=<c:out value="${scenario.id}'"/>">
												<fmt:message key="message_delete" />
											</button>
											<a class="btn btn-primary" type="button" 
											id="btnExport" href="export?id=<c:out value="${scenario.id}"/>" target="_blank">
											<fmt:message key="message_export"/></a>
										</div>
									</div>
								</div>
								<div id="<c:out value="${scenario.id}"/>"
									class="panel-collapse collapse" role="tabpanel"
									aria-labelledby="heading<c:out value="${scenario.id}"/>">
									<div class="panel-body">
										<p>Scenario name : ${scenario.name }</p>
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
										</div>										
										
										</div>
																				<div class="col-md-8"><div id="timeline<c:out value="${scenario.id}"/>"></div>
										
										
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
									</div>
								</div>
							</c:forEach>

						</div>
					</div>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</t:baseLayout>