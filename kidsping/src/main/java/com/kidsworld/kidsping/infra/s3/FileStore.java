package com.kidsworld.kidsping.infra.s3;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.kidsworld.kidsping.global.common.entity.UploadedFile;
import com.kidsworld.kidsping.global.exception.ExceptionCode;
import com.kidsworld.kidsping.global.exception.custom.FileException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class FileStore {

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    private static final int FILE_COUNT = 10;
    private static final String BASE_DIR = "image/";

    private final AmazonS3Client amazonS3Client;

    private UploadedFile convertFile(MultipartFile multipartFile) {

        String uploadedFilename = multipartFile.getOriginalFilename();
        String serverFileName = createStoreFileName(uploadedFilename);
        return new UploadedFile(serverFileName, uploadedFilename);
    }

    // 파일을 S3에 저장
    private String storeFile(MultipartFile multipartFile, String storeFileName) {

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());

        try (InputStream inputStream = multipartFile.getInputStream()) {
            amazonS3Client.putObject(bucketName, storeFileName, inputStream, objectMetadata);
        } catch (IOException e) {
            throw new FileException(ExceptionCode.FILE_UPLOAD_FAILED);
        }

        return amazonS3Client.getUrl(bucketName, storeFileName).toString();
    }

    public List<UploadedFile> storeFiles(List<MultipartFile> multipartFiles) {

        validateFileUploadCount(multipartFiles);

        List<UploadedFile> uploadFiles = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            UploadedFile uploadFile = convertFile(multipartFile);
            String storeFileName = uploadFile.getServerFileName();
            String fileUrl = storeFile(multipartFile, storeFileName);
            uploadFile.setFileUrl(fileUrl);
            uploadFiles.add(uploadFile);
        }

        return uploadFiles;
    }

    private String createStoreFileName(String originalFilename) {
        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return BASE_DIR + uuid + "." + ext;
    }

    // 확장자 추출 메소드
    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }

    private void validateFileUploadCount(List<MultipartFile> multipartFiles) {
        if (multipartFiles.size() > FILE_COUNT) {
            throw new FileException(ExceptionCode.FILE_COUNT_EXCEEDED);
        }
    }
}