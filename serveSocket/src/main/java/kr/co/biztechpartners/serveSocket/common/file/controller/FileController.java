package kr.co.biztechpartners.serveSocket.common.file.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.co.biztechpartners.serveSocket.common.file.payload.UploadFileResponse;
import kr.co.biztechpartners.serveSocket.common.file.service.FileStorageService;
import kr.co.biztechpartners.serveSocket.common.file.vo.FileBean;
import kr.co.biztechpartners.serveSocket.common.file.vo.ImageFile;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class FileController {

	private static final Logger logger = LoggerFactory.getLogger(FileController.class);

	@Autowired
	FileStorageService fileStorageService;

	@PostMapping("/uploadFile")
	@ResponseBody
	public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file, @RequestParam String gubun, @RequestParam int id) {

		String fileOriginalName = fileStorageService.storeFile(file);

		String fileOriginalExtension = fileOriginalName.substring(fileOriginalName.lastIndexOf("."));

		String fileStoredName = fileStorageService.convertToUUID() ;
//		String fileStoredName = fileStorageService.convertToUUID() + fileOriginalExtension;

		String fileDownloadUri = fileStorageService.downloadFileUri(file, gubun) + fileStoredName;

		
		String nontype[] = {".exe", ".bin", ".asp", ".jsp", ".php", ".bat", ".cmd", ".com", ".sc", ".scf", ".srp", ".cpl", ".dat", ".ocx"
				 			,".jspx", ".aspx", ".phps", ".htm", ".html", ".sh", ".class", ".java", ".war", ".cgi" };	// khj
		String filename = fileOriginalName.toLowerCase();

		boolean isok = true;
		for(int i = 0; i < nontype.length; i++) {
			if(filename.indexOf(nontype[i]) != -1) {
				isok =  false;
				break;
			}
		}
		
		if(!isok) {
			new RuntimeException("확장자 제한 첨부파일은 업로드 불가합니다.");
		}
		
		File upFile = new File(fileDownloadUri);

		try {
			file.transferTo(upFile);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		UploadFileResponse storedFile = new UploadFileResponse(fileOriginalName, fileDownloadUri, file.getContentType(),
				file.getSize());

		storedFile.setDateCreated(new Date());
		
		if(gubun.equals("notice")) {
			storedFile.setNoticeId(id);
			fileStorageService.upload(storedFile);
		}else if(gubun.equals("csr")) {
			storedFile.setCsrId(id);
			fileStorageService.csrUpload(storedFile);
		}else if(gubun.equals("reply")) {
			storedFile.setReplyId(id);
			fileStorageService.replyUpload(storedFile);
		}

		return storedFile;
	}

	@PostMapping("/uploadMultipleFiles")
	@ResponseBody
	public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files,
			@RequestParam String gubun, @RequestParam int id) {
		return List.of(files).stream().map(file -> uploadFile(file, gubun, id)).collect(Collectors.toList());
	}

	@GetMapping("/downloadFile")
	public ResponseEntity<Resource> downloadFile(@RequestParam Long id,@RequestParam String gubun, @RequestParam("name") String name,
			HttpServletRequest req) throws Exception {

		
		UploadFileResponse ufr = fileStorageService.storedFile(id,gubun);
		
		Resource resource = fileStorageService.loadFileAsResource(ufr.getFileDownloadUri());

		String fileName = URLDecoder.decode(name, "UTF-8");

		String contentType = null;

		logger.debug(fileName);

		contentType = req.getServletContext().getMimeType(fileName);

		logger.debug(contentType);

		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		String browser = req.getHeader("User-Agent");
		String encodingName;

		logger.debug(browser);
		if ((browser.contains("Mozilla")) || (browser.contains("Trident")) || (browser.contains("Edge"))) {
			// 인터넷 익스플로러 10이하 버전, 11버전, 엣지에서 인코딩
			encodingName = URLEncoder.encode(fileName, "utf-8").replaceAll("\\+", "%20");
		} else {
			// 나머지 브라우저에서 인코딩
			encodingName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
		}

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodingName + "\"")
				.contentType(MediaType.parseMediaType(contentType)).body(resource);
	}
	
	@GetMapping("/viewImgAttach/img/{yyyymm}/{filename}")
	public ResponseEntity<Resource> viewImgAttach(@PathVariable String yyyymm,@PathVariable String filename,
			HttpServletRequest req) throws Exception {
		
		
		//"/viewImgAttach/img/201906/735df9680596489d9c8bc4556d6bc136.png"
		
		String uri = "/img/"+yyyymm+"/"+filename;
		
		Resource resource = fileStorageService.loadImgFileAsResource(uri);
		
		String fileName = URLDecoder.decode(filename, "UTF-8");
		
		String contentType = null;
		
		logger.debug(fileName);
		
		contentType = req.getServletContext().getMimeType(fileName);
		
		logger.debug(contentType);
		
		if (contentType == null) {
			contentType = "application/octet-stream";
		}
		
		String browser = req.getHeader("User-Agent");
		String encodingName;
		
		logger.debug(browser);
		if ((browser.contains("Mozilla")) || (browser.contains("Trident")) || (browser.contains("Edge"))) {
			// 인터넷 익스플로러 10이하 버전, 11버전, 엣지에서 인코딩
			encodingName = URLEncoder.encode(fileName, "utf-8").replaceAll("\\+", "%20");
		} else {
			// 나머지 브라우저에서 인코딩
			encodingName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
		}
		
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodingName + "\"")
				.contentType(MediaType.parseMediaType(contentType)).body(resource);
	}

	// 이미지 업로드..
	@RequestMapping(value = "/uploadEditorImage", method = RequestMethod.POST)
	@ResponseBody
	public ImageFile procFileUpload(FileBean fileBean, MultipartFile file) {
		
		String fileOriginalName = fileStorageService.storeFile(file);

		String fileOriginalExtension = fileOriginalName.substring(fileOriginalName.lastIndexOf("."));

		String fileStoredName = fileStorageService.convertToUUID() + fileOriginalExtension;
//		String fileStoredName = fileStorageService.convertToUUID();

		String fileDownloadUri = fileStorageService.downloadFileUri(file, "img") + fileStoredName;

		
		
		if(fileOriginalName.toLowerCase().endsWith(".jpg") ||
				fileOriginalName.toLowerCase().endsWith(".jpeg") ||
				fileOriginalName.toLowerCase().endsWith(".png") ||
				fileOriginalName.toLowerCase().endsWith(".gif") ||
	            fileOriginalName.toLowerCase().endsWith(".bmp")) {


			File upFile = new File(fileDownloadUri);
			
			try {
				file.transferTo(upFile);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			UploadFileResponse storedFile = new UploadFileResponse(fileOriginalName, fileDownloadUri, file.getContentType(),
					file.getSize());
			
		}
		
		//{"fileName":"\uc81c\ubaa9 \uc5c6\uc74c.png","uploaded":1,"url":"https:\/\/ckeditor.com\/apps\/ckfinder\/userfiles\/images\/%EC%A0%9C%EB%AA%A9%20%EC%97%86%EC%9D%8C.png"}
		
		SimpleDateFormat date = new SimpleDateFormat("yyyyMM");
		Date time = new Date();
		String today = date.format(time);
		
		String dir = "/" + "img" + "/" + today + "/";
		
		ImageFile view = new ImageFile();
		view.setFileName(fileBean.getFilename());
		view.setUploaded(1);
		view.setUrl("/viewImgAttach"+dir+fileStoredName);

		return view;
	}
	
	
	@RequestMapping(value = {"/upload-drag-drop","/upload-drag-drop&responseType=json"}, method = RequestMethod.POST)
	@ResponseBody
	public ImageFile uploadDragDrop( MultipartHttpServletRequest request){
	    String fileName=request.getFileNames().next();
	    MultipartFile file =  request.getFile(fileName);
	    String fileOriginalName = fileStorageService.storeFile(file);

		String fileOriginalExtension = fileOriginalName.substring(fileOriginalName.lastIndexOf("."));

		String fileStoredName = fileStorageService.convertToUUID() + fileOriginalExtension;
//		String fileStoredName = fileStorageService.convertToUUID() ;

		String fileDownloadUri = fileStorageService.downloadFileUri(file, "img") + fileStoredName;

		
		if(fileOriginalName.toLowerCase().endsWith(".jpg") ||
				fileOriginalName.toLowerCase().endsWith(".jpeg") ||
				fileOriginalName.toLowerCase().endsWith(".png") ||
				fileOriginalName.toLowerCase().endsWith(".gif") ||
	            fileOriginalName.toLowerCase().endsWith(".bmp")) {
			File upFile = new File(fileDownloadUri);

			try {
				file.transferTo(upFile);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			UploadFileResponse storedFile = new UploadFileResponse(fileOriginalName, fileDownloadUri, file.getContentType(),
					file.getSize());
			
		}
		
		
		
		//{"fileName":"\uc81c\ubaa9 \uc5c6\uc74c.png","uploaded":1,"url":"https:\/\/ckeditor.com\/apps\/ckfinder\/userfiles\/images\/%EC%A0%9C%EB%AA%A9%20%EC%97%86%EC%9D%8C.png"}
		
		SimpleDateFormat date = new SimpleDateFormat("yyyyMM");
	    Date time = new Date();
	    String today = date.format(time);
	    
	    String dir = "/" + "img" + "/" + today + "/";
		
		
		ImageFile view = new ImageFile();
		view.setFileName(fileName);
		view.setUploaded(1);
		view.setUrl("/viewImgAttach"+dir+fileStoredName);

		return view;
	}

	@GetMapping("/delFiles")
	public void delFiles(@RequestParam List<Long> id, @RequestParam List<String> path) {

		logger.debug("DELETEFILE notice==================================");

		for (int i = 0; i < path.size(); i++) {
			fileStorageService.deleteFile(path.get(i));
		}

		for (int i = 0; i < id.size(); i++) {
			fileStorageService.delFile(id.get(i));
		}

	}
	
	@GetMapping("/delCsrFiles")
	public void delCsrFiles(@RequestParam List<Long> id, @RequestParam List<String> path) {

		logger.debug("DELETEFILE csr==================================");

		for (int i = 0; i < path.size(); i++) {
			fileStorageService.deleteFile(path.get(i));
		}

		for (int i = 0; i < id.size(); i++) {
			fileStorageService.delCsrFile(id.get(i));
		}

	}
	
	@GetMapping("/delReplyFiles")
	public void delReplyFiles(@RequestParam List<Long> id, @RequestParam List<String> path) {

		logger.debug("DELETEFILE reply==================================");

		for (int i = 0; i < path.size(); i++) {
			fileStorageService.deleteFile(path.get(i));
		}

		for (int i = 0; i < id.size(); i++) {
			fileStorageService.delReplyFile(id.get(i));
		}

	}
}
