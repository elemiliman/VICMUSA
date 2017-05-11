<%@include file="includes/header.jsp" %>

<div class="page-head blog" >
	<div class="container">
		<h2 class="page-title">Blog</h2>
	</div>
</div>
<div class="container white-container">
	  <div class="row">

            <!-- Blog Entries Column -->
            <div class="col-md-8">

                <!-- First Blog Post -->
                <c:forEach items="${author}" var="blog" varStatus="loopStatus">
                <h2>
                    <strong><a href="/blogs/blog/${blog.blogId}"><c:out value="${blog.blogTitle}" /></a></strong>
                </h2>
                <p>
                    by <a href="/blogs/author/${blog.user.userName}"><c:out value="${blog.user.firstName}" /> <c:out value="${blog.user.lastName}" /></a>
                </p>
                <p><span class="glyphicon glyphicon-time"></span> Posted on <c:out value="${blog.blogDateFormatted}" /></p>
                <hr class="vicmblog">
    			<img class="img-responsive" src="${blog.media}" width="900" height="300" />
                <hr class="vicmblog">
                <p><c:out value="${blog.blogPlainText}" /></p>
                <a class="btn btn-primary btn-more" href="/blogs/blog/${blog.blogId}">Read More <span class="glyphicon glyphicon-chevron-right"></span></a>
                <hr class="vicmblog">
				</c:forEach>
                
                <!-- Pager -->
                <tag:paginate max="15" offset="${offset}" count="${count}" uri="/blogs/author/${blog.user.userName}" next="&raquo;" previous="&laquo;" />

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

<%@include file="includes/footer.jsp" %>