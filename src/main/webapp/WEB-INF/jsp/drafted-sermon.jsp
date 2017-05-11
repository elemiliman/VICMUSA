<%@include file="includes/header.jsp" %>

         <div class="container">
        	<div class="row-fluid">
				<%@include file="includes/sidebar.jsp" %>
				<div class="col-sm-9">
					<table class="table table-striped table-bordered">
						<thead class="thead-inverse">
							<tr>
								<th class="myColor"><strong>#</strong></th>
								<th class="myColor"><strong>Study Date</strong></th>
								<th class="myColor"><strong>Title</strong></th>
								<th class="myColor"><strong>Scripture</strong></th>
								<th class="myColor"></th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${draftSermon}" var="sermon" varStatus="loopStatus">
								<tr class="${loopStatus.index % 2 == 0 ? 'odd' : 'even'}" > 
									<td>${offset + loopStatus.index + 1 }</td>
								<td><c:out value="${sermon.studyDate}" /></td>
								<td><a href="/sermon/${sermon.sermonId}/edit"><c:out value="${sermon.title}" /></a></td>
								<td><c:out value="${sermon.scripture}" /></td>
								<td><a href="/sermon/${sermon.sermonId}/publish">Click to publish sermon</a></td>
							</tr>
						</c:forEach>
						<tr>
						</tbody>
					</table>
					<tag:paginate max="15" offset="${offset}" count="${count}" uri="/sermon/drafts" next="&raquo;" previous="&laquo;" />
				</div>
			</div>
		</div>

<%@include file="includes/sidebar-footer.jsp" %>