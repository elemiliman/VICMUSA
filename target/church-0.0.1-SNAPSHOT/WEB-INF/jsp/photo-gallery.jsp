<%@include file="includes/header.jsp" %>
<div class="page-head gallery" >
	<div class="container">
		<h2 class="page-title">Gallery</h2>
	</div>
</div>
<br>
<div class="container my-custom-container">

	<div class="row">
		<c:forEach items="${gallery}" var="imageGallery">
			<div class="col-md-4 col-sm-12">
	  			<div class="img">
	    			<a target="_blank" href="<c:out value='${imageGallery}'/>">
	      				<img src="<c:out value='${imageGallery}'/>" alt="" width="300" height="200" >
	    			</a>
	  			</div>
			</div>
		</c:forEach>
	</div>
							
 	<tag:paginate max="15" offset="${offset}" count="${count}" uri="/gallery" next="&raquo;" previous="&laquo;" />


</div>

<%@include file="includes/footer.jsp" %>