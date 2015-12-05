<t:baseLayout>
	<style>
		h2 a:hover {
			text-decoration: none;
		}
		.fa-minus {
			position: absolute;
  			right: 12px;
  			border-radius: 4px;
  			padding: 0 3px;
		}
		.fa-minus:hover {
			background-color: white;
			color: rgb(51, 122, 183);
		}
	</style>
	<script>
	$(document).ready(function() {
		$("i.fa-minus").click(function(event){
			$("input#action").val("removeFlow");
			$("#create-form").submit();
			event.preventDefault();
			return false;
		});
		$("#addFlowButton").click(function(event){
			$("input#action").val("addFlow");
			$("#create-form").submit();
			event.preventDefault();
			return false;
		});
	});
	</script>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
			<h2><a href="create">Create a scenario</a></h2>
			</div>
		</div>
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
		<form method="post" id="create-form">
		<div class="col-md-12">
		<div class="row">
		<div class="col-md-6 col-md-offset-4">
			<div class="form-group">
				<label for="name">Name</label>
				<input id="name" name="name" class="form-control" placeholder="Name of your scenario" value="${name}"/>
			</div>
		</div>
		</div>
		<div class="row">
		<div class="col-md-4">
			<ul class="nav nav-pills nav-stacked">
				<c:forEach begin="1" end="${flowcount}" var="i" varStatus="status">
					<li role="presentation" class="${currentflowview eq i ? 'active' : ''}">
						<a data-toggle="tab" href="#contentflow${i}">Flow ${i}
						<c:if test="${status.last and flowcount > 1}">
							<i class="fa fa-minus"></i>
						</c:if>
						</a>
					</li>
				</c:forEach>
				<li role="presentation"><a href="#" id="addFlowButton"><i class="fa fa-plus"></i></a></li>
			</ul>
		</div>
		<div class="col-md-6">
			<div class="tab-content">
			<c:forEach begin="1" end="${flowcount}" var="i">
				<div id="contentflow${i}" class="tab-pane fade ${currentflowview eq i ? 'in active' : ''}">
					<div class="flow">
						<h4>Flow ${i}</h4>
						<div class="form-group">
						  <label for="consumer${i}">Consumer</label>
						  <select class="form-control" id="consumer${i}" name="consumer${i}">
						  	<c:forEach begin="1" end="${CONSUMER_NUM}" var="j">
							    <option ${j eq map.consumer[i-1] ? 'selected': ''}>${j}</option>
						    </c:forEach>
						  </select>
					  	</div>
					  	<div class="form-group">
						  <label for="producer${i}">Producer</label>
						  <select class="form-control" id="producer${i}" name="producer${i}">
						  	<c:forEach begin="1" end="${PRODUCER_NUM}" var="j">
							    <option ${j eq map.producer[i-1] ? 'selected': ''}>${j}</option>
						    </c:forEach>
						  </select>
					  	</div>
					  	<div class="form-group">
						  <label for="processtime${i}">Process time</label>
						  <input class="form-control" id="processtime${i}" name="processtime${i}" placeholder="Producer Processing Time" value="${map.processtime[i-1]}"/>
					  	</div>
					  	<div class="form-group">
						  <label for="frequency${i}">Frequency</label>
						  <input class="form-control" id="frequency${i}" name="frequency${i}" placeholder="Sending frequency of consumer" value="${map.frequency[i-1]}"/>
					  	</div>
					  	<div class="form-group">
						  <label for="starttime${i}">Start at</label>
						  <input class="form-control" id="starttime${i}" name="starttime${i}" placeholder="Relative time when the consumer should start" value="${map.starttime[i-1]}"/>
					  	</div>
					  	<div class="form-group">
						  <label for="stoptime${i}">Finishing at</label>
						  <input class="form-control" id="stoptime${i}" name="stoptime${i}" placeholder="Relative time when the consumer should stop" value="${map.stoptime[i-1]}"/>
					  	</div>
					</div>
					<button class="btn btn-primary" type=submit>Create</button>
					<input type="hidden" id="flowcount" name="flowcount" value="${flowcount}"/>
					<input type="hidden" id="action" name="action" value=""/>
				</div>
			</c:forEach>
			</div>
		</div>
		</div>
		</div>
		</form>
		</div>
		</div>
</t:baseLayout>