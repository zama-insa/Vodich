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
										<div class="col-md-3">
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
										<div class="col-md-3 col-md-offset-6">
											<button class="btn btn-success" type="button"
												onclick="location.href='launch?id=<c:out value="${scenario.id}'"/>">
												<fmt:message key="message_launch" />
											</button>
											<button class="btn btn-danger" type="button"
												onclick="location.href='delete?id=<c:out value="${scenario.id}'"/>">
												<fmt:message key="message_delete" />
											</button>
										</div>
										<div class="col-xs-6 col-sm-3">
											<button class="btn btn-primary" type="button" 
											id="btnExport" onclick="location.href='export?id=<c:out value="${scenario.id}'"/>">
											<fmt:message key="message_export"/></button>
	                                        <input type="file" id="ctrl" webkitdirectory directory multiple/>
										
										</div>
									</div>
								</div>
								<div id="<c:out value="${scenario.id}"/>"
									class="panel-collapse collapse" role="tabpanel"
									aria-labelledby="heading<c:out value="${scenario.id}"/>">
									<div class="panel-body">
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
			</c:otherwise>
		</c:choose>
	</div>
</t:baseLayout>

