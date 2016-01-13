<t:baseLayout>
<div class="container">
	<div class="row">
		<div class="col-md-12">
		<h2><a href="result">Scenario result</a></h2>
		</div>
	</div>
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
</div>
</t:baseLayout>