package ma.zs.univ.zynerator.security.ws.facade;

import io.swagger.v3.oas.annotations.Operation;
import ma.zs.univ.zynerator.controller.AbstractController;
import ma.zs.univ.zynerator.dto.FileTempDto;
import ma.zs.univ.zynerator.security.bean.ModelPermissionUser;
import ma.zs.univ.zynerator.security.dao.criteria.core.ModelPermissionUserCriteria;
import ma.zs.univ.zynerator.security.service.facade.ModelPermissionUserService;
import ma.zs.univ.zynerator.security.ws.converter.ModelPermissionUserConverter;
import ma.zs.univ.zynerator.security.ws.dto.ModelPermissionUserDto;
import ma.zs.univ.zynerator.util.PaginatedList;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

import java.util.List;

@RestController
@RequestMapping("/api/modelPermissionUser/")
public class ModelPermissionUserRest  extends AbstractController<ModelPermissionUser, ModelPermissionUserDto, ModelPermissionUserCriteria, ModelPermissionUserService, ModelPermissionUserConverter> {


    @GetMapping("user/{username}")
    public List<ModelPermissionUserDto> findByUserUserName(@PathVariable String username){
        return findDtos(service.findByUserUsername(username));
    }

    @Operation(summary = "upload one modelPermissionUser")
    @RequestMapping(value = "upload", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity<FileTempDto> uploadFileAndGetChecksum(@RequestBody MultipartFile file) throws Exception {
        return super.uploadFileAndGetChecksum(file);
    }
    @Operation(summary = "upload multiple modelPermissionUsers")
    @RequestMapping(value = "upload-multiple", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity<List<FileTempDto>> uploadMultipleFileAndGetChecksum(@RequestBody MultipartFile[] files) throws Exception {
        return super.uploadMultipleFileAndGetChecksum(files);
    }

    @Operation(summary = "Finds a list of all modelPermissionUsers")
    @GetMapping("")
    public ResponseEntity<List<ModelPermissionUserDto>> findAll() throws Exception {
        return super.findAll();
    }

	@Operation(summary = "initialisation des ModelPermissionUsers")
    @GetMapping("init-model-permission-user")
    public ResponseEntity<List<ModelPermissionUserDto>> initModelPermissionUser() {
        List<ModelPermissionUser> list = service.initModelPermissionUser();
        List<ModelPermissionUserDto> dtos = converter.toDto(list);
        HttpStatus status = dtos != null && !dtos.isEmpty() ? HttpStatus.OK : HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(dtos, status);
    }
	
    @Operation(summary = "initialisation des ModelPermissionUsers")
    @GetMapping("init-security-model-permission-user")
    public ResponseEntity<List<ModelPermissionUserDto>> initSecurityModelPermissionUser() {
        List<ModelPermissionUser> list = service.initSecurityModelPermissionUser();
        List<ModelPermissionUserDto> dtos = converter.toDto(list);
        HttpStatus status = dtos != null && !dtos.isEmpty() ? HttpStatus.OK : HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(dtos, status);
    }



    @Operation(summary = "Finds a modelPermissionUser by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ModelPermissionUserDto> findById(@PathVariable Long id) {
        return super.findById(id);
    }


    @Operation(summary = "Saves the specified  modelPermissionUser")
    @PostMapping("")
    public ResponseEntity<ModelPermissionUserDto> save(@RequestBody ModelPermissionUserDto dto) throws Exception {
        return super.save(dto);
    }

    @Operation(summary = "Updates the specified  modelPermissionUser")
    @PutMapping("")
    public ResponseEntity<ModelPermissionUserDto> update(@RequestBody ModelPermissionUserDto dto) throws Exception {
        return super.update(dto);
    }

    @Operation(summary = "Delete list of modelPermissionUser")
    @PostMapping("multiple")
    public ResponseEntity<List<ModelPermissionUserDto>> delete(@RequestBody List<ModelPermissionUserDto> listToDelete) throws Exception {
        return super.delete(listToDelete);
    }
    @Operation(summary = "Delete the specified modelPermissionUser")
    @DeleteMapping("")
    public ResponseEntity<ModelPermissionUserDto> delete(@RequestBody ModelPermissionUserDto dto) throws Exception {
            return super.delete(dto);
    }

    @Operation(summary = "Delete the specified modelPermissionUser")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) throws Exception {
        return super.deleteById(id);
    }
    @Operation(summary = "Delete multiple modelPermissionUsers by ids")
    @DeleteMapping("multiple/id")
    public ResponseEntity<List<Long>> deleteByIdIn(@RequestBody List<Long> ids) throws Exception {
            return super.deleteByIdIn(ids);
     }


    @Operation(summary = "find by actionPermission id")
    @GetMapping("actionPermission/id/{id}")
    public List<ModelPermissionUserDto> findByActionPermissionId(@PathVariable Long id){
        return findDtos(service.findByActionPermissionId(id));
    }
    @Operation(summary = "delete by actionPermission id")
    @DeleteMapping("actionPermission/id/{id}")
    public int deleteByActionPermissionId(@PathVariable Long id){
        return service.deleteByActionPermissionId(id);
    }
    @Operation(summary = "find by modelPermission id")
    @GetMapping("modelPermission/id/{id}")
    public List<ModelPermissionUserDto> findByModelPermissionId(@PathVariable Long id){
        return findDtos(service.findByModelPermissionId(id));
    }
    @Operation(summary = "delete by modelPermission id")
    @DeleteMapping("modelPermission/id/{id}")
    public int deleteByModelPermissionId(@PathVariable Long id){
        return service.deleteByModelPermissionId(id);
    }
    @Operation(summary = "find by user id")
    @GetMapping("user/id/{id}")
    public List<ModelPermissionUserDto> findByUserId(@PathVariable Long id){
        return findDtos(service.findByUserId(id));
    }
    @Operation(summary = "find by user and model reference and action reference")
    @GetMapping("user/{username}/model/{modelReference}/action/{actionReference}")
    public Boolean findByUserUsernameAndModelPermissionReferenceAndActionPermissionReference(@PathVariable String username ,@PathVariable String modelReference,@PathVariable String actionReference){
        return service.findByUserUsernameAndModelPermissionReferenceAndActionPermissionReference(username ,modelReference,actionReference);
    }
    @Operation(summary = "delete by user id")
    @DeleteMapping("user/id/{id}")
    public int deleteByUserId(@PathVariable Long id){
        return service.deleteByUserId(id);
    }
    @Operation(summary = "Finds modelPermissionUsers by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ModelPermissionUserDto>> findByCriteria(@RequestBody ModelPermissionUserCriteria criteria) throws Exception {
        return super.findByCriteria(criteria);
    }

    @Operation(summary = "Finds paginated modelPermissionUsers by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ModelPermissionUserCriteria criteria) throws Exception {
        return super.findPaginatedByCriteria(criteria);
    }

    @Operation(summary = "Exports modelPermissionUsers by criteria")
    @PostMapping("export")
    public ResponseEntity<InputStreamResource> export(@RequestBody ModelPermissionUserCriteria criteria) throws Exception {
        return super.export(criteria);
    }

    @Operation(summary = "Gets modelPermissionUser data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ModelPermissionUserCriteria criteria) throws Exception {
        return super.getDataSize(criteria);
    }



    public ModelPermissionUserRest (ModelPermissionUserService service, ModelPermissionUserConverter converter) {
        super(service, converter);
    }




}
