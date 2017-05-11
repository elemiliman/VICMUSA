<%@include file="includes/header.jsp" %>
<div class="container">
     <div class="row">
     
		<%@include file="includes/sidebar.jsp" %>
	    
        <div class="col-sm-9">
			<div class="panel panel-primary">
			    <div class="panel-heading">
			        <h3 class="panel-title">Profile</h3>
			    </div>
			    
			    <div class="panel-body">
			        <dl class="dl-horizontal">
			            <dt>First Name:</dt>
			            <dd><c:out value="${user.firstName}" /></dd>
			            <dt>Last Name:</dt>
			            <dd><c:out value="${user.lastName}" /></dd>
			            <dt>Email:</dt>
			            <dd><c:out value="${user.email}" /></dd>
			            <dt>Roles:</dt>
			            <dd><c:out value="${user.roles}" /></dd>
			            <dt>Gender:</dt>
			            <dd><c:out value="${user.userAddress.gender}" /></dd>
			            <dt>Birth Date:</dt>
			            <dd><c:out value="${user.userAddress.dob}" /></dd>
			            <dt>Address Line 1:</dt>
			            <dd><c:out value="${user.userAddress.addressLineOne}" /></dd>
			            <dt>Address Line 2:</dt>
			            <dd><c:out value="${user.userAddress.addressLineTwo}" /></dd>
			            <dt>City:</dt>
			            <dd><c:out value="${user.userAddress.city}" /></dd>
			            <dt>State:</dt>
			            <dd><c:out value="${user.userAddress.state}" /></dd>
			            <dt>Zip Code:</dt>
			            <dd><c:out value="${user.userAddress.zipCode}" /></dd>
			            <dt>County:</dt>
			            <dd><c:out value="${user.userAddress.country}" /></dd>
			        </dl>
			        
				    <c:if test="${user.editable}">
					    <div class="panel-footer">
					        <a class="btn btn-link" href="/users/${user.userName}/profile">Edit Profile</a>
					    </div>
					</c:if>
			    </div>
			</div>
		</div>
     </div>
</div>

<%@include file="includes/sidebar-footer.jsp" %>