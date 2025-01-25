package ma.zs.univ.zynerator.security.ws.facade;

import io.swagger.v3.oas.annotations.Operation;
import ma.zs.univ.zynerator.controller.AbstractController;
import ma.zs.univ.zynerator.dto.FileTempDto;
import ma.zs.univ.zynerator.security.bean.Role;
import ma.zs.univ.zynerator.security.dao.criteria.core.RoleCriteria;
import ma.zs.univ.zynerator.security.service.facade.RoleService;
import ma.zs.univ.zynerator.security.ws.converter.RoleConverter;
import ma.zs.univ.zynerator.security.ws.dto.RoleDto;
import ma.zs.univ.zynerator.util.PaginatedList;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/role/")
public class RoleRest  extends AbstractController<Role, RoleDto, RoleCriteria, RoleService, RoleConverter> {



    @Operation(summary = "upload one role")
    @RequestMapping(value = "upload", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity<FileTempDto> uploadFileAndGetChecksum(@RequestBody MultipartFile file) throws Exception {
        return super.uploadFileAndGetChecksum(file);
    }
    @Operation(summary = "upload multiple roles")
    @RequestMapping(value = "upload-multiple", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity<List<FileTempDto>> uploadMultipleFileAndGetChecksum(@RequestBody MultipartFile[] files) throws Exception {
        return super.uploadMultipleFileAndGetChecksum(files);
    }

    @Operation(summary = "Finds a list of all roles")
    @GetMapping("")
    public ResponseEntity<List<RoleDto>> findAll() throws Exception {
        return super.findAll();
    }

    @Operation(summary = "Finds an optimized list of all roles")
    @GetMapping("optimized")
    public ResponseEntity<List<RoleDto>> findAllOptimized() throws Exception {
        return super.findAllOptimized();
    }

    @Operation(summary = "Finds a role by id")
    @GetMapping("id/{id}")
    public ResponseEntity<RoleDto> findById(@PathVariable Long id) {
        return super.findById(id);
    }

    @Operation(summary = "Finds a role by authority")
    @GetMapping("authority/{authority}")
    public ResponseEntity<RoleDto> findByAuthority(@PathVariable String authority) {
        return super.findByReferenceEntity(new Role(authority));
    }

    @Operation(summary = "Saves the specified  role")
    @PostMapping("")
    public ResponseEntity<RoleDto> save(@RequestBody RoleDto dto) throws Exception {
        return super.save(dto);
    }

    @Operation(summary = "Updates the specified  role")
    @PutMapping("")
    public ResponseEntity<RoleDto> update(@RequestBody RoleDto dto) throws Exception {
        return super.update(dto);
    }

    @Operation(summary = "Delete list of role")
    @PostMapping("multiple")
    public ResponseEntity<List<RoleDto>> delete(@RequestBody List<RoleDto> listToDelete) throws Exception {
        return super.delete(listToDelete);
    }
    @Operation(summary = "Delete the specified role")
    @DeleteMapping("")
    public ResponseEntity<RoleDto> delete(@RequestBody RoleDto dto) throws Exception {
            return super.delete(dto);
    }

    @Operation(summary = "Delete the specified role")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) throws Exception {
        return super.deleteById(id);
    }
    @Operation(summary = "Delete multiple roles by ids")
    @DeleteMapping("multiple/id")
    public ResponseEntity<List<Long>> deleteByIdIn(@RequestBody List<Long> ids) throws Exception {
            return super.deleteByIdIn(ids);
     }


    @Operation(summary = "Finds roles by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<RoleDto>> findByCriteria(@RequestBody RoleCriteria criteria) throws Exception {
        return super.findByCriteria(criteria);
    }

    @Operation(summary = "Finds paginated roles by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody RoleCriteria criteria) throws Exception {
        return super.findPaginatedByCriteria(criteria);
    }

    @Operation(summary = "Exports roles by criteria")
    @PostMapping("export")
    public ResponseEntity<InputStreamResource> export(@RequestBody RoleCriteria criteria) throws Exception {
        return super.export(criteria);
    }

    @Operation(summary = "Gets role data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody RoleCriteria criteria) throws Exception {
        return super.getDataSize(criteria);
    }



    public RoleRest (RoleService service, RoleConverter converter) {
        super(service, converter);
    }




}
