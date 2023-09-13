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

        amazonS3.putObject(bucket, getFullFilename(path, savedFilename), multipartFile.getInputStream(), metadata);
        return savedFilename;
    }

    public String getS3Url(File file) {
        return file != null ? amazonS3.getUrl(bucket, getFullFilename(file.getSavedPath(), file.getSavedFilename())).toString() : null;
    }

    public void deleteImage(File file) {
        if (file != null) {
            amazonS3.deleteObject(bucket, getFullFilename(file.getSavedPath(), file.getSavedFilename()));
        }
    }

    private String getFullFilename(String path, String savedFilename) {
        StringBuilder sb = new StringBuilder();
        return sb.append(directory)
                .append("/")
                .append(path)
                .append(savedFilename).toString();
    }
}