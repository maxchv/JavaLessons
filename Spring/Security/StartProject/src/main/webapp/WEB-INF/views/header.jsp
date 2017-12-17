<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
	<nav class="navbar navbar-inverse navbar-static-top">
		<div class="container">
			<div class="navbar-header">
				<a href="<spring:url value="/"/>" class="navbar-brand">Kevin's Auto Service Center</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="<spring:url value="/services/"/>">Services</a></li>
				<li><a href="<spring:url value="/appointments/"/>">Appointments</a></li>
				<li><a href="<spring:url value="/login/"/>">Sign In</a></li>
			</ul>
		</div>
	</nav>