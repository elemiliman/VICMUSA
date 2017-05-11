<%@include file="includes/header.jsp" %>

	<div class="container">
		<div class="row">
			
			<%@include file="includes/sidebar.jsp" %>
			
			<div class="col-sm-9">
				<div class="panel panel-primary">

					<div class="panel-heading">
						<p class="panel-title">Change password</h3>
					</div>
			
					<div class="panel-body">
					
						<form:form modelAttribute="userEditAccountForm" class="form-horizontal" role="form">
						
							<div class="form-group">
								<form:errors class="col-sm-offset-2 col-sm-8" />
							</div>
							
							<div class="form-group">
								<form:label path="oldPassword" class="col-lg-4 control-label">Old password</form:label>
								<div class="col-lg-6">
									<form:password path="oldPassword" class="form-control" placeholder="" />
									<form:errors cssClass="error" path="oldPassword" />
								</div>
							</div>
							
							<div class="form-group">
								<form:label path="password" class="col-lg-4 control-label">New password</form:label>
								<div class="col-lg-6">
									<form:password path="password" class="form-control" placeholder="" />
									<form:errors cssClass="error" path="password" />
								</div>
							</div>
							
							<div class="form-group">
								<form:label path="retypePassword" class="col-lg-4 control-label">Confirm new password</form:label>
								<div class="col-sm-6">
									<form:password path="retypePassword" class="form-control" placeholder="" />
									<form:errors cssClass="error" path="retypePassword" />
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-offset-4 col-sm-8">
									<button type="submit" class="btn btn-primary">Update password</button> &nbsp;&nbsp;
									<a href="/logout-forgotpassword">I forgot my password</a>
								</div>
							</div>   
							
						</form:form>
					</div>
				</div>
				
				<div class="panel panel-primary">

					<div class="panel-heading">
						<p class="panel-title">Change email (username)</h3>
					</div>
			
					<div class="panel-body">
						<div class="col-sm-8">
							<p>Changing your email address can have unintended side effects.</p>
							<button type="submit" class="btn btn-primary">Change email address</button> 
						</div>
					</div>
				</div>
				
			</div>
		</div>
	</div>


<%@include file="includes/sidebar-footer.jsp" %>