package kr.co.techner.serveSocket.common.file.repository;



import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.techner.serveSocket.common.file.payload.UploadFileResponse;


@Mapper
@Repository
public interface FileMapper {

    Long upload(UploadFileResponse uploadFileResponse);
    
    Long csrUpload(UploadFileResponse uploadFileResponse);
    
    Long replyUpload(UploadFileResponse uploadFileResponse);
        
    Long delFile(Long id);
    
    Long delCsrFile(Long id);
    
    Long delReplyFile(Long id);
    
    UploadFileResponse storedFile(Long id);
    
    UploadFileResponse storedCsrFile(Long id);

    UploadFileResponse storedReplyFile(Long id);

	Long delCsrAppFileData(Long csrId);

	List<UploadFileResponse> selCsrAppFileData(Long csrId);

}
