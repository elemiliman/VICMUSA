<%@include file="includes/header.jsp" %>

         <div class="container">
        	<div class="row">
				<%@include file="includes/sidebar.jsp" %>
					<div class="col-sm-9">
						<table class="table table-striped table-bordered">
							<thead class="thead-inverse">
								<tr>
									<th class="myColor"><strong>#</strong></th>
									<th class="myColor"><strong>First Name</strong></th>
									<th class="myColor"><strong>Last Name</strong></th>
									<th class="myColor"><strong>Email</strong></th>
									<th></th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${allUsers}" var="member" varStatus="loopStatus">
 								<tr class="${loopStatus.index % 2 == 0 ? 'odd' : 'even'}" > 
 									<td>${offset + loopStatus.index +1 }</td>
									<td><c:out value="${member.firstName}" /></td>
									<td><c:out value="${member.lastName}" /></td>
									<td><c:out value="${member.email}" /></td>
									<td> <a href="/users/${member.userName}/profile">Edit</a></td>
								</tr>
							</c:forEach>
							<tr>
							</tbody>
						</table>
 						<tag:paginate max="15" offset="${offset}" count="${count}" uri="/users/all" next="&raquo;" previous="&laquo;" />
 					</div>
			</div>
		</div>

<%@include file="includes/sidebar-footer.jsp" %>