<t:baseLayout>
	<div class="container">
	<h2>Create a scenario</h2>
	<form method="post" id="create-scenario">
		<div class="form-group">
			<label for="name">Name</label>
			<input id="name" name="name" class="form-control" placeholder="Name of your scenario"/>
		</div>
		<div class="flow">
			<h4>Flow1</h4>
			<div class="form-group">
			  <label for="consumer">Consumer</label>
			  <!-- TODO : make a for loop -->
			  <select class="form-control" id="consumer">
				    <option>1</option>
				    <option>2</option>
				    <option>3</option>
				    <option>4</option>
				    <option>5</option>
			  </select>
		  	</div>
		  	<div class="form-group">
			  <label for="producer">Producer</label>
			  <!-- TODO : make a for loop -->
			  <select class="form-control" id="producer">
				    <option>1</option>
				    <option>2</option>
				    <option>3</option>
				    <option>4</option>
				    <option>5</option>
			  </select>
		  	</div>
		  	<div class="form-group">
			  <label for="process-time">Process time</label>
			  <!-- TODO : make a for loop -->
			  <input class="form-control" id="process-time" placeholder="Producer Processing Time"/>
		  	</div>
		</div>
		<button class="btn btn-primary" type=submit>Create</button>
	</form>
	</div>
</t:baseLayout>