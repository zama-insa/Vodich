<t:baseLayout>
<div class="container">
	<c:choose>
	<c:when test="${not empty error}">
	<div class="row">
			<div class="col-md-10">
					<div class="alert alert-danger alert-dismissible" role="alert">
					  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					  <strong>Error</strong><br/> ${error}
					</div>
			</div>
	</div>
	</c:when>
	<c:otherwise>
		<c:choose>
		<c:when test="${listView}">
			<c:choose>
			<c:when test="${empty resultList}">
				<div class="alert alert-success alert-dismissible" role="alert">
					<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<strong><fmt:message key="message_result_no_result_1" /></strong> 
					<fmt:message key="message_result_no_result_2" /> 
					<a href="default"><fmt:message key="message_result_no_result_3" /></a>
				</div>
			</c:when>
			<c:otherwise>
				<c:forEach items="${resultList}" var="result">
					<a href="result?rid=${result.id}">${result.scenarioId}</a><br/>
				</c:forEach>
			</c:otherwise>
			</c:choose>
		</c:when>
		<c:otherwise>
		<div class="row">
			<div class="col-md-12" id="linechart"></div>
		</div>
		<div class="row">
			<div class="col-md-3" id="totalcount"></div>
			<div class="col-md-3" id="datalost"></div>	
		</div>
		<br/>
		<div class="row">
			<div class="col-md-5">
				<p><a href="default"><i class="fa fa-arrow-left"></i> Default page</a>
			</div>
		</div>
		<script src="res/js/kibana-utils.js"></script>
		<script>
		var kibana = new KibanaUtils();
		$("#linechart").html(kibana.lineChartIframe('${rid}',800,200));
		$("#totalcount").html(kibana.totalCountIframe('${rid}'));
		$("#datalost").html(kibana.dataLostIframe('${rid}'));
		</script>
		</c:otherwise>
		</c:choose>
	</c:otherwise>
	</c:choose>
</div>
</t:baseLayout>