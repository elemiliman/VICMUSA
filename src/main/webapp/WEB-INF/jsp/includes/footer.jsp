	<footer class="site-footer">
		<div class="container my-custom-container">
			<div class="row">
				<div class="col-md-4">
					<div class="widget">
						<h3 class="widget-title">Our address</h3>
						<p>Colossians 3:16 "Let the message of Christ dwell among you richly as you teach and admonish one another with all wisdom through psalms, hymns, and songs from the Spirit, singing to God with gratitude in your hearts"</p>
						<ul class="address">
							<li><i class="fa fa-map-marker"></i> 1140 Teller Avenue Bronx NY 10456</li>
							<li><i class="fa fa-phone"></i> (718) 542-7417</li>
							<li><i class="fa fa-envelope"></i> contact@vicmusa.org</li>
						</ul>
					</div>
				</div>
				<div class="col-md-4">
					<div class="widget">
						<h3 class="widget-title">Topics from last meeting</h3>
						<ul class="bullet">
							<li><a href="#">Lorem ipsum dolor sit amet</a></li>
							<li><a href="#">Consectetur adipisicing elit quis nostrud</a></li>
							<li><a href="#">Eiusmod tempor incididunt ut labore et dolore magna</a></li> 
							<li><a href="#">Ut enim ad minim veniam cillum</a></li>
							<li><a href="#">Exercitation ullamco laboris nisi ut aliquip</a></li> 
							<li><a href="#">Duis aute irure dolor in reprehenderit in voluptate</a></li>
						</ul>
					</div>
				</div>
				<div class="col-md-4">
					<div class="widget">
						<h3 class="widget-title">Contact form</h3>
						<form action="#" class="contact-form">
							<div class="row">
								<div class="col-md-6"><input type="text" placeholder="Your name..."></div>
								<div class="col-md-6"><input type="text" placeholder="Email..."></div>
							</div>
							
							<textarea name="" placeholder="Your message..."></textarea>
							<div class="text-right"><input type="submit" value="Send message"></div>
							
						</form>
					</div>
				</div>
			</div> <!-- .row -->
	
			<p class="colophon">Copyright 2017 Vineyard International Christian Ministries Inc. All right reserved</p>
		</div><!-- .container -->
	</footer> <!-- .site-footer -->
	
	</div>
		
	<!-- jQuery (necessary for Boostrap's JavaScript plugins)  -->
	<script src="/public/lib/bootstrap-3.3.7/js/jquery-3.1.1.min.js"></script>
	<script src="/public/lib/bootstrap-3.3.7/js/bootstrap.min.js"></script>
	<script src="/public/lib/bootstrap-3.3.7/js/plugins.js"></script>
	<script src="/public/lib/bootstrap-3.3.7/js/app.js"></script>
	<script src="/public/lib/bootstrap-3.3.7/js/bootstrap-datepicker.js"></script>
	<script src="//cdn.tinymce.com/4/tinymce.min.js"></script>
	<script src="//biblia.com/api/logos.biblia.js"></script>
	<script src="/public/lib/bootstrap-3.3.7/js/country-state.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.3/js/bootstrap-select.min.js"></script>
	
	<script>
	$(document).ready(function() {
		// get current URL path and assign 'active' class
		var pathname = window.location.pathname;
		$('.menu > li > a[href="'+pathname+'"]').parent().addClass('current-menu-item');
	})
	</script>
	
	<script>
	/* When the user clicks on the button, 
	toggle between hiding and showing the dropdown content */
	function myFunction() {
	    document.getElementById("myDropdown").classList.toggle("show");
	}
	</script>
		
	</body>

</html>