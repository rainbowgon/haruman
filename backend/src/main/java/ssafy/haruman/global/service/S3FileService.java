package ssafy.haruman.global.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ssafy.haruman.global.entity.File;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3FileService {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.s3.directory}")
    private String directory;

    public String saveFile(String path, MultipartFile multipartFile) throws IOException {
        String savedFilename = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(multipartFile.getSize());
        metadata.setContentType(multipartFile.getContentType());

        amazonS3.putObject(bucket, directory + "/" + path + savedFilename, multipartFile.getInputStream(), metadata);
        return savedFilename;
    }

    public String getS3Url(File file) {
        return file != null ? amazonS3.getUrl(bucket, file.getSavedPath() + file.getSavedFilename()).toString() : null;
    }

    public void deleteImage(String filename) {
        amazonS3.deleteObject(bucket, filename);
    }
}