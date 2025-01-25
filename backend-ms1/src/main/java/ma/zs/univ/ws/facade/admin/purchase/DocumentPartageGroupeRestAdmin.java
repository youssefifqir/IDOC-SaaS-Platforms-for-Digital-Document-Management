package  ma.zs.univ.ws.facade.admin.purchase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.univ.bean.core.purchase.DocumentPartageGroupe;
import ma.zs.univ.dao.criteria.core.purchase.DocumentPartageGroupeCriteria;
import ma.zs.univ.service.facade.admin.purchase.DocumentPartageGroupeAdminService;
import ma.zs.univ.ws.converter.purchase.DocumentPartageGroupeConverter;
import ma.zs.univ.ws.dto.purchase.DocumentPartageGroupeDto;
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
@RequestMapping("/api/admin/documentPartageGroupe/")
public class DocumentPartageGroupeRestAdmin {




    @Operation(summary = "Finds a list of all documentPartageGroupes")
    @GetMapping("")
    public ResponseEntity<List<DocumentPartageGroupeDto>> findAll() throws Exception {
        ResponseEntity<List<DocumentPartageGroupeDto>> res = null;
        List<DocumentPartageGroupe> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<DocumentPartageGroupeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a documentPartageGroupe by id")
    @GetMapping("id/{id}")
    public ResponseEntity<DocumentPartageGroupeDto> findById(@PathVariable Long id) {
        DocumentPartageGroupe t = service.findById(id);
        if (t != null) {
            converter.init(true);
            DocumentPartageGroupeDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  documentPartageGroupe")
    @PostMapping("")
    public ResponseEntity<DocumentPartageGroupeDto> save(@RequestBody DocumentPartageGroupeDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            DocumentPartageGroupe myT = converter.toItem(dto);
            DocumentPartageGroupe t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                DocumentPartageGroupeDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  documentPartageGroupe")
    @PutMapping("")
    public ResponseEntity<DocumentPartageGroupeDto> update(@RequestBody DocumentPartageGroupeDto dto) throws Exception {
        ResponseEntity<DocumentPartageGroupeDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            DocumentPartageGroupe t = service.findById(dto.getId());
            converter.copy(dto,t);
            DocumentPartageGroupe updated = service.update(t);
            DocumentPartageGroupeDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of documentPartageGroupe")
    @PostMapping("multiple")
    public ResponseEntity<List<DocumentPartageGroupeDto>> delete(@RequestBody List<DocumentPartageGroupeDto> dtos) throws Exception {
        ResponseEntity<List<DocumentPartageGroupeDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<DocumentPartageGroupe> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified documentPartageGroupe")
    @DeleteMapping("")
    public ResponseEntity<DocumentPartageGroupeDto> delete(@RequestBody DocumentPartageGroupeDto dto) throws Exception {
		ResponseEntity<DocumentPartageGroupeDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            DocumentPartageGroupe t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified documentPartageGroupe")
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
    @Operation(summary = "Delete multiple documentPartageGroupes by ids")
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


    @Operation(summary = "find by acessShare id")
    @GetMapping("acessShare/id/{id}")
    public List<DocumentPartageGroupeDto> findByAcessShareId(@PathVariable Long id){
        return findDtos(service.findByAcessShareId(id));
    }
    @Operation(summary = "delete by acessShare id")
    @DeleteMapping("acessShare/id/{id}")
    public int deleteByAcessShareId(@PathVariable Long id){
        return service.deleteByAcessShareId(id);
    }

    @Operation(summary = "Finds a documentPartageGroupe and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<DocumentPartageGroupeDto> findWithAssociatedLists(@PathVariable Long id) {
        DocumentPartageGroupe loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        DocumentPartageGroupeDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds documentPartageGroupes by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<DocumentPartageGroupeDto>> findByCriteria(@RequestBody DocumentPartageGroupeCriteria criteria) throws Exception {
        ResponseEntity<List<DocumentPartageGroupeDto>> res = null;
        List<DocumentPartageGroupe> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<DocumentPartageGroupeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated documentPartageGroupes by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody DocumentPartageGroupeCriteria criteria) throws Exception {
        List<DocumentPartageGroupe> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<DocumentPartageGroupeDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets documentPartageGroupe data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody DocumentPartageGroupeCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<DocumentPartageGroupeDto> findDtos(List<DocumentPartageGroupe> list){
        converter.initObject(true);
        List<DocumentPartageGroupeDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<DocumentPartageGroupeDto> getDtoResponseEntity(DocumentPartageGroupeDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private DocumentPartageGroupeAdminService service;
    @Autowired private DocumentPartageGroupeConverter converter;





}
