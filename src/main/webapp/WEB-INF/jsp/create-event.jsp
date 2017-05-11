<%@include file="includes/header.jsp" %>

	<div class="container">
		<div class="row">
			<%@include file="includes/sidebar.jsp" %>
			
			<div class="col-sm-9">
				
				<div class="panel panel-primary">

					<div class="panel-heading">
						<p class="panel-title">Event
					</div>
			
					<div class="panel-body">
					
						<form:form modelAttribute="eventForm" id="myForm" class="form-horizontal" enctype="multipart/form-data"   role="form">
						
							<div class="form-group">
								<form:errors class="col-lg-offset-2 col-sm-9" />
							</div>
							
							<h3>Event Details</h3>
							<hr class="vicmblog">
							
							<div class="form-group">
								<form:label path="eventName" class="col-md-2 control-label">Event Name</form:label>
								<div class="col-md-8">
									<form:input path="eventName" class="form-control" placeholder="Event Name" />
									<form:errors cssClass="error" path="eventName" />
								</div>
							</div>
							
							<div class="form-group">
								<form:label path="eventDescription" class="col-md-2 control-label">Description</form:label>
								<div class="col-md-8">
									<form:textarea path="eventDescription" class="form-control" id="event-desc" rows="10"/>
									<form:errors cssClass="error" path="eventDescription" />
								</div>
							</div>
							
							<div class="form-group">
								<form:label path="eventLocation" class="col-md-2 control-label">Location</form:label>
								<div class="col-md-8">
									<form:input path="eventLocation" class="form-control" placeholder="Location" />
									<form:errors cssClass="error" path="eventLocation" />
								</div>
							</div>
							
							<div class="form-group">
								<form:label path="eventStartDate" class="col-md-2 control-label">Start Date</form:label>
								<div class="col-md-4">
									<form:input path="eventStartDate" class="form-control" id="fromDate" data-date-format="mm/dd/yyyy"
										placeholder="mm/dd/yyyy" data-provide="datepicker" />
									<form:errors cssClass="error" path="eventStartDate" />
								</div>
							</div>
							
							<div class="form-group">
								<form:label path="eventEndDate" class="col-md-2 control-label">End Date</form:label>
								<div class="col-md-4">
									<form:input path="eventEndDate" class="form-control" id="toDate" data-date-format="mm/dd/yyyy"
										placeholder="mm/dd/yyyy" data-provide="datepicker" />
										<span class="add-on"><i class="icon-th"></i></span>
									<form:errors cssClass="error" path="eventEndDate" />
								</div>
							</div>
							
							<div class="form-group">
								<form:label path="eventTime" class="col-md-2 control-label">Time</form:label>
								<div class="col-md-6">
									<form:input path="eventTime" class="form-control" placeholder="Time" />
									<form:errors cssClass="error" path="eventTime" />
								</div>
							</div>
							
							<div class="form-group">
								<form:label path="eventImage" class="col-md-2 control-label">Image upload</form:label>
								<div class="col-md-6">
									<form:input path="" class="form-control"  type="file" name="file"/>
									<form:errors cssClass="error" path="eventImage" />
									<p class="help-block">upload event image if any</p>
								</div>
							</div>
							
	
							<br>
							
							<h3>Organizer</h3>
							<hr class="vicmblog">
							
							<div class="form-group">
								<form:label path="organizerName" class="col-md-2 control-label">Name</form:label>
								<div class="col-md-8">
									<form:input path="organizerName" class="form-control" placeholder="Name" />
									<form:errors cssClass="error" path="organizerName" />
								</div>
							</div>
							
							<div class="form-group">
								<form:label path="organizerPhone" class="col-md-2 control-label">Phone</form:label>
								<div class="col-md-8">
									<form:input path="organizerPhone" class="form-control" placeholder="Phone" />
									<form:errors cssClass="error" path="organizerPhone" />
								</div>
							</div>
							
							<div class="form-group">
								<form:label path="organizerEmail" class="col-md-2 control-label">Email</form:label>
								<div class="col-md-8">
									<form:input path="organizerEmail" type="email" class="form-control" placeholder="Email" />
									<form:errors cssClass="error" path="organizerEmail" />
								</div>
							</div>
							
							<div class="form-group">
								<form:label path="organizerWebsite" class="col-md-2 control-label">Website</form:label>
								<div class="col-md-8">
									<form:input path="organizerWebsite" class="form-control" placeholder="Website" />
									<form:errors cssClass="error" path="organizerWebsite" />
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-9">
									<button type="submit" class="btn btn-primary">Save Event</button>
								</div>
							</div>   
						</form:form>
					</div>
				
				</div>
				
			</div>
		</div>
	</div>

<%@include file="includes/sidebar-footer.jsp" %>