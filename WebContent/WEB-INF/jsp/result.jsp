<t:baseLayout>
<div class="container">
	<div class="row">
		<div class="col-md-12">
		<h2><a href="result">Scenario result</a></h2>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<iframe src="http://localhost:5601/app/kibana#/visualize/edit/Linechart-Response-Time?embed&_g=(refreshInterval:(display:Off,pause:!f,value:0),time:(from:now-15m,mode:quick,to:now))&_a=(filters:!(),linked:!f,query:(query_string:(analyze_wildcard:!t,query:'*')),vis:(aggs:!((id:'1',params:(field:result.messageResults.time),schema:metric,type:avg),(id:'2',params:(field:result.messageResults.id,order:asc,orderBy:_term,size:100000),schema:segment,type:terms)),listeners:(),params:(addLegend:!t,addTimeMarker:!f,addTooltip:!t,defaultYExtents:!f,drawLinesBetweenPoints:!t,interpolate:linear,radiusRatio:9,scale:linear,setYExtents:!f,shareYAxis:!t,showCircles:!t,smoothLines:!f,spyPerPage:10,times:!(),yAxis:()),type:line))" height="600" width="1000"></iframe>
		</div>
	</div>
	<br/>
	<div class="row">
		<div class="col-md-5">
			<p><a href="default"><i class="fa fa-arrow-left"></i> Default page</a>
		</div>
	</div>
</div>
</t:baseLayout>