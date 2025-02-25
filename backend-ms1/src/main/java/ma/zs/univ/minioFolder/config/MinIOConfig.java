package ma.zs.univ.minioFolder.config;


import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.SetBucketVersioningArgs;
import io.minio.messages.VersioningConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MinIOConfig {

    @Value("${minio.endpoint}")
    private String endpoint;

    @Value("${minio.accessKey}")
    private String accessKey;

    @Value("${minio.secretKey}")
    private String secretKey;

    @Value("${minio.default.bucket}")
    private String bucketName;

    private void createBucketIfNotExists(MinioClient minioClient) {
        try {
            boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName.toLowerCase()).build());
            if(StringUtils.isNotEmpty(bucketName) && ! bucketExists ){
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName.toLowerCase()).build());
                VersioningConfiguration config = new VersioningConfiguration(VersioningConfiguration.Status.ENABLED, false);
                minioClient.setBucketVersioning(
                        SetBucketVersioningArgs.builder().bucket(bucketName.toLowerCase()).config(config).build());
            }
        }catch (Exception e){
            e.getStackTrace();
        }

    }

    @Bean
    public MinioClient minioClient(){
        MinioClient minioClient = MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();
        createBucketIfNotExists(minioClient);
        return minioClient;
    }


}
