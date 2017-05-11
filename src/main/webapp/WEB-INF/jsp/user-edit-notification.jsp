<%@include file="includes/header.jsp" %>
	<div class="container">
		<div class="row">
			<%@include file="includes/sidebar.jsp" %>
			
			<div class="col-sm-6">
				<div class="panel panel-primary">

					<div class="panel-heading">
						<h3 class="panel-title">Notifications</h3>
					</div>
			
					<div class="panel-body">
					
						<form:form modelAttribute="userEditNotificationForm" class="form-horizontal" role="form">
						
							<div class="form-group">
								<form:errors class="col-lg-offset-2 col-lg-7" />
							</div>
							
							<p><strong>Notify me of :</strong></p>
							
							<div class="form-group">
								<div class="col-lg-4">
									<form:checkbox path="blogUpdates" value="New Blog Post" /> New Blog Post
									<form:errors cssClass="error" path="blogUpdates" />
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-lg-4">
									<form:checkbox path="eventUpdates" value="New Events"/> New Events
									<form:errors cssClass="error" path="eventUpdates" />
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-lg-4">
									<form:checkbox path="sermonUpdates" value="Sermon Updates"/> New Sermons
									<form:errors cssClass="error" path="sermonUpdates" />
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-lg-4">
									<form:checkbox path="weeklyUpdates" value="Weekly Updates"/> Weekly Updates
									<form:errors cssClass="error" path="weeklyUpdates" />
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-lg-7">
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