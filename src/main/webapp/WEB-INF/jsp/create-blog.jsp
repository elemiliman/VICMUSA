<%@include file="includes/header.jsp" %>

	<div class="container">
		<div class="row">
			<%@include file="includes/sidebar.jsp" %>
			
			<div class="col-sm-9">
				
				<div class="panel panel-primary">

					<div class="panel-heading">
						<p class="panel-title">Blog</h3>
					</div>
			
					<div class="panel-body">
					
						<form:form modelAttribute="blogForm" enctype="multipart/form-data"  role="form">
						
							<div class="form-group">
								<form:errors class="col-sm-offset-2 col-sm-9" />
							</div>
							
							<div class="form-group">
								<form:label path="blogTitle" class="control-label">Title</form:label>
								<form:input path="blogTitle" class="form-control" placeholder="title" />
								<form:errors cssClass="error" path="blogTitle" />
							</div>
							
							<div class="form-group">
								<form:label path="blogDate" class="control-label">Blog Date</form:label>
									<form:input path="blogDate" class="form-control datepicker" data-date-format="mm/dd/yyyy"
										placeholder="mm-dd-yyyy" data-provide="datepicker"/>
										<span class="add-on"><i class="icon-th"></i></span>
									<form:errors cssClass="error" path="blogDate" />
							</div>
							
							<div class="form-group">
								<form:label path="blogText" class="control-label">Blog Content</form:label>
								<form:textarea path="blogText" class="form-control" id="blog" name="blog" rows="25"/>
								<form:errors cssClass="error" path="blogText" />
							</div>
							
							<input type="hidden" name="blogtext" id="blogtext" value="">
							
							<div class="form-group">
								<form:label path="media" class="control-label">Media upload</form:label>
								<form:input path="" class="form-control"  type="file" name="file"/>
								<form:errors cssClass="error" path="media" />
							</div>
							
							<div class="form-group">
								<div>
									<button type="submit" class="btn btn-primary" id="createBlog">Save blog</button>
								</div>
							</div>   
						</form:form>
					</div>
				</div>
				
				<div class="panel panel-primary">
	
					<div class="panel-heading">
						<h3 class="panel-title">Bible App</h3>
					</div>
			
					<div class="panel-body">
						<biblia:bible class="bible-app" layout="normal" resource="kjv" width="100%" height="550px" startingReference=""></biblia:bible>
					</div>
				</div>
				
			</div>
		</div>
	</div>

<%@include file="includes/sidebar-footer.jsp" %>