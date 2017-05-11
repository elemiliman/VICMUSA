<footer class="site-footer">
		<div class="container my-custom-container">
			<div class="row">
				<div class="col-md-4">
					<div class="widget">
						<h3 class="widget-title">Our address</h3>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Modi perspiciatis magnam, ab ipsa laboriosam tempore tenetur, aliquid repellat, ex cum dicta reiciendis accusamus. Omnis repudiandae quasi mollitia, iusto odio dignissimos.</p>
						<ul class="address">
							<li><i class="fa fa-map-marker"></i> 1140 Teller Avenue Bronx NY 10456</li>
							<li><i class="fa fa-phone"></i> (425) 853 442 552</li>
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
<!-- JavaScript Plug-ins  -->
<script src="/public/lib/bootstrap-3.3.7/js/jquery-3.1.1.min.js"></script>
<script src="/public/lib/bootstrap-3.3.7/js/bootstrap.min.js"></script>
<script src="/public/lib/bootstrap-3.3.7/js/plugins.js"></script>
<script src="/public/lib/bootstrap-3.3.7/js/app.js"></script>
<script src="/public/lib/bootstrap-3.3.7/js/bootstrap-datepicker.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.3/js/bootstrap-select.min.js"></script>
<script src="//cdn.tinymce.com/4/tinymce.min.js"></script>
<script src="//biblia.com/api/logos.biblia.js"></script>


<!-- Date Picker -->
<script>
$('.datepicker').datepicker({
    format: 'mm-dd-yyyy'
});
</script>

<!-- Date Picker Start and End Date -->
<script>
//set default dates
var start = new Date();
// set end date to max one year period:
var end = new Date(new Date().setYear(start.getFullYear()+1));

$('#fromDate').datepicker({
	format: 'mm-dd-yyyy',
    startDate : start,
    endDate   : end
// update "toDate" defaults whenever "fromDate" changes
}).on('changeDate', function(){
    // set the "toDate" start to not be later than "fromDate" ends:
    $('#toDate').datepicker('setStartDate', new Date($(this).val()));
}); 

$('#toDate').datepicker({
	format: 'mm-dd-yyyy',
    startDate : start,
    endDate   : end
// update "fromDate" defaults whenever "toDate" changes
}).on('changeDate', function(){
    // set the "fromDate" end to not be later than "toDate" starts:
    $('#fromDate').datepicker('setEndDate', new Date($(this).val()));
});
</script>

<!-- TinyMCE - Sermon -->
<script>
	tinymce.init({
		selector:'#sermon',
		height: 500,
		browser_spellcheck: true,
		  theme: 'modern',
		  plugins: [
		            "advlist autolink lists link image charmap print preview anchor",
		            "searchreplace visualblocks code fullscreen",
		            "insertdatetime media table contextmenu paste"
		        ],
		        toolbar: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image"
		 });
</script>

<!-- Send Sermon Plain Text -->
<script>
function sermonPlainText() {
	var plainText = tinymce.get('sermon').getContent({format : 'text'});
	document.getElementById('sermontext').value = plainText;
}

//Event listeners for button click
window.onload = function() {
    document.getElementById('createSermon').addEventListener('click', sermonPlainText(), false);
}
</script>

<!-- TinyMCE -->
<script>
	tinymce.init({
		selector:'#blog',
		height: 500,
		browser_spellcheck: true,
		  theme: 'modern',
		  plugins: [
		            "advlist autolink lists link image charmap print preview anchor",
		            "searchreplace visualblocks code fullscreen",
		            "insertdatetime media table contextmenu paste"
		        ],
		        toolbar: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image"
		 });
</script>

<!-- Send Blog Plain Text -->
<script>
function blogPlainText() {
	var plainText = tinymce.get('blog').getContent({format : 'text'});
	document.getElementById('blogtext').value = plainText;
}

//Event listeners for button click
window.onload = function() {
    document.getElementById('createBlog').addEventListener('click', blogPlainText(), true);
}
</script>


<!-- Bible App -->
<script>
$( document ).ready(function() {
	logos.biblia.init();
});
</script>

<!-- TinyMCE - Holds Public Contents -->
<script>
tinymce.init({
	  selector: '#textarea',
	  plugins: "autoresize",
	  menubar:false,
	  statusbar:false,
	  toolbar:false,
	  readonly:true
	 });
</script>

<!-- Sidebar Menu -->
<script type="text/javascript">
$(function(){

	$('#slide-submenu').on('click',function() {			        
        $(this).closest('.list-group').fadeOut('slide',function(){
        	$('.mini-submenu').fadeIn();	
        });
        
      });

	$('.mini-submenu').on('click',function(){		
        $(this).next('.list-group').toggle('slide');
        $('.mini-submenu').hide();
	})
})
</script>

</body>
</html>