
var singleUploadForm = document.querySelector('#singleUploadForm');
var singleFileUploadInput = document.querySelector('#singleFileUploadInput');
var singleFileUploadError = document.querySelector('#singleFileUploadError');
var singleFileUploadSuccess = document.querySelector('#singleFileUploadSuccess');

var multipleUploadForm = document.querySelector('#multipleUploadForm');
var multipleFileUploadInput = document.querySelector('#multipleFileUploadInput');
var multipleFileUploadError = document.querySelector('#multipleFileUploadError');
var multipleFileUploadSuccess = document.querySelector('#multipleFileUploadSuccess');

/*$(document).ready(function(){
    $("#uploader").plupload({
        // General settings
        runtimes : 'html5,flash,silverlight,html4',
        url : "/uploadFile",
        // Maximum file size
        max_file_size : '2000mb',
        chunk_size: '1mb',
        // Resize images on clientside if we can
        resize : {
            width : 200,
            height : 200,
            quality : 90,
            crop: true // crop to exact dimensions
        },
        // Specify what files to browse for
        filters : [
            {title : "Image files", extensions : "jpg,gif,png"},
            {title : "Zip files", extensions : "zip,avi"}
        ],
        // Rename files by clicking on their titles
        rename: true,
        // Sort files
        sortable: true,
        // Enable ability to drag'n'drop files onto the widget (currently only HTML5 supports that)
        dragdrop: true,
        //Views to activate
        views: {
            list: true,
            thumbs: true, // Show thumbs
            active: 'thumbs'
        },
        // Flash settings
        flash_swf_url : '/js/plupload-2.3.6/js/Moxie.swf',
        // Silverlight settings
        silverlight_xap_url : '/js/plupload-2.3.6/js/Moxie.xap'
    });
});*/



$(document).ready(function(){


    $("#uploader").plupload({
        // General settings
        runtimes : 'html5,html4',
        url : '/uploadFile',
        init : {
            BeforeUpload: function(up, file) {
                console.log('[BeforeUpload]', 'File: ', file);
            }
        },
        // User can upload no more then 20 files in one go (sets multiple_queues to false)
        max_file_count : 2,

        chunk_size : '1000mb',

        // Resize images on clientside if we can
        resize : {
            width : 200,
            height : 200,
            quality : 90,
            crop : true
            // crop to exact dimensions
        },

        filters : {
            // Maximum file size
            max_file_size : '1000mb',
            // Specify what files to browse for
            /*            mime_types : [ {
                            title : "Image files",
                            extensions : "jpg,gif,png"
                        }, {
                            title : "Zip files",
                            extensions : "zip,war"
                        } ]*/
        },

        // Rename files by clicking on their titles
        rename : false,

        // Sort files
        sortable : true,

        // Enable ability to drag'n'drop files onto the widget (currently only HTML5 supports that)
        dragdrop : true,

        // Views to activate
        views : {
            list : true,
            thumbs : true, // Show thumbs
            active : 'thumbs'
        }
    });

    // Handle the case when form was submitted before uploading has finished
    // Files in queue upload them first
    if ($('#uploader').plupload('getFiles').length > 0) {

        // When all files are uploaded submit form
        $('#uploader').on('complete', function() {
            $('#form')[0].submit();
        });

        $('#uploader').plupload('start');
    } else {
        //alert("You must have at least one file in the queue.");
    }
});


/*

function uploadSingleFile(file) {
    var formData = new FormData();
    formData.append("file", file);

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/uploadFile");

    xhr.onload = function() {
    	var jsonData = xhr.responseText
        console.log(jsonData);
        var response = JSON.parse(xhr.responseText);
        if(xhr.status == 200) {
            singleFileUploadError.style.display = "none";
            singleFileUploadSuccess.innerHTML = "<p>File Uploaded Successfully.</p><p>DownloadUrl : <a href='" + response.fileDownloadUri + "' target='_blank'>" + response.fileDownloadUri + "</a></p>";
            singleFileUploadSuccess.style.display = "block";
        } else {
            singleFileUploadSuccess.style.display = "none";
            singleFileUploadError.innerHTML = (response && response.message) || "Some Error Occurred";
        }
    }

    xhr.send(formData);
}

function uploadMultipleFiles(files) {
    var formData = new FormData();
    for(var index = 0; index < files.length; index++) {
        formData.append("files", files[index]);
    }

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/uploadMultipleFiles");

    xhr.onload = function() {
        console.log(xhr.responseText);
        var response = JSON.parse(xhr.responseText);
        if(xhr.status == 200) {
            multipleFileUploadError.style.display = "none";
            var content = "<p>All Files Uploaded Successfully</p>";
            for(var i = 0; i < response.length; i++) {
                content += "<p>DownloadUrl : <a href='" + response[i].fileDownloadUri + "' target='_blank'>" + response[i].fileDownloadUri + "</a></p>";
            }
            multipleFileUploadSuccess.innerHTML = content;
            multipleFileUploadSuccess.style.display = "block";
        } else {
            multipleFileUploadSuccess.style.display = "none";
            multipleFileUploadError.innerHTML = (response && response.message) || "Some Error Occurred";
        }
    }

    xhr.send(formData);
}

singleUploadForm.addEventListener('submit', function(event){
    var files = singleFileUploadInput.files;
    if(files.length === 0) {
        singleFileUploadError.innerHTML = "Please select a file";
        singleFileUploadError.style.display = "block";
    }
    uploadSingleFile(files[0]);
    event.preventDefault();
}, true);


multipleUploadForm.addEventListener('submit', function(event){
    var files = multipleFileUploadInput.files;
    if(files.length === 0) {
        multipleFileUploadError.innerHTML = "Please select at least one file";
        multipleFileUploadError.style.display = "block";
    }
    uploadMultipleFiles(files);
    event.preventDefault();
}, true);*/
