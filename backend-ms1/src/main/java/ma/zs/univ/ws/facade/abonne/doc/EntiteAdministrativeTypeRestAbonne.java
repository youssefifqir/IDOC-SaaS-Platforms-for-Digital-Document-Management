package  ma.zs.univ.ws.facade.abonne.doc;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.univ.bean.core.doc.EntiteAdministrativeType;
import ma.zs.univ.dao.criteria.core.doc.EntiteAdministrativeTypeCriteria;
import ma.zs.univ.service.facade.abonne.doc.EntiteAdministrativeTypeAbonneService;
import ma.zs.univ.ws.converter.doc.EntiteAdministrativeTypeConverter;
import ma.zs.univ.ws.dto.doc.EntiteAdministrativeTypeDto;
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
@RequestMapping("/api/abonne/entiteAdministrativeType/")
public class EntiteAdministrativeTypeRestAbonne {




    @Operation(summary = "Finds a list of all entiteAdministrativeTypes")
    @GetMapping("")
    public ResponseEntity<List<EntiteAdministrativeTypeDto>> findAll() throws Exception {
        ResponseEntity<List<EntiteAdministrativeTypeDto>> res = null;
        List<EntiteAdministrativeType> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<EntiteAdministrativeTypeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all entiteAdministrativeTypes")
    @GetMapping("optimized")
    public ResponseEntity<List<EntiteAdministrativeTypeDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<EntiteAdministrativeTypeDto>> res = null;
        List<EntiteAdministrativeType> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<EntiteAdministrativeTypeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a entiteAdministrativeType by id")
    @GetMapping("id/{id}")
    public ResponseEntity<EntiteAdministrativeTypeDto> findById(@PathVariable Long id) {
        EntiteAdministrativeType t = service.findById(id);
        if (t != null) {
            EntiteAdministrativeTypeDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a entiteAdministrativeType by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<EntiteAdministrativeTypeDto> findByLibelle(@PathVariable String libelle) {
	    EntiteAdministrativeType t = service.findByReferenceEntity(new EntiteAdministrativeType(libelle));
        if (t != null) {
            EntiteAdministrativeTypeDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  entiteAdministrativeType")
    @PostMapping("")
    public ResponseEntity<EntiteAdministrativeTypeDto> save(@RequestBody EntiteAdministrativeTypeDto dto) throws Exception {
        if(dto!=null){
            EntiteAdministrativeType myT = converter.toItem(dto);
            EntiteAdministrativeType t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                EntiteAdministrativeTypeDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  entiteAdministrativeType")
    @PutMapping("")
    public ResponseEntity<EntiteAdministrativeTypeDto> update(@RequestBody EntiteAdministrativeTypeDto dto) throws Exception {
        ResponseEntity<EntiteAdministrativeTypeDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            EntiteAdministrativeType t = service.findById(dto.getId());
            converter.copy(dto,t);
            EntiteAdministrativeType updated = service.update(t);
            EntiteAdministrativeTypeDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of entiteAdministrativeType")
    @PostMapping("multiple")
    public ResponseEntity<List<EntiteAdministrativeTypeDto>> delete(@RequestBody List<EntiteAdministrativeTypeDto> dtos) throws Exception {
        ResponseEntity<List<EntiteAdministrativeTypeDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<EntiteAdministrativeType> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified entiteAdministrativeType")
    @DeleteMapping("")
    public ResponseEntity<EntiteAdministrativeTypeDto> delete(@RequestBody EntiteAdministrativeTypeDto dto) throws Exception {
		ResponseEntity<EntiteAdministrativeTypeDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            EntiteAdministrativeType t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified entiteAdministrativeType")
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
    @Operation(summary = "Delete multiple entiteAdministrativeTypes by ids")
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



    @Operation(summary = "Finds a entiteAdministrativeType and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<EntiteAdministrativeTypeDto> findWithAssociatedLists(@PathVariable Long id) {
        EntiteAdministrativeType loaded =  service.findWithAssociatedLists(id);
        EntiteAdministrativeTypeDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds entiteAdministrativeTypes by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<EntiteAdministrativeTypeDto>> findByCriteria(@RequestBody EntiteAdministrativeTypeCriteria criteria) throws Exception {
        ResponseEntity<List<EntiteAdministrativeTypeDto>> res = null;
        List<EntiteAdministrativeType> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<EntiteAdministrativeTypeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated entiteAdministrativeTypes by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody EntiteAdministrativeTypeCriteria criteria) throws Exception {
        List<EntiteAdministrativeType> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<EntiteAdministrativeTypeDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets entiteAdministrativeType data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody EntiteAdministrativeTypeCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<EntiteAdministrativeTypeDto> findDtos(List<EntiteAdministrativeType> list){
        List<EntiteAdministrativeTypeDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<EntiteAdministrativeTypeDto> getDtoResponseEntity(EntiteAdministrativeTypeDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private EntiteAdministrativeTypeAbonneService service;
    @Autowired private EntiteAdministrativeTypeConverter converter;





}
