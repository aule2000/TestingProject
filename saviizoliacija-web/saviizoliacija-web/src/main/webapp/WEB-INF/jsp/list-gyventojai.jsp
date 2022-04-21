<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
<H1>List of Gyventojai:</H1>

<!--
<p>${gyventojai}</p>
-->

<table border="1">
<caption>Gyventojai</caption>
<thead>
<tr>
<th>Id</th><th>Vardas</th><th>Telefono Numeris</th><th>Update</th><th>Delete</th>
</tr>
</thead>
<tbody>
<c:forEach items="${gyventojai}" var="gyventojas">
<tr>
<td>${gyventojas.id}</td>
<td>${gyventojas.vardas}</td>
<td>${gyventojas.telNr}</td>
<td><a type="button" href="/update-gyventojas/${gyventojas.id}">UPDATE</a></td>
<td><a type="button" href="/delete-gyventojas/${gyventojas.id}">DELETE</a></td>
</tr>
</c:forEach>

</tbody>
</table>

<div>
<a href="add-gyventoja">ADD Gyventoja</a>
</div>
</div>
<%@ include file="common/footer.jspf"%>
