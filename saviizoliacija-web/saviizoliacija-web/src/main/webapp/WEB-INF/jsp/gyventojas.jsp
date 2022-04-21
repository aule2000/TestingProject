<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
	<p>Add new Gyventoja:</p>
<form:form method="post" modelAttribute="gyventojas">
	<form:label path="id">Id</form:label>
	<form:input path="id" type="text" required="required" />
	<form:errors path="id" />

	<form:label path="vardas">Vardas</form:label>
	<form:input path="vardas" type="text" required="required" />
	<form:errors path="vardas" />

	<form:label path="telNr">Telefono Numeris</form:label>
	<form:input path="telNr" type="text" required="required" />
	<form:errors path="telNr" />


	<button type="submit">OK</button>
</form:form>
</div>
<%@ include file="common/footer.jspf"%>