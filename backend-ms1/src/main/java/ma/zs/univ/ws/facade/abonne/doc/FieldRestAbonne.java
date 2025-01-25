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

import ma.zs.univ.bean.core.doc.Field;
import ma.zs.univ.dao.criteria.core.doc.FieldCriteria;
import ma.zs.univ.service.facade.abonne.doc.FieldAbonneService;
import ma.zs.univ.ws.converter.doc.FieldConverter;
import ma.zs.univ.ws.dto.doc.FieldDto;
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
@RequestMapping("/api/abonne/field/")
public class FieldRestAbonne {




    @Operation(summary = "Finds a list of all fields")
    @GetMapping("")
    public ResponseEntity<List<FieldDto>> findAll() throws Exception {
        ResponseEntity<List<FieldDto>> res = null;
        List<Field> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<FieldDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all fields")
    @GetMapping("optimized")
    public ResponseEntity<List<FieldDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<FieldDto>> res = null;
        List<Field> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<FieldDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a field by id")
    @GetMapping("id/{id}")
    public ResponseEntity<FieldDto> findById(@PathVariable Long id) {
        Field t = service.findById(id);
        if (t != null) {
            FieldDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a field by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<FieldDto> findByLibelle(@PathVariable String libelle) {
	    Field t = service.findByReferenceEntity(new Field(libelle));
        if (t != null) {
            FieldDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  field")
    @PostMapping("")
    public ResponseEntity<FieldDto> save(@RequestBody FieldDto dto) throws Exception {
        if(dto!=null){
            Field myT = converter.toItem(dto);
            Field t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                FieldDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  field")
    @PutMapping("")
    public ResponseEntity<FieldDto> update(@RequestBody FieldDto dto) throws Exception {
        ResponseEntity<FieldDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Field t = service.findById(dto.getId());
            converter.copy(dto,t);
            Field updated = service.update(t);
            FieldDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of field")
    @PostMapping("multiple")
    public ResponseEntity<List<FieldDto>> delete(@RequestBody List<FieldDto> dtos) throws Exception {
        ResponseEntity<List<FieldDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Field> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified field")
    @DeleteMapping("")
    public ResponseEntity<FieldDto> delete(@RequestBody FieldDto dto) throws Exception {
		ResponseEntity<FieldDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            Field t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified field")
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
    @Operation(summary = "Delete multiple fields by ids")
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



    @Operation(summary = "Finds a field and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<FieldDto> findWithAssociatedLists(@PathVariable Long id) {
        Field loaded =  service.findWithAssociatedLists(id);
        FieldDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds fields by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<FieldDto>> findByCriteria(@RequestBody FieldCriteria criteria) throws Exception {
        ResponseEntity<List<FieldDto>> res = null;
        List<Field> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<FieldDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated fields by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody FieldCriteria criteria) throws Exception {
        List<Field> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<FieldDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets field data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody FieldCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<FieldDto> findDtos(List<Field> list){
        List<FieldDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<FieldDto> getDtoResponseEntity(FieldDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private FieldAbonneService service;
    @Autowired private FieldConverter converter;





}
