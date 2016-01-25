function KibanaUtils() {}

KibanaUtils.prototype.lineChartIframe = function(rid, width, height) {
	if (!width) width = 500;
	if (!height) height = 200;
	return "<iframe src=\"http://localhost:5601/app/kibana#/visualize/edit/Response-Time?" +
			"embed&_g=(refreshInterval:(display:Off,pause:!f,value:0)," +
			"time:(from:now-15m,mode:quick,to:now))&_a=(filters:!(),linked:!f," +
			"query:(query_string:(analyze_wildcard:!t,query:'_type:%22" + rid + "%22'))," +
			"vis:(aggs:!((id:'1',params:(field:time),schema:metric,type:avg)," +
			"(id:'2',params:(field:id,order:asc,orderBy:_term,size:100000)," +
			"schema:segment,type:terms)),listeners:()," +
			"params:(addLegend:!f,addTimeMarker:!f,addTooltip:!f,defaultYExtents:!f," +
			"drawLinesBetweenPoints:!t,interpolate:linear,radiusRatio:9,scale:linear," +
			"setYExtents:!f,shareYAxis:!t,showCircles:!t,smoothLines:!f,times:!(),yAxis:()),type:line))\"" +
			" height=\"" + height + "\" width=\"" + width + "\">" +
			"</iframe>";
}

KibanaUtils.prototype.totalCountIframe = function(rid) {
	return "<iframe src=\"http://localhost:5601/app/kibana#/visualize/edit/Total-Count?" +
			"embed&_g=(refreshInterval:(display:Off,pause:!f,value:0)," +
			"time:(from:now-15m,mode:quick,to:now))&_a=(filters:!(),linked:!f," +
			"query:(query_string:(analyze_wildcard:!t,query:'_type:%22" + rid + "%22'))," +
			"vis:(aggs:!((id:'1',params:(),schema:metric,type:count))," +
			"listeners:(),params:(fontSize:30),type:metric))\"" +
			" height=\"150\" width=\"200\">" +
			"</iframe>";
}

KibanaUtils.prototype.dataLostIframe = function(rid) {
	return "<iframe src=\"http://localhost:5601/app/kibana#/visualize/edit/Data-Lost?" +
			"embed&_g=(refreshInterval:(display:Off,pause:!f,value:0)," +
			"time:(from:now-15m,mode:quick,to:now))&_a=(filters:!(),linked:!f," +
			"query:(query_string:(analyze_wildcard:!t,query:'_type:%22" + rid + "%22'))," +
			"vis:(aggs:!((id:'1',params:(field:time,values:!(-1))," +
			"schema:metric,type:percentile_ranks)),listeners:(),params:(fontSize:'30'),type:metric))\"" + 
			" height=\"150\" width=\"200\">" +
			"</iframe>";
}