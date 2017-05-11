<%@include file="includes/header.jsp" %>

<div class="page-head teacher" >
	<div class="container">
		<h2 class="page-title">Teacher</h2>
	</div>
</div>
<br>
<div class="container my-custom-container">
	
      <div class="row-fluid">
		<div class="span8">
			<div class="row-fluid">
				<table class="table table-striped table-bordered">
					<thead class="thead-inverse">
						<tr>
							<th class="myColor"><strong>#</strong></th>
							<th class="myColor"><strong>Study Date</strong></th>
							<th class="myColor"><strong>Title</strong></th>
							<th class="myColor"><strong>Scripture</strong></th>
							<th class="myColor"><strong>Teacher</strong></th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${teacher}" var="sermon" varStatus="loopStatus">
							<tr class="${loopStatus.index % 2 == 0 ? 'odd' : 'even'}" > 
								<td>${offset + loopStatus.index + 1 }</td>
							<td><c:out value="${sermon.studyDateFormatted}" /></td>
							<td><a href="/sermons/sermon/${sermon.sermonId}"><c:out value="${sermon.title}" /></a></td>
							<td><c:out value="${sermon.scripture}" /></td>
							<td><c:out value="${sermon.user.firstName}" /> <c:out value="${sermon.user.lastName}" /></td>
						</tr>
					</c:forEach>
					<tr>
					</tbody>
				</table>
					<tag:paginate max="15" offset="${offset}" count="${count}" uri="/sermons" next="&raquo;" previous="&laquo;" />
			</div>
		</div>
	</div>
</div>

<%@include file="includes/footer.jsp" %>