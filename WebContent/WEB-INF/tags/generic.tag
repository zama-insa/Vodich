<%@tag
	description="Just a default generic tag, useful to create layouts"
	language="java" pageEncoding="UTF-8"%>
<%@attribute name="head" fragment="true"%>
<%@attribute name="header" fragment="true"%>
<%@attribute name="messagePanel" fragment="true"%>
<%@attribute name="body" fragment="true"%>
<%@attribute name="footer" fragment="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<html lang="${language}">
<jsp:invoke fragment="head" />

<script src="res/js/script.js"></script>
<body>
	<div class="page-wrap">
		<form id="frmUser">
			<span class="label label-default"> Language :</span> <input
				class="btn btn-secondary" type="button" id="btnSubmitFrench"
				value="French" /> <input class="btn btn-secondary" type="button"
				id="btnSubmitEnglish" value="English" /> <br /> <br />
		</form>

		<jsp:invoke fragment="header" />
		<div id="body">
			<jsp:invoke fragment="body" />
		</div>
	</div>
	<div id="footer">
		<jsp:invoke fragment="footer" />
	</div>
</body>
</html>