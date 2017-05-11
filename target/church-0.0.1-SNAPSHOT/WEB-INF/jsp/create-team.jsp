<%@include file="includes/header.jsp" %>

	<div class="container">
		<div class="row">
			<%@include file="includes/sidebar.jsp" %>
			
			<div class="col-sm-9">
				
				<div class="panel panel-primary">

					<div class="panel-heading">
						<p class="panel-title">Team
					</div>
			
					<div class="panel-body">
					
						<form:form modelAttribute="ourTeamForm" id="myForm" class="form-horizontal" enctype="multipart/form-data"   role="form">
						
							<div class="form-group">
								<form:errors class="col-lg-offset-2 col-sm-9" />
							</div>
							
							<h3>Basic Details</h3>
							<hr class="vicmblog">
							
							<div class="form-group">
								<form:label path="fullName" class="col-md-3 control-label">Full Name</form:label>
								<div class="col-md-6">
									<form:input path="fullName" class="form-control" placeholder="name surname" />
									<form:errors cssClass="error" path="fullName" />
								</div>
							</div>
							
							<div class="form-group">
								<form:label path="designation" class="col-md-3 control-label">Designation</form:label>
								<div class="col-md-6">
									<form:input path="designation" class="form-control" placeholder="designation" />
									<form:errors cssClass="error" path="designation" />
								</div>
							</div>
							
							<div class="form-group">
								<form:label path="faceBookProfile" class="col-md-3 control-label">Facebook Profile</form:label>
								<div class="col-md-6">
									<form:input path="faceBookProfile" class="form-control" placeholder="facebook profile" />
									<form:errors cssClass="error" path="faceBookProfile" />
								</div>
							</div>
							
							<div class="form-group">
								<form:label path="twitterProfile" class="col-md-3 control-label">Twitter Profile</form:label>
								<div class="col-md-6">
									<form:input path="twitterProfile" class="form-control" placeholder="twitter profile" />
									<form:errors cssClass="error" path="twitterProfile" />
								</div>
							</div>
							
							<div class="form-group">
								<form:label path="linkedInProfile" class="col-md-3 control-label">LinkedIn Profile</form:label>
								<div class="col-md-6">
									<form:input path="linkedInProfile" class="form-control" placeholder="LinkedIn profile" />
									<form:errors cssClass="error" path="linkedInProfile" />
								</div>
							</div>
							
							<div class="form-group">
								<form:label path="profilePicture" class="col-md-3 control-label">Profile Picture</form:label>
								<div class="col-md-6">
									<form:input path="" class="form-control"  type="file" name="file"/>
									<form:errors cssClass="error" path="profilePicture" />
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-offset-3 col-sm-9">
									<button type="submit" class="btn btn-primary">Save</button>
								</div>
							</div>   
						</form:form>
					</div>
				
				</div>
				
			</div>
		</div>
	</div>

<%@include file="includes/sidebar-footer.jsp" %>