<t:baseLayout>
	<div class="container">
		<div class="row">
		<div class="col-md-4">
			<h2>Create a scenario</h2>
			<c:if test="${not empty error}">
				<div class="alert alert-danger alert-dismissible" role="alert">
				  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				  <strong>Form Error</strong><br/> ${error}
				</div>
			</c:if>
			<form method="post" id="create-scenario">
				<div class="form-group">
					<label for="name">Name</label>
					<input id="name" name="name" class="form-control" placeholder="Name of your scenario" value="${name}"/>
				</div>
				<div class="flow">
					<h4>Flow1</h4>
					<div class="form-group">
					  <label for="consumer1">Consumer</label>
					  <select class="form-control" id="consumer1" name="consumer1">
					  	<c:forEach begin="1" end="${CONSUMER_NUM}" var="i">
						    <option ${i eq map.consumer[0] ? 'selected': ''}>${i}</option>
					    </c:forEach>
					  </select>
				  	</div>
				  	<div class="form-group">
					  <label for="producer1">Producer</label>
					  <!-- TODO : make a for loop -->
					  <select class="form-control" id="producer1" name="producer1">
					  	<c:forEach begin="1" end="${CONSUMER_NUM}" var="i">
						    <option ${i eq map.producer[0] ? 'selected': ''}>${i}</option>
					    </c:forEach>
					  </select>
					  </select>
				  	</div>
				  	<div class="form-group">
					  <label for="processtime1">Process time</label>
					  <!-- TODO : make a for loop -->
					  <input class="form-control" id="processtime1" name="processtime1" placeholder="Producer Processing Time" value="${map.processtime[0]}"/>
				  	</div>
				  	<div class="form-group">
					  <label for="frequency1">Frequency</label>
					  <!-- TODO : make a for loop -->
					  <input class="form-control" id="frequency1" name="frequency1" placeholder="Sending frequency of consumer" value="${map.frequency[0]}"/>
				  	</div>
				  	<div class="form-group">
					  <label for="starttime1">Start at</label>
					  <!-- TODO : make a for loop -->
					  <input class="form-control" id="starttime1" name="starttime1" placeholder="Relative time when the consumer should start" value="${map.starttime[0]}"/>
				  	</div>
				  	<div class="form-group">
					  <label for="stoptime1">Finishing at</label>
					  <!-- TODO : make a for loop -->
					  <input class="form-control" id="stoptime1" name="stoptime1" placeholder="Relative time when the consumer should stop" value="${map.stoptime[0]}"/>
				  	</div>
				</div>
				<button class="btn btn-primary" type=submit>Create</button>
				<input type="hidden" id="flowcount" name="flowcount" value="${flowcount}"/>
			</form>
		</div>
		</div>
	</div>
</t:baseLayout>