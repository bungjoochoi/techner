package kr.co.biztechpartners.serveSocket.common.file.service;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import kr.co.biztechpartners.serveSocket.common.file.exception.BizFileNotFoundException;
import kr.co.biztechpartners.serveSocket.common.file.exception.FileStorageException;
import kr.co.biztechpartners.serveSocket.common.file.payload.UploadFileResponse;
import kr.co.biztechpartners.serveSocket.common.file.property.FileStorageProperties;
import kr.co.biztechpartners.serveSocket.common.file.repository.FileMapper;

@Service
public class FileStorageService {
	
	private final Path fileStorageLocation;
	
	@Autowired
	FileMapper FileMapper;

	@Autowired
	public FileStorageService(FileStorageProperties fileStorageProperties) {
		
		this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
				.toAbsolutePath().normalize();
		
		
		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception e) {
			throw new FileStorageException("디렉토리를 생성할수 없습니다.",e);
		}
	}
	
	public String storeFile(MultipartFile file) {
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		if(fileName.lastIndexOf("/") > 0) {
			fileName = fileName.substring(fileName.lastIndexOf("/")+1);			
		}
		
		if(fileName.contains("..")) {
			throw new FileStorageException("파일이름이 잘못되었습니다."+ fileName );
		}

		return fileName;
			
	}
	
	public String storedCsrFiles(MultipartFile file) {
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		if(fileName.lastIndexOf("/") > 0) {
			fileName = fileName.substring(fileName.lastIndexOf("/")+1);			
		}
		
		if(fileName.contains("..")) {
			throw new FileStorageException("파일이름이 잘못되었습니다."+ fileName );
		}

		return fileName;
			
	}
	
	public String storedReplyFiles(MultipartFile file) {
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		if(fileName.lastIndexOf("/") > 0) {
			fileName = fileName.substring(fileName.lastIndexOf("/")+1);			
		}
		
		if(fileName.contains("..")) {
			throw new FileStorageException("파일이름이 잘못되었습니다."+ fileName );
		}

		return fileName;
			
	}

	
	public Resource loadFileAsResource(String fileDownloadUri) {
		
		String fileName = fileDownloadUri.substring(fileDownloadUri.lastIndexOf("/")+1);
		
		try {
			Path filePath = Paths.get(fileDownloadUri);
			Resource resource = new UrlResource(filePath.toUri());
			
			if(resource.exists()) {
				return resource;
			}else  {
				throw new BizFileNotFoundException("파일이 존재하지 않습니다."+ fileName);
			}
			
		} catch (MalformedURLException e) {
			throw new BizFileNotFoundException("파일이 존재하지 않습니다."+ fileName,e);
		}
		
	}
	
	public Resource loadImgFileAsResource(String fileDownloadUri) {
		
		String fileName = fileDownloadUri.substring(fileDownloadUri.lastIndexOf("/")+1);
		
		try {
			Path filePath = Paths.get(this.fileStorageLocation+fileDownloadUri);
			Resource resource = new UrlResource(filePath.toUri());
			
			if(resource.exists()) {
				return resource;
			}else  {
				throw new BizFileNotFoundException("파일이 존재하지 않습니다."+ fileName);
			}
			
		} catch (MalformedURLException e) {
			throw new BizFileNotFoundException("파일이 존재하지 않습니다."+ fileName,e);
		}
		
	}
	
	public String downloadFileUri(MultipartFile file, String gubun) {
			
	    SimpleDateFormat date = new SimpleDateFormat("yyyyMM");
	    Date time = new Date();
	    String today = date.format(time);
	    
	    String dir = this.fileStorageLocation + "/" + gubun + "/" + today + "/";
	    
	    if(!file.isEmpty()) {
    		File saveDir = new File(dir);
    		if(!saveDir.exists()) {
    			saveDir.mkdirs();
    		}
    	}	    
		return dir;
	}
	
	public String convertToUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
    public void deleteFile(String uri) {
    	boolean check = false;    	
    	File file = new File(uri);
    	if(file.exists()) {
    		check = file.delete();
    	}
    }
    
    public Long upload(UploadFileResponse storedFile) {
    	return FileMapper.upload(storedFile);
    }
    
    public Long csrUpload(UploadFileResponse storedFile) {
    	return FileMapper.csrUpload(storedFile);
    }
    
    public Long replyUpload(UploadFileResponse storedFile) {
    	return FileMapper.replyUpload(storedFile);
    }
    
    public Long delFile(Long id) {
    	return FileMapper.delFile(id);
    }
    
    public Long delCsrFile(Long id) {
    	return FileMapper.delCsrFile(id);
    }

    public Long delReplyFile(Long id) {
    	return FileMapper.delReplyFile(id);
    }
    
    public UploadFileResponse storedFile(Long id,String gubun){
    	if(gubun.equals("notice")) {
    		return FileMapper.storedFile(id);
    	}else if(gubun.equals("csr")) {
    		return FileMapper.storedCsrFile(id);
    	}else {
    		return FileMapper.storedReplyFile(id);
    	}
    	
    }
    
    public List<UploadFileResponse> selCsrAppFileData(Long csrId) {
    	return FileMapper.selCsrAppFileData(csrId);
    }
    
    public Long delCsrAppFileData(Long csrId) {
    	return FileMapper.delCsrAppFileData(csrId);
    }

}
