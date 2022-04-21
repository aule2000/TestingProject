<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
	<p>Add new Saviizoliacijos Irasa:</p>
<form:form method="post" modelAttribute="saviizoliacija">
	<form:label path="id">Id</form:label>
	<form:input path="id" type="text" required="required" />
	<form:errors path="id" />

	<form:label path="gyventojai">UserId</form:label>
	<form:input path="gyventojai" type="text" required="required" />
	<form:errors path="gyventojai" />

	<form:label path="data">Data</form:label>
	<form:input path="data" type="text" required="required" />
	<form:errors path="data" />

<form:label path="saviizoliacijaON">Saviizoliacija ON/OFF</form:label>
	<form:input path="saviizoliacijaON" type="text" required="required" />
	<form:errors path="saviizoliacijaON" />


	<button type="submit">OK</button>
</form:form>
</div>
<%@ include file="common/footer.jspf"%>