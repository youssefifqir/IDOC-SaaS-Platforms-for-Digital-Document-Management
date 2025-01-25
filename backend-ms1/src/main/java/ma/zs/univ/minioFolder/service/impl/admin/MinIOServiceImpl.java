package ma.zs.univ.minioFolder.service.impl.admin;

import com.fasterxml.jackson.databind.JsonMappingException;
import io.minio.*;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.MinioException;
import io.minio.messages.Item;
import io.minio.messages.VersioningConfiguration;
import ma.zs.univ.bean.core.user.EntiteAdministrative;
import ma.zs.univ.dao.facade.core.user.EntiteAdministrativeDao;
import ma.zs.univ.minioFolder.MinIOInfos;
import ma.zs.univ.minioFolder.exceptions.BucketNotFoundException;
import ma.zs.univ.minioFolder.service.facade.admin.MinIOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class MinIOServiceImpl implements MinIOService {

    @Autowired
    private MinioClient minioClient;
    private @Autowired EntiteAdministrativeDao dao;

    @Override
    public Boolean bucketExists(String name) {
        try {
            return minioClient.bucketExists(BucketExistsArgs.builder().bucket(name).build());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean objectExists(String bucketName, String objectName) throws MinioException {
        try {
            StatObjectResponse response = minioClient.statObject(
                    StatObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .build()
            );
            return true; // Object exists
        } catch (MinioException e) {
            // Check if the exception is due to object not found
            if (e instanceof ErrorResponseException && ((ErrorResponseException) e).errorResponse().code().equals("NoSuchKey")) {
                return false; // Object not found
            }
            // If it's not NoSuchKey, throw the MinioException
            throw e;
        } catch (Exception e) {
            throw new MinioException("An error occurred while checking if the object " + objectName + " exists: " + e.getMessage());
        }
    }

   /* @Override
    public boolean folderEexists(String path, String bucketName) {
        try {
            // Construct the object name for the folder (ends with '/')
            String objectName = path.endsWith("/") ? path : path + "/";

            // Check if the object representing the folder exists
            StatObjectResponse response = minioClient.statObject(
                    StatObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .build()
            );

            // If the response is successful, return true
            return response != null && response.size() == 0; // A folder should have size 0
        } catch (Exception e) {
            // Handle any exceptions here
            // If the folder doesn't exist, an exception will be thrown
            // Log the exception or handle it as needed
            return false;
        }
    } */
   @Override
   public boolean folderEexists(String path, String bucketName) {
       try {
           // Construct the object name for the folder (ends with '/')
           String objectName = path.endsWith("/") ? path : path + "/";

           // Check if the object representing the folder exists
           StatObjectResponse response = minioClient.statObject(
                   StatObjectArgs.builder()
                           .bucket(bucketName)
                           .object(objectName)
                           .build()
           );

           // If the response is successful, return true
           return response != null && response.size() == 0; // A folder should have size 0
       } catch (Exception e) {
           // Handle any exceptions here
           // If the folder doesn't exist, an exception will be thrown
           // Log the exception or handle it as needed
           return false;
       }
   }

    @Override
    public boolean checkPathExists(String bucket, String path) {
        try {
            StatObjectResponse response = minioClient.statObject(
                    StatObjectArgs.builder()
                            .bucket(bucket)
                            .object(path)
                            .build()
            );
            return true;
        } catch (Exception e) {
            // Handle the exception or log the error if needed
            return false;
        }
    }

    @Override
    public int createFolderInBucket(String folderPath, String bucketName) {
        try {
            // Construct the object name for the folder (ends with '/')
            String objectName = folderPath.endsWith("/") ? folderPath : folderPath + "/";

            // Check if the folder already exists
            if (folderEexists(objectName, bucketName)) { // Use the objectName instead of folderPath
                System.out.println("Folder already exists.");
                return 0; // Folder already exists
            }
            // Create an empty object representing the folder
            InputStream emptyContent = new ByteArrayInputStream(new byte[0]);
            PutObjectArgs args = PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .stream(emptyContent, 0, -1)
                    .build();
            minioClient.putObject(args);

            System.out.println("Folder created successfully.");
            return 0; // Folder created successfully
        } catch (Exception e) {
            // Handle any exceptions here
            // Log the exception or handle it as needed
            System.err.println("Error creating folder: " + e.getMessage());
            return 1; // Error creating folder
        }
    }
    //

    @Override
    public int uploadToMinio(MultipartFile file, String bucket) {
        if (!bucketExists(bucket))
            return 0;
        try {
            minioClient.putObject(
                    PutObjectArgs.builder().bucket(bucket)
                            .object(file.getOriginalFilename())
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    @Override
    public int uploadToMinio(MultipartFile file, String bucket, String path) {
        if (!bucketExists(bucket)) {
            return 0;
        }

        try {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucket)
                            .object(path + "/" + file.getOriginalFilename())
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int uploadToMinioWithGeneratedPath(MultipartFile file, String entiteAdministrativeCode, String username) {
        String path = generatePath(entiteAdministrativeCode, username);
        return uploadToMinio(file, "yarbi", path);
    }

    @Override
    public int saveBucket(String bucket) {
        if (bucketExists(bucket))
            return 0;
        try {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
            VersioningConfiguration config = new VersioningConfiguration(VersioningConfiguration.Status.ENABLED, false);
            minioClient.setBucketVersioning(SetBucketVersioningArgs.builder().bucket(bucket).config(config).build());
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<String> getAllDocumentsNames(String bucket) throws MinioException {
        if (Boolean.FALSE.equals(bucketExists(bucket))) {
            throw new BucketNotFoundException("the bucket " + bucket + " does not exist");
        }
        List<String> documents = new ArrayList<>();
        try {
            Iterable<Result<Item>> results = minioClient.listObjects(ListObjectsArgs.builder().bucket(bucket).recursive(true).build());
            for (Result<Item> result : results) {
                Item item = result.get();
                documents.add(item.objectName());
            }
        } catch (Exception e) {
            throw new MinioException("Error while fetching files form the bucket " + bucket + ", error : " + e.getMessage());
        }
        return documents;
    }

    @Override
    public byte[] LoadDocument(String bucket, String documentName) throws MinioException {
        if (!bucketExists(bucket)) {
            throw new BucketNotFoundException("The bucket " + bucket + " does not exist");
        }

        try {
            // Get the document object from MinIO
            GetObjectResponse response = minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(bucket)
                            .object(documentName)
                            .build()
            );

            // Get the input stream containing the document data
            InputStream documentStream = response;

            // Create a byte array output stream to hold the document data
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            // Buffer for reading data
            byte[] buffer = new byte[8192];
            int bytesRead;

            // Write the document data to the byte array output stream
            while ((bytesRead = documentStream.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }

            // Close the input stream for the document
            documentStream.close();

            // Return the document data as a byte array
            return baos.toByteArray();
        } catch (Exception e) {
            throw new MinioException("Error while downloading the document " + documentName + " from the bucket " + bucket + ", error : " + e.getMessage());
        }
    }

    @Override
    public int setObjectTags(String bucketName, String objectName, Map<String, String> tags) throws MinioException {
        try {
            if (!bucketExists(bucketName)) {
                throw new BucketNotFoundException("The bucket " + bucketName + " does not exist");
            }
            if (!objectExists(bucketName, objectName)) {
                throw new BucketNotFoundException("The object " + bucketName + " does not exist in the bucket " + bucketName);
            }
            minioClient.setObjectTags(
                    SetObjectTagsArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .tags(tags)
                            .build()
            );
            return 0;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteObject(String bucketName, String objectName) throws MinioException {
        try {
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .build()
            );
            System.out.println("Object " + objectName + " successfully deleted from bucket " + bucketName);
        } catch (Exception e) {
            System.out.println("Error deleting object " + objectName + " from bucket " + bucketName + ": " + e.getMessage());
           // throw new MinioException("Error deleting object " + objectName + " from bucket " + bucketName, e);
        }
    }

@Override
    public void deleteBucket(String bucketName) throws IOException {
        try {
          //  minioClient.removeBucket(bucketName);
            System.out.println("Bucket " + bucketName + " successfully deleted");
        } catch (Exception e) {
            System.out.println("Error deleting bucket " + bucketName + ": " + e.getMessage());
            throw new IOException("Error deleting bucket " + bucketName, e);
        }
    }

@Override
    public String generatePath(String entiteAdministrativeCode, String username) {
        StringBuilder sb = new StringBuilder();
        buildPath(entiteAdministrativeCode, sb);
        sb.append("/").append(username); // Append the username at the end
        return sb.toString();
    }

    @Override
    public void buildPath(String entiteAdministrative, StringBuilder sb) {
        EntiteAdministrative entity = dao.findByCode(entiteAdministrative);
        if (entity != null) {
            EntiteAdministrative parentEntity = dao.findByCode(entity.getCodeEntiteAdminParent());
            if (parentEntity != null) {
                // Recursive call
                buildPath(parentEntity.getCode(), sb);
                // Check if the parent folder already exists
                String parentFolderPath = sb.toString();
                if (checkPathExists("yarbi", parentFolderPath)) {
                    return; // Parent folder already exists, no need to continue building the path
                }
                // Append parent code only if it's different from the current entity code AND not already present in the path
                if (!parentEntity.getCode().equals(entity.getCode()) && !sb.toString().contains("/" + parentEntity.getCode())) {
                    sb.append("/").append(parentEntity.getCode());
                }
            }
            sb.append("/").append(entity.getCode()); // Append current entity code
        }
    }
  /*  @Override
    public String generatePath(String entiteAdministrativeCode, String username) {
        StringBuilder sb = new StringBuilder();
        buildPath(entiteAdministrativeCode, sb);
        sb.append("/").append(username);  // Append the username at the end
        return sb.toString();
    }

    @Override
    public void buildPath(String entiteAdministrative , StringBuilder sb) {
        EntiteAdministrative entity = dao.findByCode(entiteAdministrative);
        if (entity != null) {
            EntiteAdministrative parentEntity = dao.findByCode(entity.getCodeEntiteAdminParent());
            if (parentEntity != null) {
                buildPath(parentEntity.getCode(), sb);
                sb.append(parentEntity.getCode()).append("/");
                // Check if the parent folder already exists
                String parentFolderPath = sb.toString();
                if (checkPathExists("yarbi", parentFolderPath)) {
                    return; // Parent folder already exists, no need to continue building the path
                }
            }
            sb.append(entity.getCode());
        }
    } */
  /*  @Override public String createFoldersForEntity(EntiteAdministrative entity, String bucketName) {
      try {
          if (entity != null) {
              StringBuilder sb = new StringBuilder();
              // Get the parent entity
              String c =entity.getCodeEntiteAdminParent();
              EntiteAdministrative parentEntity = dao.findByCode(c);

              if (parentEntity != null) {
                  String result = createFoldersForEntity(parentEntity, bucketName);
                  if (!result.equals("0")) {
                      return result; // Propagate the error code from recursive call
                  }
                  sb.append(parentEntity.getCode()).append("/"); // Append parent code with separator
              }

              sb.append(entity.getCode()); // Append child code

              String path = sb.toString();
              boolean exists = folderEexists(path, bucketName);
              if (!exists) {
                  createFolderInBucket(path, bucketName);
              }
          }
          return "0"; // Return 0 for success
      } catch (Exception e) {
          e.printStackTrace(); // Print the exception trace
          return e.getMessage(); // Return 1 for general error
      }
  }
   */
   /* @Override
    public String createFoldersForEntity(EntiteAdministrative entity, String bucketName) {
        try {
            if (entity != null) {
                StringBuilder sb = new StringBuilder();
                // Get the parent entity
                String c = entity.getCodeEntiteAdminParent();
                EntiteAdministrative parentEntity = dao.findByCode(c);

                if (parentEntity != null) {
                    String result = createFoldersForEntity(parentEntity, bucketName);
                    if (!result.equals("0")) {
                        return result; // Propagate the error code from recursive call
                    }
                    sb.append(parentEntity.getCode()).append("/"); // Append parent code with separator
                }

                sb.append(entity.getCode()); // Append child code

                String path = sb.toString();
                // Vérifiez l'existence du dossier enfant avant de le créer
                boolean exists = folderEexists(path, bucketName);
                if (!exists) {
                    createFolderInBucket(path, bucketName);
                }
            }
            return "0"; // Return 0 for success
        } catch (Exception e) {
            e.printStackTrace(); // Print the exception trace
            return e.getMessage(); // Return 1 for general error
        }
    }  */
    @Override
  public String createFoldersForEntity(EntiteAdministrative entity, String bucketName) {
      try {
          if (entity != null) {
              StringBuilder sb = new StringBuilder();
              // Get the parent entity
              String c = entity.getCodeEntiteAdminParent();
              EntiteAdministrative parentEntity = dao.findByCode(c);
              if (parentEntity != null) {
                  // Recursively create parent folders
                  String result = createFoldersForEntity(parentEntity, bucketName);
                  if (!result.equals("0")) {
                      return result; // Propagate the error code from recursive call
                  }
                  sb.append(parentEntity.getCode()).append("/"); // Append parent code with separator
              }

              sb.append(entity.getCode()); // Append child code

              String path = sb.toString();
              // Create the child folder if it doesn't exist
              boolean exists = folderEexists(path, bucketName);
              if (!exists) {
                  createFolderInBucket(path, bucketName);
              }
          }
          return "0"; // Return 0 for success
      } catch (Exception e) {
          e.printStackTrace(); // Print the exception trace
          return e.getMessage(); // Return 1 for general error
      }
  }

  @Override
    public boolean isVersioningEnabled() {
        try {
            VersioningConfiguration versioningConfig = minioClient.getBucketVersioning(GetBucketVersioningArgs.builder().bucket("yarbi").build());
            return versioningConfig.status().equals("Enabled");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Exception occurred while checking bucket versioning: " + e.getMessage());
        }
    }
    // Method to get all versions of an object in the default bucket
    @Override
    public List<String> getAllVersions(String objectPath) {
        try {
            // Check if bucket versioning is enabled
            if (!
                    isVersioningEnabled()){
                return Collections.singletonList("-1");
            }
            // Get all versions of the object
            GetObjectResponse object = minioClient.getObject(GetObjectArgs.builder().bucket("yarbi").object(objectPath).build());
            List<String> versionIds = Collections.emptyList();
            //object.
            if (versionIds.isEmpty()) {
                return Collections.singletonList("1");
               // throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No versions of the object found.");
            }

            return versionIds;
        } catch (MinioException e) {
            return Collections.singletonList("-3");
            //throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Minio Error: " + e.getMessage());
        }
        catch (Exception e ){
            return Collections.singletonList("-4");
            //throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Exception : "+e.getMessage());
        }
    }

    @Override
    public List<MinIOInfos> uploadMultipleToMinio(List<MultipartFile> files, String bucket) {
        List<MinIOInfos> minIOInfosList = new ArrayList<>();
        if (!bucketExists(bucket))
            return null;
        for (MultipartFile file : files) {
            try {
                String originalFilename = file.getOriginalFilename();
                String contentType = file.getContentType();
                long size = file.getSize();
                InputStream inputStream = file.getInputStream();
                byte[] bytes = file.getBytes();
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
                String objectName = originalFilename;
                minioClient.putObject(
                        PutObjectArgs.builder()
                                .bucket(bucket)
                                .object(objectName)
                                .stream(byteArrayInputStream, bytes.length, -1)
                                .contentType(contentType)
                                .build()
                );
                byteArrayInputStream.close();
                String etag = minioClient.statObject(
                        StatObjectArgs.builder().bucket(bucket).object(objectName).build()
                ).etag();

                int resultStatus = (etag != null) ? 1 : 0;

                MinIOInfos minIOInfos = new MinIOInfos(bucket, originalFilename, size, bytes, etag, resultStatus);
                minIOInfosList.add(minIOInfos);

            } catch (Exception e) {
                e.printStackTrace();
                // You might want to handle this differently, such as logging the error and continuing with the next file
            }
        }
        return minIOInfosList;
    }


}



