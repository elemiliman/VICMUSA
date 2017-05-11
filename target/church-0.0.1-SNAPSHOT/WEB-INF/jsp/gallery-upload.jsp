<%@include file="includes/header.jsp" %>

	<div class="container">
		<div class="row">
			<%@include file="includes/sidebar.jsp" %>
			
			<div class="col-sm-9">
				<div class="panel panel-primary">

					<div class="panel-heading">
						<p class="panel-title">Gallery Upload</h3>
					</div>
			
					<div class="panel-body">
					
						<form:form modelAttribute="galleryUploadForm" enctype="multipart/form-data" class="form-horizontal" role="form">
						
							<div class="form-group">
								<form:errors class="col-lg-offset-2 col-lg-7" />
							</div>
							
							<div class="form-group">
								<form:label path="imageDescription" class="col-lg-2 control-label">Description</form:label>
								<div class="col-lg-6">
									<form:input path="imageDescription" class="form-control" placeholder="description" />
									<form:errors cssClass="error" path="imageDescription" />
								</div>
							</div>
							
							<div class="form-group">
								<form:label path="images" class="col-lg-2 control-label">Image upload</form:label>
								<div class="col-lg-6">
									<form:input path="" class="form-control"  type="file" id="files" name="images" multiple="multiple"/>
									<output id="selectedFiles"></output>
									<form:errors cssClass="error" path="images" />
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-lg-offset-2 col-lg-10">
									<button type="submit" class="btn btn-primary" id="uploadButton">Upload</button> 
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
				
			</div>
		</div>
	</div>

<!-- Script For Multiple File Upload Progress. Author: Lokesh Gupta FaceBook: https://www.facebook.com/howtodoinjava -->
<script>
    var totalFileLength, totalUploaded, fileCount, filesUploaded;
 
    //To log everything on console
    function debug(s) {
        var debug = document.getElementById('debug');
        if (debug) {
            debug.innerHTML = debug.innerHTML + '<br/>' + s;
        }
    }
 
    //Will be called when upload is completed
    function onUploadComplete(e) {
        totalUploaded += document.getElementById('files').files[filesUploaded].size;
        filesUploaded++;
        debug('complete ' + filesUploaded + " of " + fileCount);
        debug('totalUploaded: ' + totalUploaded);
        if (filesUploaded < fileCount) {
            uploadNext();
        } else {
            var bar = document.getElementById('bar');
            bar.style.width = '100%';
            bar.innerHTML = '100% complete';
//          alert('Finished uploading file(s)');
        }
    }
 
    //Will be called when user select the files in file control
    function onFileSelect(e) {
        var files = e.target.files; // FileList object
        var output = [];
        fileCount = files.length;
        totalFileLength = 0;
        for (var i = 0; i < fileCount; i++) {
            var file = files[i];
            output.push(file.name, ' (', file.size, ' bytes, ', file.lastModifiedDate.toLocaleDateString(), ')');
            output.push('<br/>');
            debug('add ' + file.size);
            totalFileLength += file.size;
        }
        document.getElementById('selectedFiles').innerHTML = output.join('');
        debug('totalFileLength:' + totalFileLength);
    }
 
    //This will continueously update the progress bar
    function onUploadProgress(e) {
        if (e.lengthComputable) {
            var percentComplete = parseInt((e.loaded + totalUploaded) * 100 / totalFileLength);
            var bar = document.getElementById('bar');
            bar.style.width = percentComplete + '%';
            bar.innerHTML = percentComplete + ' % complete';
        } else {
            debug('unable to compute');
        }
    }
 
    //the Ouchhh !! moments will be captured here
    function onUploadFailed(e) {
        alert("Error uploading file");
    }
 
    //Pick the next file in queue and upload it to remote server
    function uploadNext() {
        var xhr = new XMLHttpRequest();
        var fd = new FormData();
        var file = document.getElementById('files').files[filesUploaded];
        fd.append("multipartFile", file);
        xhr.upload.addEventListener("progress", onUploadProgress, false);
        xhr.addEventListener("load", onUploadComplete, false);
        xhr.addEventListener("error", onUploadFailed, false);
        xhr.open("POST", "/upload-gallery");
        debug('uploading ' + file.name);
        xhr.send(fd);
    }
 
    //Let's begin the upload process
    function startUpload() {
        totalUploaded = filesUploaded = 0;
        uploadNext();
    }
 
    //Event listeners for button clicks
    window.onload = function() {
        document.getElementById('files').addEventListener('change', onFileSelect, false);
        document.getElementById('uploadButton').addEventListener('click', startUpload, false);
    }
</script>

<%@include file="includes/sidebar-footer.jsp" %>