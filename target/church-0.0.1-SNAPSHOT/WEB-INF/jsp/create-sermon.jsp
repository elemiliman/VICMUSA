<%@include file="includes/header.jsp" %>

	<div class="container">
		<div class="row">
			<%@include file="includes/sidebar.jsp" %>
			
			<div class="col-sm-9">
				
				<div class="panel panel-primary">

					<div class="panel-heading">
						<p class="panel-title">Sermon</h3>
					</div>
			
					<div class="panel-body">
					
						<form:form modelAttribute="sermonForm" id="myForm" enctype="multipart/form-data"   role="form">
						
							<div class="form-group">
								<form:errors class="col-lg-offset-2 col-sm-9" />
							</div>
							
							<div class="form-group">
								<form:label path="title" class="control-label">Title</form:label>
									<form:input path="title" class="form-control" placeholder="title" />
									<form:errors cssClass="error" path="title" />
							</div>
							
							<div class="form-group">
								<form:label path="scripture" class="control-label">Scripture</form:label>
								<form:input path="scripture" class="form-control" placeholder="scripture" />
								<form:errors cssClass="error" path="scripture" />
							</div>
							
							<div class="form-group">
								<form:label path="studyDate" class="control-label">Study Date</form:label>
									<form:input path="studyDate" class="form-control datepicker" data-date-format="mm/dd/yyyy"
										placeholder="mm-dd-yyyy" data-provide="datepicker" />
										<span class="add-on"><i class="icon-th"></i></span>
									<form:errors cssClass="error" path="studyDate" />
							</div>
							
							<div class="form-group">
								<form:label path="sermonText" class="control-label">Sermon</form:label>
								<form:textarea path="sermonText" class="form-control" id="sermon" name="sermon" rows="25"/>
								<form:errors cssClass="error" path="sermonText" />
							</div>
							
							<input type="hidden" name="sermontext" id="sermontext" value="">
							
							<div class="form-group">
								<form:label path="media" class="control-label">Media upload</form:label>
								<form:input path="" class="form-control"  type="file" name="file"/>
								<form:errors cssClass="error" path="media" />
							</div>
							
							<div class="form-group">
								<div>
									<button type="submit" class="btn btn-primary" id="createSermon">Save sermon</button>
								</div>
							</div>   
						</form:form>
					</div>
					<div class="panel-footer">
						<p>File Upload Progress...</p>
						<div id='progressBar' style='height: 30px; border: 2px solid #1e4162; margin-bottom: 20px; font-color:#000000'>
           					<div id='bar' style='height: 100%; font-color:#000000; background: #428bca; width: 0%'></div>
       					</div> 
       					<div id='debug' style='height: 100px; border: 2px solid #ccc; overflow: auto'></div>
            		</div>
				</div>
				
				<div class="panel panel-primary">
	
					<div class="panel-heading">
						<p class="panel-title">Bible App</h3>
					</div>
			
					<div class="panel-body">
						<biblia:bible class="bible-app" layout="normal" resource="kjv" width="100%" height="550px" startingReference=""></biblia:bible>
					</div>
				</div>
				
			</div>
		</div>
	</div>

<%@include file="includes/sidebar-footer.jsp" %>