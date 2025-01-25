package  ma.zs.univ.ws.facade.admin.doc;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.univ.bean.core.doc.DocumentTag;
import ma.zs.univ.dao.criteria.core.doc.DocumentTagCriteria;
import ma.zs.univ.service.facade.admin.doc.DocumentTagAdminService;
import ma.zs.univ.ws.converter.doc.DocumentTagConverter;
import ma.zs.univ.ws.dto.doc.DocumentTagDto;
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
@RequestMapping("/api/admin/documentTag/")
public class DocumentTagRestAdmin {




    @Operation(summary = "Finds a list of all documentTags")
    @GetMapping("")
    public ResponseEntity<List<DocumentTagDto>> findAll() throws Exception {
        ResponseEntity<List<DocumentTagDto>> res = null;
        List<DocumentTag> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<DocumentTagDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a documentTag by id")
    @GetMapping("id/{id}")
    public ResponseEntity<DocumentTagDto> findById(@PathVariable Long id) {
        DocumentTag t = service.findById(id);
        if (t != null) {
            converter.init(true);
            DocumentTagDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  documentTag")
    @PostMapping("")
    public ResponseEntity<DocumentTagDto> save(@RequestBody DocumentTagDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            DocumentTag myT = converter.toItem(dto);
            DocumentTag t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                DocumentTagDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  documentTag")
    @PutMapping("")
    public ResponseEntity<DocumentTagDto> update(@RequestBody DocumentTagDto dto) throws Exception {
        ResponseEntity<DocumentTagDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            DocumentTag t = service.findById(dto.getId());
            converter.copy(dto,t);
            DocumentTag updated = service.update(t);
            DocumentTagDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of documentTag")
    @PostMapping("multiple")
    public ResponseEntity<List<DocumentTagDto>> delete(@RequestBody List<DocumentTagDto> dtos) throws Exception {
        ResponseEntity<List<DocumentTagDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<DocumentTag> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified documentTag")
    @DeleteMapping("")
    public ResponseEntity<DocumentTagDto> delete(@RequestBody DocumentTagDto dto) throws Exception {
		ResponseEntity<DocumentTagDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            DocumentTag t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified documentTag")
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
    @Operation(summary = "Delete multiple documentTags by ids")
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



    @Operation(summary = "Finds a documentTag and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<DocumentTagDto> findWithAssociatedLists(@PathVariable Long id) {
        DocumentTag loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        DocumentTagDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds documentTags by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<DocumentTagDto>> findByCriteria(@RequestBody DocumentTagCriteria criteria) throws Exception {
        ResponseEntity<List<DocumentTagDto>> res = null;
        List<DocumentTag> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<DocumentTagDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated documentTags by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody DocumentTagCriteria criteria) throws Exception {
        List<DocumentTag> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<DocumentTagDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets documentTag data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody DocumentTagCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<DocumentTagDto> findDtos(List<DocumentTag> list){
        converter.initObject(true);
        List<DocumentTagDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<DocumentTagDto> getDtoResponseEntity(DocumentTagDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private DocumentTagAdminService service;
    @Autowired private DocumentTagConverter converter;





}
