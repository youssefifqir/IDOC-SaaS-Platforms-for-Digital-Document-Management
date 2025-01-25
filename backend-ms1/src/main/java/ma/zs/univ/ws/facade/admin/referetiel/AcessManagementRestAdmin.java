package  ma.zs.univ.ws.facade.admin.referetiel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.univ.bean.core.referetiel.AcessManagement;
import ma.zs.univ.dao.criteria.core.referetiel.AcessManagementCriteria;
import ma.zs.univ.service.facade.admin.referetiel.AcessManagementAdminService;
import ma.zs.univ.ws.converter.referetiel.AcessManagementConverter;
import ma.zs.univ.ws.dto.referetiel.AcessManagementDto;
import ma.zs.univ.zynerator.controller.AbstractController;
import ma.zs.univ.zynerator.dto.AuditEntityDto;
import ma.zs.univ.zynerator.util.PaginatedList;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zs.univ.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zs.univ.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/acessManagement/")
public class AcessManagementRestAdmin {




    @Operation(summary = "Finds a list of all acessManagements")
    @GetMapping("")
    public ResponseEntity<List<AcessManagementDto>> findAll() throws Exception {
        ResponseEntity<List<AcessManagementDto>> res = null;
        List<AcessManagement> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<AcessManagementDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all acessManagements")
    @GetMapping("optimized")
    public ResponseEntity<List<AcessManagementDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<AcessManagementDto>> res = null;
        List<AcessManagement> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<AcessManagementDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a acessManagement by id")
    @GetMapping("id/{id}")
    public ResponseEntity<AcessManagementDto> findById(@PathVariable Long id) {
        AcessManagement t = service.findById(id);
        if (t != null) {
            AcessManagementDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a acessManagement by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<AcessManagementDto> findByLibelle(@PathVariable String libelle) {
	    AcessManagement t = service.findByReferenceEntity(new AcessManagement(libelle));
        if (t != null) {
            AcessManagementDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  acessManagement")
    @PostMapping("")
    public ResponseEntity<AcessManagementDto> save(@RequestBody AcessManagementDto dto) throws Exception {
        if(dto!=null){
            AcessManagement myT = converter.toItem(dto);
            AcessManagement t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                AcessManagementDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  acessManagement")
    @PutMapping("")
    public ResponseEntity<AcessManagementDto> update(@RequestBody AcessManagementDto dto) throws Exception {
        ResponseEntity<AcessManagementDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            AcessManagement t = service.findById(dto.getId());
            converter.copy(dto,t);
            AcessManagement updated = service.update(t);
            AcessManagementDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of acessManagement")
    @PostMapping("multiple")
    public ResponseEntity<List<AcessManagementDto>> delete(@RequestBody List<AcessManagementDto> dtos) throws Exception {
        ResponseEntity<List<AcessManagementDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<AcessManagement> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified acessManagement")
    @DeleteMapping("")
    public ResponseEntity<AcessManagementDto> delete(@RequestBody AcessManagementDto dto) throws Exception {
		ResponseEntity<AcessManagementDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            AcessManagement t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified acessManagement")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) throws Exception {
        ResponseEntity<Long> res;
        HttpStatus status = HttpStatus.PRECONDITION_FAILED;
        if (id != null) {
            boolean resultDelete = service.deleteById(id);
            if (resultDelete) {
                status = HttpStatus.OK;
            }
        }
        res = new ResponseEntity<>(id, status);
        return res;
    }
    @Operation(summary = "Delete multiple acessManagements by ids")
    @DeleteMapping("multiple/id")
    public ResponseEntity<List<Long>> deleteByIdIn(@RequestBody List<Long> ids) throws Exception {
        ResponseEntity<List<Long>> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (ids != null) {
            service.deleteByIdIn(ids);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(ids, status);
        return res;
     }



    @Operation(summary = "Finds a acessManagement and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<AcessManagementDto> findWithAssociatedLists(@PathVariable Long id) {
        AcessManagement loaded =  service.findWithAssociatedLists(id);
        AcessManagementDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds acessManagements by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<AcessManagementDto>> findByCriteria(@RequestBody AcessManagementCriteria criteria) throws Exception {
        ResponseEntity<List<AcessManagementDto>> res = null;
        List<AcessManagement> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<AcessManagementDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated acessManagements by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody AcessManagementCriteria criteria) throws Exception {
        List<AcessManagement> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<AcessManagementDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets acessManagement data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody AcessManagementCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<AcessManagementDto> findDtos(List<AcessManagement> list){
        List<AcessManagementDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<AcessManagementDto> getDtoResponseEntity(AcessManagementDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private AcessManagementAdminService service;
    @Autowired private AcessManagementConverter converter;





}
