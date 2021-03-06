<t:baseLayout>
	<script src="res/js/kibana-utils.js"></script>
	<div class="container">
		<c:choose>
			<c:when test="${not empty error}">
				<div class="row">
					<div class="col-md-10">
						<div class="alert alert-danger alert-dismissible" role="alert">
							<button type="button" class="close" data-dismiss="alert"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<strong>Error</strong><br /> ${error}
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
									<button type="button" class="close" data-dismiss="alert"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<strong><fmt:message key="message_result_no_result_1" /></strong>
									<fmt:message key="message_result_no_result_2" />
									<a href="default"><fmt:message
											key="message_result_no_result_3" /></a>
								</div>
							</c:when>
							<c:otherwise>
								<div class="row">
									<div class="col-md-9">
										<div class="panel-group" id="accordion" role="tablist"
											aria-multiselectable="true">
											<div class="panel panel-default">
												<c:forEach items="${resultList}" var="result">
													<div class="panel-heading" role="tab">
														<div class="row">
															<div class="col-xs-6 col-sm-3">
																<h4 class="panel-title">
																	<a class="resultItem" href="result?rid=${result.id}">${result.name}</a>
																</h4>
															</div>
														</div>
													</div>
												</c:forEach>
											</div>
										</div>
									</div>
								</div>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<div class="row" style="margin-bottom: 20px;">
							<div class="col-md-5">
								<h3>
									<fmt:message key="message_result" /> 
									<span style="color: #298EEA;">${result.name}</span>
								</h3>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12" id="linechart"></div>
						</div>
						<div class="row">
							<div class="col-md-3" id="totalcount"></div>
							<div class="col-md-3" id="datalost"></div>
						</div>
						<br />
						<script>
							var kibana = new KibanaUtils();
							$("#linechart").html(
									kibana.lineChartIframe('${result.id}', 800, 200));
							$("#totalcount").html(
									kibana.totalCountIframe('${result.id}'));
							$("#datalost")
									.html(kibana.dataLostIframe('${result.id}'));
						</script>
					</c:otherwise>
				</c:choose>
			</c:otherwise>
		</c:choose>
	</div>
</t:baseLayout>