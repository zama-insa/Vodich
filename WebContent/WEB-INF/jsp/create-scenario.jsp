<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<t:baseLayout>
	<button class="btn btn-primary" type=button onclick="location.href='create'"><fmt:message key="message_creation" /> </button>
    <button class="btn btn-primary" type=button onclick="location.href='default'"> <fmt:message key="message_displayt" /> </button>
    <button class="btn btn-primary" type=button onclick="location.href='import'"> <fmt:message key="message_importt" /> </button>
	
	<ul class="nav nav-pills">
<button type="button"  onclick="location.href='import'" class="btn btn-default" aria-label="Left Align">
  <img src="res/img/zama.png" class="images_petit"  >
</button>

  <li role="presentation"><a href="default">Default</a></li>
  <li role="presentation"><a href="#">Messages</a></li>
</ul>
	
	
	
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
			<h2><a href="create"><fmt:message key="message_creation" /></a></h2>
			</div>
		</div>
		<div class="row">
		<div class="col-md-10">
			<c:if test="${not empty error}">
				<div class="alert alert-danger alert-dismissible" role="alert">
				  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				  <strong><fmt:message key="message_error"/></strong><br/> ${error}
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
				<label for="name"><fmt:message key="message_name" /></label>
				<input id="name" name="name" class="form-control" placeholder=<fmt:message key="message_nameofsc" /> value="${name}"/>
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
						  <label for="consumer${i}"><fmt:message key="message_consumer" /></label>
						  <select class="form-control" id="consumer${i}" name="consumer${i}">
						  	<c:forEach begin="1" end="${CONSUMER_NUM}" var="j">
							    <option ${j eq map.consumer[i-1] ? 'selected': ''}>${j}</option>
						    </c:forEach>
						  </select>
					  	</div>
					  	<div class="form-group">
						  <label for="producer${i}"><fmt:message key="message_producer" /></label>
						  <select class="form-control" id="producer${i}" name="producer${i}">
						  	<c:forEach begin="1" end="${PRODUCER_NUM}" var="j">
							    <option ${j eq map.producer[i-1] ? 'selected': ''}>${j}</option>
						    </c:forEach>
						  </select>
					  	</div>
					  	<div class="form-group">
						  <label for="processtime${i}"><fmt:message key="message_processtime" /></label>
						  <input class="form-control" id="processtime${i}" name="processtime${i}" placeholder=<fmt:message key="message_producerprocessingtime" /> value="${map.processtime[i-1]}"/>
					  	</div>
					  	<div class="form-group">
						  <label for="frequency${i}"><fmt:message key="message_frequency" /></label>
						  <input class="form-control" id="frequency${i}" name="frequency${i}" placeholder= <fmt:message key="message_sendingfrequencyofconsumer" /> value="${map.frequency[i-1]}"/>
					  	</div>
					  	<div class="form-group">
						  <label for="starttime${i}"><fmt:message key="message_startsat" /></label>
						  <input class="form-control" id="starttime${i}" name="starttime${i}" placeholder=<fmt:message key="message_relativetimestart" /> value="${map.starttime[i-1]}"/>
					  	</div>
					  	<div class="form-group">
						  <label for="stoptime${i}"><fmt:message key="message_endsat" /></label>
						  <input class="form-control" id="stoptime${i}" name="stoptime${i}" placeholder=<fmt:message key="message_relativetimestop" /> value="${map.stoptime[i-1]}"/>
					  	</div>
					</div>
					<button class="btn btn-primary" type=submit><fmt:message key="message_create" /></button>
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
