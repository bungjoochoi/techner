<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
        <title>Spring Boot File Upload / Download Rest API Example</title>
        <link rel="stylesheet" href="/css/main.css" />
        
        <link href="//code.jquery.com/ui/1.9.2/themes/smoothness/jquery-ui.css" rel="stylesheet" type="text/css" />
        <link href="/js/plupload-2.3.6/js/jquery.ui.plupload/css/jquery.ui.plupload.css" rel="stylesheet" type="text/css" />
	    <script src="/js/jquery-3.3.1.min.js"></script>
	    <script src="//code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
	    <script type="text/javascript" src="/js/plupload-2.3.6/js/plupload.full.min.js"></script>
	    <script type="text/javascript" src="/js/plupload-2.3.6/js/jquery.ui.plupload/jquery.ui.plupload.min.js" charset="UTF-8"></script>
    </head>
    <body>

        <script src="/js/main.js"></script>

        <div id="uploader">
            <p>Your browser doesn't have Flash, Silverlight or HTML5 support.</p>
        </div>
        <%--        <noscript>
                    <h2>Sorry! Your browser doesn't support Javascript</h2>
                </noscript>
                <div class="upload-container">
                    <div class="upload-header">
                        <h2>Spring Boot File Upload / Download Rest API Example</h2>
                    </div>
                    <div class="upload-content">
                        <div class="single-upload">
                            <h3>Upload Single File</h3>
                            <form id="singleUploadForm" name="singleUploadForm">
                                <input id="singleFileUploadInput" type="file" name="file" class="file-input" required />
                                <button type="submit" class="primary submit-btn">Submit</button>
                            </form>
                            <div class="upload-response">
                                <div id="singleFileUploadError"></div>
                                <div id="singleFileUploadSuccess"></div>
                            </div>
                        </div>
                        <div class="multiple-upload">
                            <h3>Upload Multiple Files</h3>
                            <form id="multipleUploadForm" name="multipleUploadForm">
                                <input id="multipleFileUploadInput" type="file" name="files" class="file-input" multiple required />
                                <button type="submit" class="primary submit-btn">Submit</button>
                            </form>
                            <div class="upload-response">
                                <div id="multipleFileUploadError"></div>
                                <div id="multipleFileUploadSuccess"></div>
                            </div>
                        </div>
                    </div>
                </div>--%>

        <%--<form id="form" method="post" action="/upload">
            <div id="uploader"></div>
            <br />
        </form>---%>

    </body>
</html>