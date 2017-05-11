<%@include file="includes/header.jsp" %>
<div class="container">
	<div class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2"> 
		<div class="panel panel-default">
		
			<div class="panel-heading">
				<h3 class="panel-title">Please register</h3>
			</div>
			
			<div class="panel-body">
			
				<form:form modelAttribute="signupForm" role="form">
				
					<form:errors />
					
					<div class="form-group">
						<form:label path="firstName">First Name</form:label>
						<form:input path="firstName" class="form-control" placeholder="Enter first name" />
						<form:errors cssClass="error" path="firstName" />
					</div>
					
					<div class="form-group">
						<form:label path="lastName">Last Name</form:label>
						<form:input path="lastName" class="form-control" placeholder="Enter last name" />
						<form:errors cssClass="error" path="lastName" />
					</div>
					
					<div class="form-group">
						<form:label path="userName">User Name</form:label>
						<form:input path="userName" class="form-control" placeholder="Enter username" />
						<form:errors cssClass="error" path="userName" />
					</div>
					
					<div class="form-group">
						<form:label path="email">Email Address</form:label>
						<form:input path="email" type="email" class="form-control" placeholder="Enter email" />
						<form:errors cssClass="error" path="email" />
					</div>
					
					<div class="form-group">
						<form:label path="password">Password</form:label>
						<form:password path="password" class="form-control" placeholder="Password" />
						<form:errors cssClass="error" path="password" />
					</div>
					
					<button type="submit" class="btn btn-primary">Create an account</button>
					
				</form:form>
			</div>
			<div class="panel-footer">
				Already have an account! <a href="/login">Log In Here</a>
            </div>
		</div>
	</div>
</div>
<!-- jQuery (necessary for Boostrap's JavaScript plugins)  -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.js"></script>
<script src="/public/lib/bootstrap-3.3.7/js/bootstrap.min.js"></script>
</body>