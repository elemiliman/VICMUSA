<%@include file="includes/header.jsp" %>

<div class="container my-custom-container">
	<div class="col-sm-12">
		<div class="jumbotron" id="jumbo">
			<div class="row">
			    <div class="col-sm-8">
			      	<H2><strong><c:out value="${blog.blogTitle}" /></strong></H2>
					<p class="medium"><span class="glyphicon glyphicon-time"></span> Posted on  <c:out value="${blog.blogDateFormatted}" /></p><br><br>
					<a href="<c:url value='/blogs'/>">Return to All Posts</a>
			    </div>
			    <div class="col-sm-push-2 col-md-2 text-center">
			      <img src="${blog.user.userAddress.profilePicture}" class="img-circle" onerror="this.src='/public/img/blank-profile-picture.png';" alt="" width="110" height="110"><br>
			      <h3><a href="/blogs/author/${blog.user.userName}"><c:out value="${blog.user.firstName}" /> <c:out value="${blog.user.lastName}" /></a></h3><br>
			      CONTRIBUTOR
			    </div>
			</div>
		</div>
	


<!-- Page Content -->

     <div class="row">

         <!-- Blog Post Content Column -->
         <div class="col-md-8">
         
             <!-- Preview Image -->
             <img class="img-responsive" src="${blog.media}" width="900" height="300" />

             <hr class="vicmblog">

             <!-- Post Content -->
             <div class="row">
			 <div class="panel-body">
					<textarea id="textarea">
					<c:out value="${blog.blogText}" />
					</textarea>
				</div>
			 </div>
             <hr class="vicmblog">

         </div>

         <!-- Blog Sidebar Widgets Column -->
         <div class="col-md-4">

             <!-- Blog Search Well -->
             <div class="well">
                 <h4>Blog Search</h4>
                 <div class="input-group">
                     <input type="text" class="form-control">
                     <span class="input-group-btn">
                         <button class="btn btn-default" type="button">
                             <span class="glyphicon glyphicon-search"></span>
                     </button>
                     </span>
                 </div>
                 <!-- /.input-group -->
             </div>

             <!-- Side Widget Well -->
             <div class="well">
                 <h4>Side Widget Well</h4>
                 <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Inventore, perspiciatis adipisci accusamus laudantium odit aliquam repellat tempore quos aspernatur vero.</p>
             </div>

         </div>

     </div>
     <!-- /.row -->
</div>
 </div>
 <!-- /.container -->

<%@include file="includes/sidebar-footer.jsp" %>