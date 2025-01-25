package ma.zs.univ.zynerator.security.ws.facade;

import io.swagger.v3.oas.annotations.Operation;
import ma.zs.univ.zynerator.controller.AbstractController;
import ma.zs.univ.zynerator.dto.FileTempDto;
import ma.zs.univ.zynerator.security.bean.ModelPermission;
import ma.zs.univ.zynerator.security.dao.criteria.core.ModelPermissionCriteria;
import ma.zs.univ.zynerator.security.service.facade.ModelPermissionService;
import ma.zs.univ.zynerator.security.ws.converter.ModelPermissionConverter;
import ma.zs.univ.zynerator.security.ws.dto.ModelPermissionDto;
import ma.zs.univ.zynerator.util.PaginatedList;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/modelPermission/")
public class ModelPermissionRest  extends AbstractController<ModelPermission, ModelPermissionDto, ModelPermissionCriteria, ModelPermissionService, ModelPermissionConverter> {


    @Operation(summary = "upload one modelPermission")
    @RequestMapping(value = "upload", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity<FileTempDto> uploadFileAndGetChecksum(@RequestBody MultipartFile file) throws Exception {
        return super.uploadFileAndGetChecksum(file);
    }
    @Operation(summary = "upload multiple modelPermissions")
    @RequestMapping(value = "upload-multiple", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity<List<FileTempDto>> uploadMultipleFileAndGetChecksum(@RequestBody MultipartFile[] files) throws Exception {
        return super.uploadMultipleFileAndGetChecksum(files);
    }

    @Operation(summary = "Finds a list of all modelPermissions")
    @GetMapping("")
    public ResponseEntity<List<ModelPermissionDto>> findAll() throws Exception {
        return super.findAll();
    }

    @Operation(summary = "Finds an optimized list of all modelPermissions")
    @GetMapping("optimized")
    public ResponseEntity<List<ModelPermissionDto>> findAllOptimized() throws Exception {
        return super.findAllOptimized();
    }

    @Operation(summary = "Finds a modelPermission by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ModelPermissionDto> findById(@PathVariable Long id) {
        return super.findById(id);
    }

    @Operation(summary = "Finds a modelPermission by reference")
    @GetMapping("reference/{reference}")
    public ResponseEntity<ModelPermissionDto> findByReference(@PathVariable String reference) {
        return super.findByReferenceEntity(new ModelPermission(reference));
    }

    @Operation(summary = "Saves the specified  modelPermission")
    @PostMapping("")
    public ResponseEntity<ModelPermissionDto> save(@RequestBody ModelPermissionDto dto) throws Exception {
        return super.save(dto);
    }

    @Operation(summary = "Updates the specified  modelPermission")
    @PutMapping("")
    public ResponseEntity<ModelPermissionDto> update(@RequestBody ModelPermissionDto dto) throws Exception {
        return super.update(dto);
    }

    @Operation(summary = "Delete list of modelPermission")
    @PostMapping("multiple")
    public ResponseEntity<List<ModelPermissionDto>> delete(@RequestBody List<ModelPermissionDto> listToDelete) throws Exception {
        return super.delete(listToDelete);
    }
    @Operation(summary = "Delete the specified modelPermission")
    @DeleteMapping("")
    public ResponseEntity<ModelPermissionDto> delete(@RequestBody ModelPermissionDto dto) throws Exception {
            return super.delete(dto);
    }

    @Operation(summary = "Delete the specified modelPermission")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) throws Exception {
        return super.deleteById(id);
    }
    @Operation(summary = "Delete multiple modelPermissions by ids")
    @DeleteMapping("multiple/id")
    public ResponseEntity<List<Long>> deleteByIdIn(@RequestBody List<Long> ids) throws Exception {
            return super.deleteByIdIn(ids);
     }


    @Operation(summary = "Finds modelPermissions by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ModelPermissionDto>> findByCriteria(@RequestBody ModelPermissionCriteria criteria) throws Exception {
        return super.findByCriteria(criteria);
    }

    @Operation(summary = "Finds paginated modelPermissions by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ModelPermissionCriteria criteria) throws Exception {
        return super.findPaginatedByCriteria(criteria);
    }

    @Operation(summary = "Exports modelPermissions by criteria")
    @PostMapping("export")
    public ResponseEntity<InputStreamResource> export(@RequestBody ModelPermissionCriteria criteria) throws Exception {
        return super.export(criteria);
    }

    @Operation(summary = "Gets modelPermission data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ModelPermissionCriteria criteria) throws Exception {
        return super.getDataSize(criteria);
    }



    public ModelPermissionRest (ModelPermissionService service, ModelPermissionConverter converter) {
        super(service, converter);
    }




}
