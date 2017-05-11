<%@include file="includes/header.jsp" %>

<div class="container my-custom-container">

	<div class="col-sm-12">
		<div class="jumbotron" id="jumbo">
		  <div class="row">
			    <div class="col-md-8">
			      	<p><strong>TITLE:</strong> <c:out value="${sermon.title}" /></p>
					<p><strong>STUDY DATE:</strong> <c:out value="${sermon.studyDateFormatted}" /></p>
					<p><strong>SCRIPTURE:</strong> <c:out value="${sermon.scripture}" /></p><br>
					<a href="<c:url value='/sermons'/>">Return to All Sermons</a>
			    </div>
			    <div class="col-sm-push-2 col-md-2 text-center">
			      <img src="${sermon.user.userAddress.profilePicture}" class="img-circle" onerror="this.src='/public/img/blank-profile-picture.png';" alt="" width="110" height="110"><br>
			      <h3><a href="/sermons/teacher/${sermon.user.userName}"><c:out value="${sermon.user.firstName}" /> <c:out value="${sermon.user.lastName}" /></a></h3>
			    </div>
		  </div>
		</div>
		
		<div class="row">
			<div class="panel-body">
				<textarea id="textarea">
					<c:out value="${sermon.sermonText}" />
				</textarea>
			</div>
		</div>
		
		<div class="panel-footer">
			<p><strong>MEDIA</strong></p>
		</div>
		
	</div>
</div>

<%@include file="includes/sidebar-footer.jsp" %>