package ma.zs.univ.minioFolder.ws;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.errors.MinioException;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import ma.zs.univ.bean.core.user.EntiteAdministrative;
import ma.zs.univ.minioFolder.MinIOInfos;
import ma.zs.univ.minioFolder.service.facade.admin.MinIOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/minio")
public class MinioWs {
    @Autowired
    private MinIOService minIOService;
    @Autowired
    MinioClient minioClient;


    @GetMapping("/bucket/{name}")
    public Boolean bucketExists(@PathVariable String name) {
        return minIOService.bucketExists(name);
    }

    @Operation(summary = "Upload a file to the bucket")
    @PostMapping("/upload/file/{bucket}")
    public void uploadToMinio(@RequestParam("file") MultipartFile file, @PathVariable String bucket) {
        minIOService.uploadToMinio(file, bucket);
    }
    @Operation(summary = "Upload multiple files to the bucket")
    @PostMapping("/upload/files/{bucket}")
    public List<MinIOInfos> uploadMultipleToMinio(@RequestParam("files") List<MultipartFile> files, @PathVariable String bucket) {
        return minIOService.uploadMultipleToMinio(files, bucket);
    }
    @GetMapping("/docs/generate-path/{entiteAdministrativeCode}/{username}")
    public String generatePath(@PathVariable String entiteAdministrativeCode,@PathVariable String username){
        return minIOService.generatePath(entiteAdministrativeCode,username);
    }

    @Operation(summary = "Upload a file to the bucket with path")
    @PostMapping("/upload/file-with-path/{bucket}")
    public void uploadToMinio(@RequestParam("file") MultipartFile file, @PathVariable String bucket , @RequestParam("path") String path) {
        minIOService.uploadToMinio(file, bucket, path);
    }
    @Operation(summary = "Upload a file to the bucket with generated path")
    @PostMapping("/upload/file-with-path-generated")
    public int uploadToMinioWithGeneratedPath(@RequestParam("file") MultipartFile file,@RequestParam("entiteAdministrativeCode") String entiteAdministrativeCode,@RequestParam("username")String username) {
        return minIOService.uploadToMinioWithGeneratedPath(file,entiteAdministrativeCode,username);
    }


        @PostMapping("/save-bucket/{bucket}")
    public int saveBucket(@PathVariable String bucket) {
        return minIOService.saveBucket(bucket);
    }

    @PostMapping("/create-folder/{bucketName}")
    public int createFolderInBucket(@RequestParam("path") String folderPath,@PathVariable String bucketName){
       return minIOService.createFolderInBucket(folderPath,bucketName);
    }
    @GetMapping("/folder-exists/{bucketName}")
    public boolean folderExists(@RequestParam("path") String folderPath,@PathVariable String bucketName){
        return minIOService.folderEexists(folderPath,bucketName);
    }

    @GetMapping("/download/{bucket}/{objectName}")
    public void downloadFileFromMinio(@PathVariable String bucket,
                                      @PathVariable String objectName,
                                      HttpServletResponse response) {
        try {
            // Créer le dossier "minioDownloads" dans le répertoire de téléchargement par défaut de l'utilisateur
            String downloadPath = System.getProperty("user.home") + File.separator + "Downloads" + File.separator + "minioDownloads";
            File directory = new File(downloadPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Télécharger l'objet depuis MinIO
            InputStream stream = minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(bucket)
                            .object(objectName)
                            .build()
            );

            // Créer un fichier dans le dossier de téléchargement avec le nom de l'objet
            String filePath = downloadPath + File.separator + objectName;
            File file = new File(filePath);
            FileOutputStream fos = new FileOutputStream(file);

            // Copier le contenu de l'objet dans le fichier
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = stream.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
            fos.close();
            stream.close();

            System.out.println("L'objet a été téléchargé avec succès vers : " + filePath);
        } catch (Exception e) {
            // Gérer les exceptions ici
            System.err.println("Une erreur s'est produite lors du téléchargement de l'objet : " + e.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/bucket/findAllDocs/{bucket}")
    public List<String> findAllDocuments(@PathVariable String bucket) throws MinioException {
        return minIOService.getAllDocumentsNames(bucket);
    }

    @GetMapping("/docs/load document/{bucket}/{documentName}")
    public byte[] LoadDocument(@PathVariable String bucket,@PathVariable String documentName) throws MinioException {
        return minIOService.LoadDocument(bucket, documentName);
    }

    @PostMapping("/document/set-tags/{bucketName}/{objectName}")
    public void setObjectTags(@PathVariable String bucketName,@PathVariable String objectName,@RequestParam() Map<String, String> tags) throws MinioException{
         minIOService.setObjectTags(bucketName,objectName,tags);
    }

    @GetMapping("/download-v2/{bucket}/{objectName}")
    public void downloadFile(@PathVariable String bucket,
                                      @PathVariable String objectName,
                                      HttpServletResponse response) {
        try {
            // Download the object from MinIO
            InputStream stream = minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(bucket)
                            .object(objectName)
                            .build()
            );

            // Determine the content type based on the file extension
            String contentType;
            if (objectName.endsWith(".pdf")) {
                contentType = "application/pdf";
            } else if (objectName.endsWith(".ppt") || objectName.endsWith(".pptx")) {
                contentType = "application/vnd.ms-powerpoint";
            } else if (objectName.endsWith(".doc") || objectName.endsWith(".docx")) {
                contentType = "application/msword";
            } else {
                // Default to binary data if file type is not recognized
                contentType = "application/octet-stream";
            }

            // Set the content type and attachment header
            response.setContentType(contentType);
            response.setHeader("Content-Disposition", "attachment; filename=\"" + objectName + "\"");

            // Copy the file content to the response output stream
            ServletOutputStream out = response.getOutputStream();
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = stream.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            out.flush();
            out.close();
            stream.close();

            System.out.println("The object was downloaded successfully.");
        } catch (Exception e) {
            // Handle exceptions here
            System.err.println("An error occurred while downloading the object: " + e.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/document/delete-document /{bucketName}/{objectName}")
    public void deleteObject(@PathVariable String bucketName,@PathVariable String objectName) throws MinioException{
         minIOService.deleteObject(bucketName,objectName);
    }
    @GetMapping("/doc/getAllVersions")
    public List<String> getAllVersions(@RequestParam("path") String objectPath){
        return minIOService.getAllVersions(objectPath);
    }

    }


