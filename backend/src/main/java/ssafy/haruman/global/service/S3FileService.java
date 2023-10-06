package ssafy.haruman.global.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ssafy.haruman.global.entity.File;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3FileService {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${spring.cloud.aws.s3.directory}")
    private String directory;

    public String saveFile(String path, MultipartFile multipartFile) throws IOException {
        String savedFilename = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();

        uploadFile(path, multipartFile.getSize(), multipartFile.getContentType(), savedFilename, multipartFile.getInputStream());
        return savedFilename;
    }

    public String saveFile(String path, String imageUrl, String originalFilename) throws IOException {
        String extension = imageUrl.substring(imageUrl.lastIndexOf(".") + 1);
        ByteArrayOutputStream byteArrayOutputStream = extractByteArrayOutputStreamFromUrl(imageUrl, extension);

        long size = byteArrayOutputStream.size();
        InputStream is = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        byteArrayOutputStream.flush();

        String savedFilename = UUID.randomUUID() + "-" + originalFilename + "." + extension;

        uploadFile(path, size, extension, savedFilename, is);
        return savedFilename;
    }

    private ByteArrayOutputStream extractByteArrayOutputStreamFromUrl(String imageUrl, String extension) throws IOException {
        URL imgURL = new URL(imageUrl);
        BufferedImage image = ImageIO.read(imgURL);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, extension, baos);
        return baos;
    }

    private void uploadFile(String path, long size, String extension, String savedFilename, InputStream inputStream) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(size);
        metadata.setContentType(extension);
        amazonS3.putObject(bucket, getFullFilename(path, savedFilename), inputStream, metadata);
    }

    public String getS3Url(File file) {
        return file != null ? amazonS3.getUrl(bucket, getFullFilename(file.getSavedPath(), file.getSavedFilename())).toString() : null;
    }

    public String getS3Url(String savedPath, String savedFilename) {
        return amazonS3.getUrl(bucket, getFullFilename(savedPath, savedFilename)).toString();
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