<%@include file="includes/header.jsp" %>

<div class="carousel fade-carousel slide" data-ride="carousel" data-interval="4000" id="bs-carousel">

  <!-- Indicators -->
  <ol class="carousel-indicators">
    <li data-target="#bs-carousel" data-slide-to="0" class="active"></li>
    <li data-target="#bs-carousel" data-slide-to="1"></li>
    <li data-target="#bs-carousel" data-slide-to="2"></li>
  </ol>
  
  <!-- Wrapper for slides -->
  <div class="carousel-inner">
    <div class="item slides active">
    <div class="overlay"></div>
      <div class="slide-1"></div>
      <div class="hero">
        <hgroup>
            <h1>Vineyard Ministries</h1>        
            <h3>A place with real love</h3>
        </hgroup>
        <button class="btn btn-hero btn-lg" role="button">Read More</button>
      </div>
    </div>
    <div class="item slides">
    <div class="overlay"></div>
      <div class="slide-2"></div>
      <div class="hero">        
        <hgroup>
            <h1>VICM Bible School</h1>        
            <h3>Register Now!</h3>
        </hgroup>       
        <button class="btn btn-hero btn-lg" role="button">See Details</button>
      </div>
    </div>
    <div class="item slides">
    <div class="overlay"></div>
      <div class="slide-3"></div>
      <div class="hero">        
        <hgroup>
            <h1>We are amazing</h1>        
            <h3>Get start your next awesome project</h3>
        </hgroup>
        <button class="btn btn-hero btn-lg" role="button">See all features</button>
      </div>
    </div>
  </div> 
</div>

<main class="main-content">
             
    <div class="container our-mission text-center">
          <div class="row">
            <div class="col-md-4 col-sm-6">
              <span class="glyphicon glyphicon-book"></span> <span class="our-mission-heading">&nbsp;&nbsp;Our Community</span>
              <p>Our ministry is open and friendly with many social activities. To keep our ministry running smoothly, we have committees anyone can join.</p>
              <br>
              <p>
                <a href = "#" class = "btn btn-primary  btn-more" role = "button">More</a>
              </p>
            </div>
            <div class="col-md-4 col-sm-6">
              <span class="glyphicon glyphicon-book"></span> <span class="our-mission-heading">&nbsp;&nbsp;Church Mission</span>
              <p>The mission of our ministry is to bring awareness about God's existence to people of all ages and encourage them to learn their own faith.</p>
              <br>
              <p>
                <a href = "#" class = "btn btn-primary  btn-more" role = "button">More</a>
              </p>
            </div>
            <div class="col-md-4 col-sm-6">
              <span class="glyphicon glyphicon-book"></span> <span class="our-mission-heading">&nbsp;&nbsp;Join a Group</span>
              <p>If you wish to become a part of our ministry or a volunteer to help those in need, our community is awaiting you with our open hearts.</p>
              <br>
              <p>
                <a href = "#" class = "btn btn-primary btn-more" role = "button">More</a>
              </p>
            </div>
          </div>
    </div>
     
 <div class="container why-our-church">
     <h2 class="section-title-white"><strong>WHY OUR MINISTRY</strong></h2>

     <div class="row">
         <div class="col-md-6">

             <p class="ministry-believes"> We are a ministry that<br>believes in <span class="Jesus">Jesus</span>, a ministry<br>that loves God and people</h1>
             <p class="White-Color">We have a strong sense of community with parishioners.<br> 
                People and children of all ages here are encouraged to<br> learn about 
                their own faith and the role of the church in<br> our community and worldwide.
             </p><br>
             <p>
                 <a href = "<c:url value='/about-us'/>" class = "btn btn-primary btn-more" role = "button">More</a> 
             </p>

         </div>

         <div class="col-md-6">

         	<img class="img-responsive img-portfolio img-hover" src="/public/img/new-brighton.jpg" alt="">

          </div>
     </div><!-- .row -->
 </div><!-- .container -->
    
     
     
<div class="container from-the-gallery">
    <h2 class="section-title"><strong>FROM THE GALLERY</strong></h2>
    <div class="row">
    	<c:forEach items="${fromGallery}" var="imageGallery">
            <div class="col-md-4 col-sm-6">
                <a href="<c:url value='/gallery'/>">
                    <img class="img-responsive img-portfolio img-hover" src="<c:out value='${imageGallery}'/>" alt="">
                </a>
            </div>
     	</c:forEach>
      </div><!-- .row -->
</div><!-- .container -->
           

<div class="container upcoming-event ">
	<div class="row">
		<div class="col-sm-12">
			<h2 class="section-title-white"><strong>UPCOMING EVENTS</strong></h2>
			<ul class="event-list">
			
				<c:forEach items="${events}" var="event">
				<li>
					<a href="/events/event/${event.eventId}">
						<h3 class="event-title "><c:out value="${event.eventName}" /></h3>
						<span class="event-meta ">
							<span class="hidden-xs White-Color"><i class="fa fa-calendar"></i> <c:out value="${event.eventStartDateFormatted}" /></span>
							<span class="hidden-xs hidden-sm White-Color"><i class="fa fa-map-marker"></i> <c:out value="${event.eventLocation}" /></span>
						</span>
					</a>
				</li>
				</c:forEach>
				
			</ul>
		</div>
	</div> <!-- .row -->
</div> <!-- .container -->
            

<div class="container from-the-blog text-justify">
	<h2 class="section-title"><strong>FROM THE BLOG</strong></h2>
	<div class = "row">
	   <c:forEach items="${blogs}" var="blog" varStatus="loopStatus">
		   <div class = "col-sm-6 col-md-4">
		      <div class = "thumbnail">
		         <img class="img-responsive" src = "${blog.media}" alt = "Generic placeholder thumbnail">
		      </div>
		      
		      <div class = "caption">
		         <h3 class="myColor-1"><a href="/blogs/blog/${blog.blogId}"><c:out value="${blog.blogTitle}" /></a></h3>
		         <p><c:out value="${blog.blogPlainText}" /></p>
		         
		         <p>
		            <a href = "/blogs/blog/${blog.blogId}" class = "btn btn-primary btn-more" role = "button">
		               More
		            </a> 
		         </p>
		         
		      </div>
		   </div>
	   </c:forEach>
	</div>
	  
</div>
     
<div class="container pastor-podcast">

    <div class="row">
        <div class="col-sm-8">
        <h2 class="section-title-white"><strong>PASTOR</strong></h2>
            <div class="row">
              <div class="col-md-3 img-pastor">
              	<img class="img-responsive img-hover" src="/public/img/CEO.JPG" alt="">
              </div>
              <div class="col-md-9">
                <p class="White-Color">
                <span class="Pastor-Tile-Message">Heaven's Court Order</span><br>
                <span>You have been accused of accepting Jesus Christ as your Lord and savior and bombarding God with prayers! 
                You are hereby sentenced to Everlasting Life, No bail, No Appeal. You have been labeled Blessed and hereby 
                detained in God's custody forever. Amen. You have been designated and destined to make it successful whatsoever 
                your hand finds to do and you shall surely achieve all your life's goals. Amen.<br>
                <span class="podcast-author"><strong>Joe Omeokwe</strong></span></span>
                </p>
              </div>
            </div>		
        </div>

        <div class="col-md-4">
          <h2 class="section-title-white"><strong>PODCASTS</strong></h2>
         <p class="podcast">
          <button type="button" id="button_play" class="btn" onclick='buttonPlayPress()'>
            <i class="fa fa-play"></i>
          </button> &nbsp;&nbsp;<strong class="White-Color">HOLY SPIRIT: STRATEGIC THINKING</strong><br>
         </p>
         <p class="White-Color">by <span class="podcast-author"><strong>Joe Omeokwe</strong></span></p>
         <hr class="hr-podcast">

          <p class="podcast">
          <button type="button" id="button_play" class="btn" onclick='buttonPlayPress()'>
            <i class="fa fa-play"></i>
          </button> &nbsp;&nbsp;<strong class="White-Color">HOLY SPIRIT: STRATEGIC THINKING</strong><br>
         </p>
         <p class="White-Color">by <span class="podcast-author"><strong>Joe Omeokwe</strong></span></p>
         <hr class="hr-podcast">

         <p class="podcast">
          <button type="button" id="button_play" class="btn" onclick='buttonPlayPress()'>
            <i class="fa fa-play"></i>
          </button> &nbsp;&nbsp;<strong class="White-Color">HOLY SPIRIT: STRATEGIC THINKING</strong><br>
         </p>
         <p class="White-Color">by <span class="podcast-author"><strong>Joe Omeokwe</strong></span></p>
         <hr class="hr-podcast">
        </div>
    </div><!-- .row -->
</div><!-- .container -->
</main> <!-- .main-content -->


<%@include file="includes/footer.jsp" %>