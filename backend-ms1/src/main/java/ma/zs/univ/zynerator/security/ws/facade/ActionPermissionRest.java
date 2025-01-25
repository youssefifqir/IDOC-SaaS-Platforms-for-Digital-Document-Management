package ma.zs.univ.zynerator.security.ws.facade;

import io.swagger.v3.oas.annotations.Operation;
import ma.zs.univ.zynerator.controller.AbstractController;
import ma.zs.univ.zynerator.dto.FileTempDto;
import ma.zs.univ.zynerator.security.bean.ActionPermission;
import ma.zs.univ.zynerator.security.dao.criteria.core.ActionPermissionCriteria;
import ma.zs.univ.zynerator.security.service.facade.ActionPermissionService;
import ma.zs.univ.zynerator.security.ws.converter.ActionPermissionConverter;
import ma.zs.univ.zynerator.security.ws.dto.ActionPermissionDto;
import ma.zs.univ.zynerator.util.PaginatedList;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/actionPermission/")
public class ActionPermissionRest  extends AbstractController<ActionPermission, ActionPermissionDto, ActionPermissionCriteria, ActionPermissionService, ActionPermissionConverter> {



    @Operation(summary = "upload one actionPermission")
    @RequestMapping(value = "upload", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity<FileTempDto> uploadFileAndGetChecksum(@RequestBody MultipartFile file) throws Exception {
        return super.uploadFileAndGetChecksum(file);
    }
    @Operation(summary = "upload multiple actionPermissions")
    @RequestMapping(value = "upload-multiple", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity<List<FileTempDto>> uploadMultipleFileAndGetChecksum(@RequestBody MultipartFile[] files) throws Exception {
        return super.uploadMultipleFileAndGetChecksum(files);
    }

    @Operation(summary = "Finds a list of all actionPermissions")
    @GetMapping("")
    public ResponseEntity<List<ActionPermissionDto>> findAll() throws Exception {
        return super.findAll();
    }

    @Operation(summary = "Finds an optimized list of all actionPermissions")
    @GetMapping("optimized")
    public ResponseEntity<List<ActionPermissionDto>> findAllOptimized() throws Exception {
        return super.findAllOptimized();
    }

    @Operation(summary = "Finds a actionPermission by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ActionPermissionDto> findById(@PathVariable Long id) {
        return super.findById(id);
    }

    @Operation(summary = "Finds a actionPermission by reference")
    @GetMapping("reference/{reference}")
    public ResponseEntity<ActionPermissionDto> findByReference(@PathVariable String reference) {
        return super.findByReferenceEntity(new ActionPermission(reference));
    }

    @Operation(summary = "Saves the specified  actionPermission")
    @PostMapping("")
    public ResponseEntity<ActionPermissionDto> save(@RequestBody ActionPermissionDto dto) throws Exception {
        return super.save(dto);
    }

    @Operation(summary = "Updates the specified  actionPermission")
    @PutMapping("")
    public ResponseEntity<ActionPermissionDto> update(@RequestBody ActionPermissionDto dto) throws Exception {
        return super.update(dto);
    }

    @Operation(summary = "Delete list of actionPermission")
    @PostMapping("multiple")
    public ResponseEntity<List<ActionPermissionDto>> delete(@RequestBody List<ActionPermissionDto> listToDelete) throws Exception {
        return super.delete(listToDelete);
    }
    @Operation(summary = "Delete the specified actionPermission")
    @DeleteMapping("")
    public ResponseEntity<ActionPermissionDto> delete(@RequestBody ActionPermissionDto dto) throws Exception {
            return super.delete(dto);
    }

    @Operation(summary = "Delete the specified actionPermission")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) throws Exception {
        return super.deleteById(id);
    }
    @Operation(summary = "Delete multiple actionPermissions by ids")
    @DeleteMapping("multiple/id")
    public ResponseEntity<List<Long>> deleteByIdIn(@RequestBody List<Long> ids) throws Exception {
            return super.deleteByIdIn(ids);
     }


    @Operation(summary = "Finds actionPermissions by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ActionPermissionDto>> findByCriteria(@RequestBody ActionPermissionCriteria criteria) throws Exception {
        return super.findByCriteria(criteria);
    }

    @Operation(summary = "Finds paginated actionPermissions by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ActionPermissionCriteria criteria) throws Exception {
        return super.findPaginatedByCriteria(criteria);
    }

    @Operation(summary = "Exports actionPermissions by criteria")
    @PostMapping("export")
    public ResponseEntity<InputStreamResource> export(@RequestBody ActionPermissionCriteria criteria) throws Exception {
        return super.export(criteria);
    }

    @Operation(summary = "Gets actionPermission data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ActionPermissionCriteria criteria) throws Exception {
        return super.getDataSize(criteria);
    }



    public ActionPermissionRest (ActionPermissionService service, ActionPermissionConverter converter) {
        super(service, converter);
    }




}
