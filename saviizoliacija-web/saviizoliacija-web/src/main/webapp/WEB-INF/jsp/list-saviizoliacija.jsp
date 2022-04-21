<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
<H1>List of Saviizoliacijos List:</H1>
<!--
<p>${saviizoliacija}</p>
-->
<table border="1">
<caption>Saviizoliacija</caption>
<thead>
<tr>
<th>User Id</th>
<th>Id</th>
<th>data</th>
<th>saviizoliacija on/off</th>
<th>Update</th>
<th>Delete</th>
</tr>
</thead>
<tbody>
<c:forEach items="${saviizoliacija}" var="sav">
<tr>
<td>${sav.gyventojai.vardas}</td>
<td>${sav.id}</td>
<td>${sav.data}</td>
<td>${sav.saviizoliacijaON}</td>
<td><a type="button" href="/update-saviizoliacija/${sav.id}">UPDATE</a></td>
<td><a type="button" href="/delete-saviizoliacija/${sav.id}">DELETE</a></td>
</tr>
</c:forEach>

</tbody>
</table>
<div>
<a href="add-saviizoliacija">ADD Saviizoliacijos irasa</a>
</div>
</div>
<%@ include file="common/footer.jspf"%>