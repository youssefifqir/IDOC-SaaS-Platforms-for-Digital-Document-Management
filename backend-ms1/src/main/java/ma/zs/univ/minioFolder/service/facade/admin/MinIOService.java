package ma.zs.univ.minioFolder.service.facade.admin;

import io.minio.errors.MinioException;
import ma.zs.univ.bean.core.user.EntiteAdministrative;
import ma.zs.univ.minioFolder.MinIOInfos;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface MinIOService {
    public String createFoldersForEntity(EntiteAdministrative entity, String bucketName);

    Boolean bucketExists(String bucket);
    public boolean checkPathExists(String bucket, String path);

    int uploadToMinio(MultipartFile file, String bucket);
    public int uploadToMinio(MultipartFile file, String bucket, String path);
    public int uploadToMinioWithGeneratedPath(MultipartFile file,String EntiteAdministrativeCode, String username) ;
    int saveBucket(String bucket);
    public List<String> getAllDocumentsNames(String bucket) throws MinioException;
    public byte[] LoadDocument(String bucket, String documentName) throws MinioException;
    public int setObjectTags(String bucketName, String objectName, Map<String, String> tags) throws MinioException;
    public int createFolderInBucket(String folderPath, String bucketName);
    public boolean folderEexists(String path, String bucketName);
    public void deleteBucket(String bucketName) throws IOException;
    public void deleteObject(String bucketName, String objectName) throws MinioException;
    public String generatePath(String entiteAdministrativeCode, String username) ;
        public void buildPath(String entiteAdministrative , StringBuilder sb) ;
    public boolean isVersioningEnabled();
    public List<String> getAllVersions(String objectPath);
    public List<MinIOInfos> uploadMultipleToMinio(List<MultipartFile> files, String bucket);

}