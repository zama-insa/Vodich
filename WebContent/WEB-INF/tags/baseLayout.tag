<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic>
	<jsp:attribute name="head">
		<jsp:include page="/WEB-INF/tags/head.jsp"/>
	</jsp:attribute>
	<jsp:attribute name="header">
		<jsp:include page="/WEB-INF/tags/header.jsp"/>
	</jsp:attribute>
	
	<jsp:attribute name="body">
		<jsp:doBody/>
	</jsp:attribute>
	
	<jsp:attribute name="footer">
		<jsp:include page="/WEB-INF/tags/footer.jsp"/>
	</jsp:attribute>
</t:generic>
