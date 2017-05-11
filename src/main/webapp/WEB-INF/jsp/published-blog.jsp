<%@include file="includes/header.jsp" %>

         <div class="container">
        	<div class="row">
        		<%@include file="includes/sidebar.jsp" %>
				<div class="col-sm-9">
					<table class="table table-striped table-bordered">
						<thead class="thead-inverse">
							<tr>
								<th class="myColor"><strong>#</strong></th>
								<th class="myColor"><strong>Blog Date</strong></th>
								<th class="myColor"><strong>Title</strong></th>
<!-- 									<th><strong>Delete Blog</strong></th> -->
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${publishedBlog}" var="blog" varStatus="loopStatus">
								<tr class="${loopStatus.index % 2 == 0 ? 'odd' : 'even'}" > 
									<td>${offset + loopStatus.index + 1 }</td>
								<td><c:out value="${blog.blogDateFormatted}" /></td>
								<td><a href="/blog/${blog.blogId}/edit"><c:out value="${blog.blogTitle}" /></a></td>
<%-- 									<td> <a href="/users/${sermon.userId}/profile">Edit</a></td> --%>
							</tr>
						</c:forEach>
						<tr>
						</tbody>
					</table>
					<tag:paginate max="15" offset="${offset}" count="${count}" uri="/blog/published" next="&raquo;" previous="&laquo;" />
				</div>
			</div>
		</div>

<%@include file="includes/sidebar-footer.jsp" %>